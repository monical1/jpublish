# JPublish Components #

JPublish includes a component architecture which provides a framework for creating reusable JPublish components. A JPublish component is a collection of actions and views which can be placed directly in content.

The difference between a [JPublishModule](http://code.google.com/p/jpublish/wiki/Modules) and [JPublishComponent](http://code.google.com/p/jpublish/wiki/Components) is that a module is one or more actions which provide a particular behavior, such as user authentication, database access, etc.  Modules have no concept of presentation - presentation is left up entirely to the web application.  Components on the other hand include zero or more actions as well as views of the results of said actions.

Components must either implement the org.jpublish.JPublishComponent interface or extend from the `org.jpublish.component.AbstractComponent` abstract base class. Components should provide a name and description of the component for future use. Components which extend from the AbstractComponent base class need only implement the render() method and they can actually delegate responsibility of rendering to a component-specific view renderer.

Components are defined in the jpublish.xml configuration as children of a component manager. JPublish currently supports only a single component manager but that component manager can have as many child components as necessary. The configuration would appear like this:
```
    <component-manager>
        <components>
            <component id="component_id" classname="com.mycompany.MyComponent">
            <repository name="repository_id"/>
            </component>
        </components>
    </component-manager>
```
The repository name can be set to any repository which is defined in the jpublish.xml configuration file. If the component extends from AbstractComponent and delegates rendering to the `AbstractComponent.renderView()` method then the content will be pulled from the specified repository. This allows a component to provide logic and a basic view but at the same time allows individual sites the ability to customize component views as necessary.

**Available Components by category**

## View, rendering, formatting support ##
  * [JPTextile](http://jpublish.googlecode.com/svn/trunk/components/jptextile/) - [Textile syntax](http://en.wikipedia.org/wiki/Textile_(markup_language)) and rendering support. With this component you can easily format and render textile content in JPublish views.
  * [JPTagCloud](http://jpublish.googlecode.com/svn/trunk/components/tagCloud/) - This is a JPublish component which displays a Tag Cloud. A [Live demo](http://tagcloud.flop.ca/) and the associated [README file](http://jpublish.googlecode.com/svn/trunk/components/tagCloud/README.txt).