# JPublish contexts #

**`JPublishContext`**

The JPublish context is a map of objects which are made available to actions and views for every request. Each time a request is made a new context is created which is only valid for that request. The context is used as a way of exposing objects and values created or obtained in actions to the view. The context also provides access to common objects such as the Servlet objects application, session and request and JPublish specific objects including the site (SiteContext) and page.

Actions which need to expose objects to the view layer need only execute `context.put("name", value)`.


There are number of variables which are available in the context:
|Name| 	Class |	Description |
|:---|:-------|:------------|
|syslog |	org.apache.commons.logging.Log | system logger|
|site |	org.jpublish.SiteContext |	The SiteContext.|
|request |	javax.servlet.http.HttpServletRequest |	The HTTP servlet request. |
|response |	javax.servlet.http.HttpServletResponse |	The HTTP servlet response. |
|session |	javax.servlet.http.HttpSession |	The HTTP session object. |
|application |	javax.servlet.ServletContext |	The HTTP servlet context. |
|page |	org.jpublish.Page |	The Page object. |


**`SiteContext`**

The SiteContext object is created when JPublish first loads and is available throughout the life of the JPublish application. The SiteContext contains a wide arrange of objects which are used through the JPublish framework. Through the SiteContext you can access most of the jpublish.xml configuration settings as well as the objects which are created when the JPublish configuration is loaded.

The SiteContext can be also used for storing object instances visible to the entire JPublish citizens: Actions, Pages, Modules, Components, etc.

Save and retrieve objects: `site.setAttribute( "foo", "bar")` or `site.getAttribute( "foo")`