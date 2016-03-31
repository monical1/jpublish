# Configuration #

JPublish's primary configuration is contained in a single configuration file typically called jpublish.xml residing in the WEB-INF directory. The JPublish configuration file is an XML document which contains information about repositories, cache managers, error handlers, actions and more. JPublish attempts to provide reasonable defaults for most of the configuration options so you only have to provide configuration elements where you need to change the default behavior.

JPublish also requires several entries in the web.xml file, also located in the WEB-INF directory. The JPublishServlet class must be registered as a servlet and paths must be overriden so that JPublish will handle all requests. Additionally, you can change the location of the jpublish.xml file in the web.xml configuration although this is not recommended.

Example of a **WEB-INF/jpublish.xml** file where Velocity is the [ViewRenderer](View.md):

```
<?xml version="1.0" encoding="UTF-8"?>

<jpublish>

    <name>Hello World; demo application</name>

    <static-root>public</static-root>
    <action-root>actions</action-root>
    <template-root>templates</template-root>
    <page-root>pages</page-root>

    <page-manager classname="org.jpublish.page.filesystem.FileSystemPageManagerWithDefault"/>
    <default-template>basic</default-template>

    <!--Velocity renderer-->
    <view-renderer classname="org.jpublish.view.velocity.VelocityViewRenderer">
        <velocity-properties relative-paths="true">WEB-INF/velocity.properties</velocity-properties>
        <resource-cache-enabled>true</resource-cache-enabled>
        <!--seconds-->
        <resource-cache-interval>15</resource-cache-interval>
    </view-renderer>

    <!--Comment the Velocity renderer above and un-comment the line below if you need to enable the FreeMarker-->
    <!--<view-renderer classname="org.jpublish.view.freemarker.FreeMarkerViewRenderer"/>-->

    <repository name="fs_repository" classname="org.jpublish.repository.filesystem.FileSystemRepository">
        <root>content</root>
        <cache>default</cache>
    </repository>

    <cache-manager>
        <jpublish.cache.provider>
            <!--<implementation name="default" class="org.jpublish.util.JPublishSimpleCacheImpl"/>-->
            <!--<implementation name="default" class="org.jpublish.util.JPublishWhirlyCacheImpl"/>-->
            <implementation name="default" class="org.jpublish.util.JPublishCacheImpl"/>
            <!--<flushInterval minutes="5"/>-->
        </jpublish.cache.provider>
    </cache-manager>

    <!--<path-dispatcher action="forward" name="jsp" path="*.jsp"/>-->

    <character-encoding-map path="*">
        <page-encoding>utf8</page-encoding>
        <template-encoding>utf8</template-encoding>
        <request-encoding>utf8</request-encoding>
        <response-encoding>utf8</response-encoding>
    </character-encoding-map>

    <debug>false</debug>
</jpublish>

```


And the associated **web.xml** file in the **WEB-INF** folder:
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd">

<web-app>

    <servlet>
        <servlet-name>jpublish</servlet-name>
        <servlet-class>org.jpublish.servlet.JPublishServlet</servlet-class>

        <init-param>
            <param-name>config</param-name>
            <param-value>WEB-INF/jpublish.xml</param-value>
        </init-param>

        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>jpublish</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <servlet-mapping>
        <servlet-name>jpublish</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

</web-app>
```


