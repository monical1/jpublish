![http://jpublish.googlecode.com/svn/trunk/images/jpublish-title.png](http://jpublish.googlecode.com/svn/trunk/images/jpublish-title.png)

**JPublish** is a Java web publishing framework which merges the [Velocity](http://velocity.apache.org/), [FreeMarker](http://freemarker.sourceforge.net/), [StringTemplate](http://www.stringtemplate.org/) or [WebMacro](http://www.webmacro.org/) templates with a content repository and application control framework. JPublish is designed to ensure a clean separation of developer roles.

JPublish is loosely based on the `FreeEnergy` methodology. `FreeEnergy` was originally created by Leon Atkinson and others as described in his self-authored article [Harnessing PHP's FreeEnergy](http://www.leonatkinson.com/index.php/writing/). Essentially the `FreeEnergy` methodology was designed to handle page development in an object oriented fashion so that common objects could be reused. Much of JPublish's initial development ideas were obtained while documenting J.J. Berhens implementation of `FreeEnergy` in Python, called [Aquarium](http://aquarium.sourceforge.net/).

This project is a continuation of the design and [work](http://sourceforge.net/projects/jpublish) started by [Anthony Eden](http://anthonyeden.com/) few years ago.

Like all good web frameworks, JPublish provides a clean separation of code, content, and layout. JPublish has the following features:

**Flexible actions** - actions which are programmatic elements which can be attached globally to a site, to a path (with wildcards) or to a page. Actions can be written in Java or any Bean Scripting Framework supported language (such as [JavaScript(Rhino)](http://www.mozilla.org/rhino/), [Python(Jython)](http://www.jython.org/), [BeanShell](http://www.beanshell.org/), [Groovy](http://groovy.codehaus.org/) or [JRuby](http://jruby.codehaus.org/).)

**Templates for layout** - the Velocity template engine from the Apache group has been chosen as JPublish's default template engine because of its straightforward syntax as well as its solid and simple design.

**Repository abstraction layer** - JPublish provides a method for binding concrete repository implementations to a site for easy and controlled access to content.

**Content which can include nested content** - content pulled from repositories can be parsed by the Template engine in order to include nested dynamic elements.

**Automatically reloading configuration** - a centralized configuration file which will automatically reload when modified makes it easy to make changes at runtime without restarting the web server.

**Search-engine friendly URLs** - JPublish URLs look just like static URLs, even though they are not.

**Flexible character encoding** - flexible character encoding control – character encoding can be specified for both input and output for specified paths.

**Pluggable page manager** - JPublish page definitions, which define page properties such as the title and page actions, can be loaded from any class which implements the PageManager interface. Current implementation include two: a file-based system which map one XML file to one page definition and one which pulls all page definitions from a single XML file.

**Custom error handling** - multiple custom error handlers can be assigned globally or by path.

**Utilities** - JPublish includes several utility classes which can be used to build URLs, format dates and numbers, and perform other common web tasks.


JPublish can be easily extended through **Modules** or **Components**. Examples are provided to demonstrate the smooth integration with Spring, Lucene, Hibernate, AJAX (through DWR), etc.

Welcome to the world of JPublish and ... **Develop with Fun**!

_The JPublish Team_


`______`

### Tools that make our development easier: ###
| JProfiler, an award-winning all-in-one Java profiler | Database developer's complete Integrated Development Environment (IDE)|
|:-----------------------------------------------------|:----------------------------------------------------------------------|
| <a href='http://www.ej-technologies.com/products/jprofiler/overview.html'><img src='http://static-aws.ej-technologies.com/uYo38cFIuffMtIwyum08rl2ZmePw4Evxi2iAgE7AtKB.png' width='188' height='31' /></a> | [![](http://www.aquafold.com/images/s_aquadatastudio_185x78.gif)](http://www.aquafold.com/)|
| [JProfiler](http://www.ej-technologies.com/products/jprofiler/overview.html) is an award-winning all-in-one Java profiler.<br /> JProfiler's intuitive GUI helps you find performance bottlenecks, pin down memory leaks and resolve threading issues.<br />JProfiler has donated a license for use on JPublish project | [AquaFold](http://www.aquafold.com) has donated an Aqua Data Studio license for use on opensource JPublish project|



## Credits & Inspirations ##
  * Some of the JPublish Wiki pages are using [Silk Icons](http://www.famfamfam.com/lab/icons/silk/) created by Mark James