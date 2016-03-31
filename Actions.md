# Actions #
Actions provide a way of exposing business objects to the view layer of JPublish. Actions can be authored in a variety of languages including Java (compiled), BeanShell (Java-syntax scripting language), JavaScript, Jython and any other scripting language supported by Apache's Bean Scripting Framework.

Actions can be associated with a particular page, can be executed for every request, can be executed when a particular path is matched, can be executed on startup or shutdown, or can be executed pre- or post- processing of the HTTP request.

|Type| 	XML Tag| 	Location| 	Description|
|:---|:--------|:---------|:------------|
|Global Action| `	<global-action name="ActionName"></global-action>` |	jpublish.xml |	Global actions are executed on every request unless preempted by a pre-evaluation action.|
|Path Action |	`<path-action name="ActionName" path="/path"></path-action>` |	jpublish.xml |	Path actions are executed when the specified path is matched. The path is relative to the web application context root and can include the '**' wildcard.**|
|Page Action |	`<page-action name="ActionName"/>` |	Page Definition |	Page actions are executed when a particular page is requested.|
|Template Action |	`<template-action name="ActionName"></template-action> `|	Template Definition |	Template actions are executed when a particular template is rendered.|
|Parameter Action| 	N/A    |	Request Parameter |	Parameter actions are executed when an action name is sent via a specific HTTP parameter. Parameter actions are disabled by default and should be considered a potential security problem. The action parameter name can be specified in the jpublish.xml configuration file, and defaults to action.|
|Startup Action |	`<startup-action name="ActionName"></startup-action>` |	jpublish.xml |	Startup actions are executed when the JPublish web application is first loaded.|
|Shutdown Action |	`<shutdown-action name="ActionName"></shutdown-action> `|	jpublish.xml |	Shutdown actions are executed when the JPublish web application is stopped.|
|Pre-evaluation Action |	`<pre-evaluation-action name="ActionName" path="/path"></pre-evaluation-action> `|	jpublish.xml| 	Pre-evaluation actions occur when the specified path matches and will be executed before any other actions for that request.|
|Post-evaluation Action| 	`<post-evaluation-action name="ActionName" path="/path"></post-evaluation-action> `|	jpublish.xml |	Post-evaluation actions occur when the specified path matches and will be executed after any other actions for that request and after the output stream is already closed.|

Java actions may be mapped to a name to make it easier to refer to the action throughout your web application:

> ` <define-action name="ActionName" classname="com.company.action.ActionName"/> `

Actions which are not pre-defined using the 

&lt;define-action&gt;

 element will be located when they are first requested. When an action is requested the ActionManager first searches for defined actions, then it searches for module actions, next it searches for script actions and finally it will attempt to construct a class with the specified action name. If the action is written in Java and is not pre-defined then the fully-qualified action name must be used. If an action cannot be located using the specified name then an ActionNotFoundException is thrown.

There are number of variables which are automatically exposed to script actions:
|Name| 	Class |	Description |	Availability|
|:---|:-------|:------------|:------------|
|syslog |	org.apache.commons.logging.Log |	A log object which can be used by the script to print to a log called syslog. |	Always      |
|site |	org.jpublish.SiteContext |	The SiteContext.| 	Always     |
|request |	javax.servlet.http.HttpServletRequest |	The HTTP servlet request. |	All except for startup, shutdown and pre-evaluation|
|response |	javax.servlet.http.HttpServletResponse |	The HTTP servlet response. |	All except for startup, shutdown and pre-evaluation|
|session |	javax.servlet.http.HttpSession |	The HTTP session object. |	All except for startup, shutdown and pre-evaluation|
|application |	javax.servlet.ServletContext |	The HTTP servlet context. |	Always      |
|page |	org.jpublish.Page |	The Page object. |	All except for startup, shutdown and pre-evaluation|
|configuration| 	com.anthonyeden.lib.config.Configuration |	The Page object. |	All except for startup, shutdown and pre-evaluation|

Developers should note that the performance of JPublish can be severely impacted by the performance of any scripting language which is used for actions since some scripting language implementations perform better than others.

Actions written in Java must implement the `org.jpublish.action.Action` interface which consists of a single method:
```
public String execute(JPublishRequest request, Configuration configuration)
```

The request parameter contains the current request context. Actions can place objects in this context and they will be available to the view layer. The second argument is a Configuration object which is an object representation of the XML contained within the action declaration. For example, if an action is defined in a page configuration as follows:
```
    <page-action name="MyPageAction">
        <sometag foo="bar">Baz</sometag>
    </page-action>
```

Then the configuration parameter would be an object representation of the page-action element as well as all of its children. This provides a means for passing configuration information to the action at runtime.

**Note:** Actions written in Java must implement the `org.jpublish.action.Action` interface.

**Note:** If you are using Jython with JPublish then you can place the registry file, which is a global configuration for Jython, in the same directory as the jython.jar file. This is the easiest way to configure Jython. You can use the registry file to tell Jython where to find the Python Lib directory, an important step if you plan on using pure Python libraries from CPython. Note that there may be problems using libraries from CPython. Please consult the Jython documentation or mailing lists for more information.