# Introduction #

JPublish is a powerful web publishing system designed to ensure a clean separation of developer roles. JPublish includes support for multiple templating engines, including Jakarta Apache's Velocity, FreeMarker and WebMacro. JPublish supports numerous scripting languages including Python, BeanShell, Groovy, recently added JRuby<sub>experimental</sub> and JavaScript. JPublish is modular and provides support for easy extensibility.


# Installation #

JPublish requires JDK 1.4 or higher and a servlet engine which is compatible with version 2.2 or higher of the Servlet Specification. JPublish has been tested with Jetty, Tomcat, Orion and JBoss.

To install JPublish you need to bind the JPublishServlet class as a servlet within the servlet engine and to configure your web site paths.

JPublish includes a "Hello World" ([download .zip](http://jpublish.googlecode.com/files/hello.war.zip)) sample site which will help you become familiar with how JPublish sites are organized. To install the Hello World application follow these simple steps:
  * checkout JPublish from our [SVN code repository](http://jpublish.googlecode.com/svn/trunk/)
  * go to the root of the JPublish code
  * run: ANT samples. A new folder "build/samples/hello.war" will be created
  * copy the hello.war to your Jetty or Tomcat webapps directory
  * start your server and point your browser to http://localhost:8080/hello
  * you're using now a JPublish application :)

To build an empty JPublish web application go to the folder where you have downloaded/checkout or exported the JPublish SVN code and use ant: **$ ant empty-web-app**

An empty JPublish web application containing all the required libraries and a very basic site structure will be created in: **build/samples/empty.war**

Copy/move/rename the empty.war and use it in your Servlet container of choice.

Few hints for the impatient :)
```
1. To modify the home page layout, edit the file: 
	empty.war/templates/basic.html
	
2. To modify the home page contents, edit the file: 
	empty.war/content/index.html

3. The simple CSS file is situated here: empty.war/public/styles/simple.css

4. To add and use a scripting action:
 - create a new Beanshell (bsh) or a Rhino(js) Action script in the empty.war/actions folder
 - use the new Action in the page you need by declaring the new Action in the page
   specific xml file: 
     <page-action name="the newly created action"/> 
   Example:
     if you created a new Action: TestAction.bsh, and you want to use this action
     in a page called content/test.html, then modify the page descriptor: pages/test.xml
     and add the action. Your pages/test.xml file should look like this:

        <?xml version="1.0" encoding="UTF-8"?>
        <page>
          <template>basic</template>
          <page-action name="TestAction.bsh"/>
        </page>
```

Later we'll show you how to use Actions to control your site logic, how localization works with JPublish and which is our preferred i18n solution (yes there are many) and at the end we'll teach you how to extend the JPublish functionality by using Modules and Components. With Modules, for example, you'll be able to dive into the wonderful world of Web2.0 and use DWR for AJAX and other goodies :)

# JPublish Basics #
Accessing a web page through JPublish is exactly the same as any static web content (even though it is not static.) In the background JPublish will actually do some work to locate the correct resources and template for the given page type (i.e. HTML, WML, etc.) and language based on the requested document's name as well as HTTP headers.

Each page is a mixture of several items. First is the page definition which is XML. Your XML definitions are located in the site-root/pages directory. The directory structure of this hierarchy is an exact representation of what is available through your web site. The next component is the template which is located in the site-root/templates directory. Each page can define its default template that it will use. The final component is the content which is located in the site-root/content directory. This content is accessed through the FileSystemRepository class.

**Page Definition**

Each web page is defined in an XML document located in the site-root/pages. It is easiest to take an example: suppose that a visitor to your site requests the page /dogs.html. JPublish will look for the dogs.xml file in the pages directory for the page definition. If the same visitor requested /development/java/stuff.html then JPublish would look for the file /development/java/stuff.xml in the pages directory. As you can see this structure is exactly the same as the structure which visitors to your site will see.

**Templates**

JPublish uses by default the Velocity template engine from the Apache group for its templating language. Velocity documentation can be found at http://jakarta.apache.org/velocity. Each page has a default template which is used to render the page. Changes made to the template will be reflected throughout your web site.
Content. Alternatively you can replace Velocity with StringTemplate, FreeMarker or WebMacro, we'll show you later how to do that.

Accessing content in JPublish is done through **repositories**. Repositories are registered at startup time through the config.xml file. Since each repository is registered with a name accessing the repository from a template is as simple as $name.get(content) where name is the repository name and content is a path to the content you are loading.

There are two typical use-cases for loading content:
  1. Global content such as copyright information, navigation components, etc. Example: fs\_repository.get("global/copyright.html").
  1. Request specific content. Example: fs\_repository.get($page.Path).

Another interesting use is to define a repository for accessing properties files. Since the SiteContext is available in your action scripts you can access properties in a resource-location independent fashion.

Caching is very important for a web application and JPublish is not neglecting this fact. Repositories are using a modular cache solution: EHCache or WhirlyCache, and they can share the same cache space or use a per-repository cache space.

**Static Elements**

JPublish also supports static elements such as images and other media files. JPublish will first look for any file requested in the static directory before looking in the page definitions.

**Actions**

The JPublish framework defines the Action interface as a way of invoking custom actions in response to form data. There are two ways to provide actions:

  1. Implement the Action interface.
  1. Implement your action in a scripting language supported by the Bean Scripting Framework (such as BeanShell) and include the script file in the actions directory.

Actions can be invoked at several different locations. JPublish currently supports 6 different locations for invoking actions:

  * Startup - Actions registered in the configuration file as startup-actions will be executed when the Servlet is first loaded (which should be when the servlet engine starts.) These actions will be executed in a minimum context from where the developer can get access to few JPublish core objects such as: 'site', 'syslog', etc. The syntax is:
```
          <startup-action name="script|name"/>
```
  * Shutdown - Actions registered in the configuration file as shutdown-actions will be executed when the Servlet is shutting down. These actions will be executed in a minimum context from where the developer can get access to few JPublish core objects such as: 'site', 'syslog', etc. The syntax is:
```
          <shutdown-action name="script|name"/>
```

  * Global - Actions registered in the configuration file as global-actions will be executed for every request. Actions defined here can be scripts or Java classes. The syntax is:
```
          <global-action name="script|name"/>
```
  * Path - Executed whenever a particular path is matched. The path can be a path to a file or a directory. The syntax is:
```
          <path-action path="path" name="script|name"/>
```
  * Page - Actions can be bound to a specific page. The definition for these types of actions occurs within the page's definition document.
  * Post and Pre evaluation - _todo_

In order for script actions to function the scripting language's JAR files must be included in the WEB-INF/lib directory in your web application.

Actions can issue cause page redirects by inserting the attribute "redirect" in the Page context. Redirects can be to a JPublish page or a URL. To issue a redirect to a JPublish page the value of "redirect" should be the path to the page without a suffix. The current page's suffix will be appended to the value of the redirect attribute. Values ending with a suffix or a slash will be considered URLs. The reasoning behind this is that a script doesn't need to know what type of page was requested (i.e. HTML, WML, etc.) rather it uses the current pages type be default.

**Logging**

JPublish uses log4j as its logging engine. Logging can be configured by placing a Log4J properties file called log.properties in the WEB-INF/classes directory of the application context. The example application which comes with JPublish includes a basic log configuration file.

JPublish exposes an object called **syslog** in the Page context and in the scripting engine. Script and page authors can send log messages to this logging stream. Actions which are written as Java classes can also access this log stream through org.jpublish.util.LogSystem.syslog.


# Disclaimer #
```
 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY DIRECT, 
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING 
 IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.
```
