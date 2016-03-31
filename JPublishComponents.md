# Components #
These notes are for developers only, specifically those who are trying to correlate the behavior of the source code with the way component tags appear in HTML and configuration files.

The difference between a JPublishModule and JPublishComponent is that a module is one or more actions which provide a particular behavior, such as user authentication, database access, etc.  Modules have no concept of presentation - presentation is left up entirely to the web application.  Components on the other hand include zero or more actions as well as views of the results of said actions.


This first example demonstrates a simple content component.

HTML:
```
<component id="content">This is dummy content which will be replaced by real content by JPublish.</component>
```

jpublish.xml:
```
<component id="content" classname="org.jpublish.component.ContentComponent">
    <repository>fs_repository</repository>
</component>
```

For a complete Component example, check the components folder in our source repository.

For a live demo of one of our most used components, the Tag Cloud, you may also be interested in reading this small blog [article](http://weblog.flop.ca/2006/10/21/1161474590401.html).

[ComponentsAvailable](ComponentsAvailable.md)
