# What Is a Module? #

JPublish is extended through modules. Modules must implement the `org.jpublish.JPublishModule` interface. Modules do not actually handle every request which is passed to the JPublish system, rather it exposes actions which can be used wherever JPublish actions can be used.

**Writing Modules**

JPublish modules consist of a class which implements the `org.jpublish.JPublishModule` interface as well as one or more classes which implement the Action interface. Modules can be thought of as a collection of actions which are related. To deploy a module just include the module with its actions in the classpath. The easiest way to do this is to include everything in a single JAR file.


**Available modules by category**

## AJAX ##
JPublish is using the Module concept for integrating with the [DWR](http://getahead.org/dwr) framework. Follow the following links for more details:
  * [JPublishDWR](JPublishDWR.md) - the documentation
  * [JPublishDWR](http://jpublish.googlecode.com/svn/trunk/modules/dwr/) - the source
  * [news](http://weblog.flop.ca/tags/dwr/)


## Localization, I18n, etc. ##
  * [JPublishI18n](http://jpublish.googlecode.com/svn/trunk/modules/i18n/) - This is a Spring based JPublish - i18n solution.


## [ORM](http://code.google.com/p/jpublish/wiki/OrmSupport) ##
  * Cayenne support **available now**
  * Hibernate support _in progress_