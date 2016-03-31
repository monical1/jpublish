## Building your own View renderer ##

To build your own view renderer you must implement the `org.jpublish.view.ViewRenderer` interface.

This interface has 5 methods which must be implemented. The setSiteContext() method should store the current SiteContext in an internal field so that other methods of the renderer can make use of the SiteContext's methods if necessary.

The loadConfiguration() method is called on startup. The Configuration object which is passed as a parameter contains an object representation of the XML tree rooted at the view renderer declaration in the jpublish.xml configuration file.

Immediately after the loadConfiguration() method is called the init() method is called. The init() method can be used to initialize the renderering engine if necessary and has access to the SiteContext object as well as the Configuration object which was passed during the call to loadConfiguration().

The render() method is called each time a piece of content is rendered. There are two versions of this method, one which takes an InputStream and one which takes a Writer.

**Note:** ViewRenderer implementations should strive to make use of caching mechanisms provided by template engines so as to avoid extra unnecessary template parsing.