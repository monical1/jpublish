#ORM support from JPublish

# Introduction #

This page is about various ORM frameworks supported by JPublish.


# Cayenne #
Cayenne is a Java object relational mapping (ORM) framework. In other words, it is a tool for Java developers who need to talk to a database (or many databases). Rather than hardcoding SQL statements through Java code, Cayenne allows a programmer to work only with Java objects abstracted from the database. Cayenne is supported via a JPublish Module integration. For a complete description of Cayenne's features, please visit Cayenne's own [site](http://cayenne.apache.org/).

To enable Cayenne support in a JPublish web application you have to add the new JPCayenneModule to the jpublish.xml configuration file. For example:

```
    <!--JPublish Cayenne support -->
    <module classname="org.jpublish.module.cayenne.JPCayenneModule">
        <cayenne-config-path>/WEB-INF/cayenne</cayenne-config-path>
        <auto-rollback>true</auto-rollback>
        <session-scope>false</session-scope>
        <shared-cache>true</shared-cache>

        <!--
         ~ Http request paths using a per-request or a per-session Cayenne ObjectContext (OC),
         ~ the read-only paths will be interpreted first and will use a global OC one defined
         ~ per web app instance.
         ~ -->
        <cayenne-enabled-urls>
            <url path="/info/*" readonly="true"/>
            <url path="/status/*" readonly="true"/>
            <url path="/rss/*" readonly="true"/>
            <url path="/users/*" readonly="false"/>
            <url path="/companies/*"/> <!-- readonly="false" by default, if not defined -->
        </cayenne-enabled-urls>

        <debug>true</debug>
    </module>
```

Let's explain the above configuration.

The JPCayenneModule installs a couple of AFTER/BEFORE like Actions which binds DataContext objects to the request before executing any other Actions for the cayenne-enabled-urls. AFTER the request was consumed and the View was rendered, the JPCayenneModule will automatically rollback any uncommitted changes.

If the in debug mode, JPCayenneModule will log any uncommitted data objects at the end of each request.

For units of work spanning multiple requests, such as a multi-page work flow, it is recommended that you add a separate DataContext (DC) to the session for the unit of work. Basically enabling the session-scope.

**Session vs Request Scope**
The DataContext objects can be associated with the users HttpSession allowing objects to be cached in the users DataContext or can be configured to create a new DataContext object for every request matching the cayenne-enabled-urls. Using session scope DataObjects is a good option for web applications which have exclusive access to the underlying database. However when the web application is sharing a database you should probably disable this option by setting the session-scope parameter to false.

**Shared Cache**
If enabled, the DataContext objects will be created in the Cayenne's shared cache. This is a good option for web applications which have exclusive access to the underlying database. However if the web applications will share a database you should probably disable this option by setting the shared-cache init parameter to false.


With JPCayenneModule two DCs are offered. One per request for the requests requiring R/W access to the database and a global DC instance which is stored in the JPublish application's context. We recommend to use the later one for R/O requests.

Following the examples of Cayenne's Wiki and the CLICK web framework, we are going a bit further and we liberalize the access to some of the core Cayenne functions through an object we call: jpCayenneService, available in the JPublish context. For example, an Action that is required by one of the cayenne-enabled-urls, can use this service like this:

```
jpCayenneService = context.get( JPCayenneModule.JPCAYENNE_SERVICE_NAME);
if( jpCayenneService!= null){
	Expression qual = ExpressionFactory.likeIgnoreCaseExp("name", "%");
	SelectQuery select = new SelectQuery(User.class, qual);

    users = jpCayenneService.performQuery( select);

    if( users != null)
    context.put( "users", users);
}
```

Below are the main methods of the jpCayenneService (closely following the [examples from Cayenne's Wiki](http://cwiki.apache.org/CAY/spring-integration-examples.html)) and their related Java comments:
```
/**
 * Instantiates new object and registers it with itself. Object class must
 * have a default constructor.
 *
 * @param dataObjectClass the data object class to create and register
 * @return the new registered data object
 */
Persistent createAndRegisterNewObject(Class dataObjectClass);

/**
 * Commit any changes in the thread local DataContext.
 */
void commitChanges();

/**
 * Schedules an object for deletion on the next commit of this DataContext. Object's
 * persistence state is changed to PersistenceState.DELETED; objects related to this
 * object are processed according to delete rules, i.e. relationships can be unset
 * ("nullify" rule), deletion operation is cascaded (cascade rule).
 *
 * @param dataObject a persistent data object that we want to delete
 * @throws org.apache.cayenne.DeleteDenyException
 *          if a DENY delete rule
 *          is applicable for object deletion
 */
void deleteObject(DataObject dataObject) throws DeleteDenyException;

/**
 * Find the data object for the specified class, property name and property
 * value, or null if no data object was found.
 *
 * @param dataObjectClass the data object class to find
 * @param property        the name of the property
 * @param value           the value of the property
 * @return the data object for the specified class, property name and property value
 * @throws RuntimeException if more than one data object was identified for the
 *                          given property name and value
 */
DataObject findObject(Class dataObjectClass, String property,
                             Object value);

/**
 * Return the thread local DataContext.
 *
 * @return the thread local DataContext
 * @throws IllegalStateException if there is no DataContext bound to the current thread
 */
DataContext getDataContext();

/**
 * Perform a database query returning the data object specified by the
 * class and the primary key. This method will perform a database query
 * and refresh the object cache.
 *
 * @param doClass the data object class to retrieve
 * @param id      the data object primary key
 * @return the data object for the given class and id
 */
DataObject getObjectForPK(Class doClass, Object id);

/**
 * Perform a query returning the data object specified by the
 * class and the primary key value. If the refresh parameter is true a
 * database query will be performed, otherwise the a query against the
 * object cache will be performed first.
 *
 * @param dataObjectClass the data object class to retrieve
 * @param id              the data object primary key
 * @param refresh         the refresh the object cache mode
 * @return the data object for the given class and id
 */
DataObject getObjectForPK(Class dataObjectClass, Object id, boolean refresh);

/**
 * Return the database primary key column name for the given data object.
 *
 * @param dataObjectClass the class of the data object
 * @return the primary key column name
 */
String getPkName(Class dataObjectClass);

/**
 * Performs a single selecting query. Various query setting control the behavior of
 * this method and the results returned:
 * <ul>
 * <li>Query caching policy defines whether the results are retrieved from cache or
 * fetched from the database. Note that queries that use caching must have a name that
 * is used as a caching key.
 * </li>
 * <li>Query refreshing policy controls whether to refresh existing data objects and
 * ignore any cached values.
 * </li>
 * <li>Query data rows policy defines whether the result should be returned as
 * DataObjects or DataRows.
 * </li>
 * </ul>
 *
 * @param query the query to perform
 * @return a list of DataObjects or a DataRows for the query
 */
List performQuery(Query query);

/**
 * Returns a list of objects or DataRows for a named query stored in one of the
 * DataMaps. Internally Cayenne uses a caching policy defined in the named query. If
 * refresh flag is true, a refresh is forced no matter what the caching policy is.
 *
 * @param queryName a name of a GenericSelectQuery defined in one of the DataMaps. If
 *                  no such query is defined, this method will throw a CayenneRuntimeException
 * @param refresh   A flag that determines whether refresh of <b>cached lists</b>
 *                  is required in case a query uses caching.
 * @return the list of data object or DataRows for the named query
 */
List performQuery(String queryName, boolean refresh);

/**
 * Returns a list of objects or DataRows for a named query stored in one of the
 * DataMaps. Internally Cayenne uses a caching policy defined in the named query. If
 * refresh flag is true, a refresh is forced no matter what the caching policy is.
 *
 * @param queryName  a name of a GenericSelectQuery defined in one of the DataMaps. If
 *                   no such query is defined, this method will throw a CayenneRuntimeException
 * @param parameters A map of parameters to use with stored query
 * @param refresh    A flag that determines whether refresh of <b>cached lists</b>
 *                   is required in case a query uses caching.
 * @return the list of data object or DataRows for the named query
 */
List performQuery(String queryName, Map parameters, boolean refresh);

/**
 * Return a list of data object of the specified class for the given property
 * and value.
 *
 * @param dataObjectClass the data object class to return
 * @param property        the name of the property to select
 * @param value           the property value to select
 * @return a list of data objects for the given class and property name and value
 */
List performQuery(Class dataObjectClass, String property, Object value);

/**
 * Performs a single database query that does not select rows. Returns an
 * array of update counts.
 *
 * @param query the query to perform
 * @return the array of update counts
 */
int[] performNonSelectingQuery(Query query);

/**
 * Performs a named mapped query that does not select rows. Returns an array
 * of update counts.
 *
 * @param queryName the name of the query to perform
 * @return the array of update counts
 */
int[] performNonSelectingQuery(String queryName);

/**
 * Performs a named mapped non-selecting query using a map of parameters.
 * Returns an array of update counts.
 *
 * @param queryName  the name of the query to perform
 * @param parameters the Map of query paramater names and values
 * @return the array of update counts
 */
int[] performNonSelectingQuery(String queryName, Map parameters);

/**
 * Registers a transient object with the context, recursively registering all
 * transient DataObjects attached to this object via relationships.
 *
 * @param dataObject new object that needs to be made persistent
 */
void registerNewObject(DataObject dataObject);

/**
 * Reverts any changes that have occurred to objects registered in the
 * thread local DataContext.
 */
void rollbackChanges();

/**
 * Return a Map containing the given key name and value.
 *
 * @param key   the map key name
 * @param value the map key value
 * @return a Map containing the given key name and value
 */
Map toMap(String key, Object value);

```

For a very simple web application using Cayenne in JPublish, please visit our [download area](http://code.google.com/p/jpublish/downloads/list).