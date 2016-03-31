#JPublish RESTLET frameworkintegration.

# RESTful services (actions and/or pages) #

I am pleased to let you know about the availability of a very simple yet flexible integration between JPublish and the [RESTLET framework](http://www.restlet.org)

The svn Rev230, contains a JPublish module that is able to transform REST requests, such as...

```
    /hello/{name}
    /user/{name}/{id}
    /user/{name}/order/{id}
    /search/{keyword}
    /info/{id}
    ...
```

... to JPublish action calls and page rendering.

For your convenience, a simple demo is provided, available here:
http://jpublish.googlecode.com/files/jprestlet.war.zip

To start, build from svn or download the demo above. If you check your jpublish.xml file, you will notice the new module, JPRestlet.
```
    <module classname="org.jpublish.module.restlet.JPRestletModule">
            <url>/rest/*</url>
            <restlet-config>/WEB-INF/jprestlet-config.xml</restlet-config>
            <profilling>true</profilling>
            <verbose>on</verbose>
            <debug>true</debug>
    </module>
```

The configuration above will initialize the RESTLET support for all the requests on the path described by the `<url/>` tag: `"rest/*"` in this case.

The RESTLET converter will be initialized from the **/WEB-INF/jprestlet-config.xml** file. In the jprestlet-config.xml, you can define your resource mappings and a dedicated JPublish content repository: "extended", in this example. The JPublish repositories are defined in the jpublish.xml, but you should already know that.

In our example, we are supporting the following routes:
```
    <jprestlet>
            <repository name="extended"/>
            <routes>
               <route map="/hello/{name}" action="rest/Hello.bsh"/>
               <route map="/user/{name}/{id}" page="user.html"/>
               <route map="/action/info/{id}" action="rest/Info.bsh" page="info.html"/>
               <route map="/info/{id}" page="info.html"/>
            </routes>
           <debug>true</debug>
    </jprestlet>
```

With the mappings declared above, a call like:
```
    http://localhost:8080/jprestlet/rest/hello/JPublish
```

will echo back to you the text: Hello JPublish!

This is enough to get you started if you are interested in learning more about RESTful services. Also, if you don't want to use JPublish but are a Java developer that wants to develop RESTful services, please visit the RESTLET framework site. It's an amazing framework.

If you like the RESTful world, then please stay tuned. There is more to come.

([original article](http://weblog.flop.ca/2009/03/18/1237421760000.html))

Have fun!

# build from source #
[JPRestlet source](http://code.google.com/p/jpublish/source/browse/trunk/modules/jprestlet/)