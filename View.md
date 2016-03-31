# View Renderers #

JPublish provides a means for plugging in a variety of view renderers. This flexible system allows your organization to use the view renderer of its choice. By default JPublish currently uses the Velocity template language to render views. You can change the view renderer by adding:

> `<view-renderer classname="package.ClassName"></view-renderer> `

to the jpublish.xml configuration file. The body of the view-renderer element can contain XML configuration information which is passed to the view renderer when it is loaded.

### [Velocity](http://velocity.apache.org/engine/devel/index.html) ###

The Velocity view renderer uses the Velocity template engine from the Jakarta project.

Velocity provides a means for having global macros which are defined together in a single file and which can then be used throughout your application. JPublish requires that this file, if it is used, be called VM\_global\_library.vm and should be placed in the WEB-INF/classes directory.

Velocity is the default JPublish view renderer, and here is a jpublish.xml fragment with a more detailed Velocity configuration:
```
    <!--Velocity renderer-->
    <view-renderer classname="org.jpublish.view.velocity.VelocityViewRenderer">
        <velocity-properties relative-paths="true">WEB-INF/velocity.properties</velocity-properties>
        <resource-cache-enabled>true</resource-cache-enabled>
        <!--seconds-->
        <resource-cache-interval>15</resource-cache-interval>
    </view-renderer>
```

If you want to learn more about Velocity you can visit the following links:

[APACHE Velocity](http://velocity.apache.org/engine/devel/index.html)

[Velocity 1.5 User Guide](http://jpublish.googlecode.com/files/VelocityUsersGuide.pdf)




### [StringTemplate](http://www.stringtemplate.org/) ###
The StringTemplate view renderer is using the StringTemplate engine. To activate this ViewRenderer, modify your jpublish.xml as follows:
```
<!--StringTemplate Viewer-->
<view-renderer classname="org.jpublish.view.stringtemplate.StringTemplateViewRenderer">
    <!--<lexer class="org.antlr.stringtemplate.language.AngleBracketTemplateLexer"/>-->
    <lexer class="org.antlr.stringtemplate.language.DefaultTemplateLexer"/>
    <!-- todo: <error-listener class=""/>-->
    <refresh-interval>15</refresh-interval>
</view-renderer>
```

and make sure to configure JPublish with the default index file as **index.st**. You only need to declare the default (welcome) page:
```
<default-page>index.st</default-page>
```
in your jpublish.xml config file.

Also, specify the mime type for the new resource type:
```
<mime-mapping ext="st" mimetype="text/html"/>
```

For learning more about StringTemplate, follow this link: [StringTemplate documentation](http://www.antlr.org/wiki/x/zwQ).

### FreeMarker ###

The FreeMarker view renderer uses the FreeMarker template engine for rendering views. The FreeMarker engine provides a larger set of features when compared to Velocity and therefore may be a desirable alternative to Velocity.

To enable FreeMarker in a JPublish application, add the following configuration in your jpublish.xml file:
```
    <!--Freemarker renderer-->
    <view-renderer classname="org.jpublish.view.freemarker.FreeMarkerViewRenderer"/>
```


### WebMacro ###

The WebMacro view renderer uses the WebMacro template engine.
```
    <view-renderer classname="org.jpublish.view.webmacro.WebMacroViewRenderer"/>
```

The WebMacro view renderer does not currently take advantage of WebMacro's caching system and thus may not match the performance of the Velocity or FreeMarker implementations. The WebMacro implementation should be considered experimental at this point. If you are interested in providing a more robust and complete implementation of this view renderer please join the JPublish user mailing list.


### Building Your Own ###

To build your own view renderer you must implement the `org.jpublish.view.ViewRenderer interface`. This interface has 5 methods which must be implemented. The setSiteContext() method should store the current SiteContext in an internal field so that other methods of the renderer can make use of the SiteContext's methods if necessary. The loadConfiguration() method is called on startup. The Configuration object which is passed as a parameter contains an object representation of the XML tree rooted at the view renderer declaration in the jpublish.xml configuration file. Immediately after the loadConfiguration() method is called the init() method is called. The init() method can be used to initialize the rendering engine if necessary and has access to the SiteContext object as well as the Configuration object which was passed during the call to loadConfiguration(). The render() method is called each time a piece of content is rendered. There are two versions of this method, one which takes an InputStream and one which takes a Writer.

```
Note: ViewRenderer implementations should strive to make use of caching mechanisms
      provided by their respective template engines. This will avoid unnecessary parsing.
```







