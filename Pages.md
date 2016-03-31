# Pages #

Pages provide meta data about particular requested page paths. When a request is received for repository content the JPublish servlet will first attempt to find a page object for that request path. By default if no page object is found then JPublish will return a 404 error (page not found).

The page manager system of JPublish is completely pluggable. The default PageManager is the FileSystemPageManager. This page manager obtains a Page object by locating an XML configuration file in the local file system.

The PageManager does not actually return a Page object, rather it returns a PageInstance object. There can only be one PageInstance object for each page in the site. When a request is received a Page objects is created which wraps the PageInstance object. There is one Page object per request. When property values are requested the Page property map is queried first. If the value has not been set then the PageInstance's property map is queried. Properties can be altered in the Page object and this will not affect the PageInstance.

Each page definition can contain a variety of meta data. Page designers can add properties to the page definition which are accessible from within the page using the page.getProperty(name) or page.getProperty(name, locale) methods. Page properties can be localized using a locale attribute which follows the java.util.Locale method of naming.

The following is an example of properties in a page definition:
```
  <property name="title">Page Title</property>
  <property name="title" locale="fr">Titre de Page</property>
```

While the above i18n solution is a handy one, we encourage you to look at our [i18n module](http://jpublish.googlecode.com/svn/trunk/modules/i18n/) which integrates the localization support of [Spring](http://www.springframework.org/) with JPublish. This is the preferred solution for any serious JPublish web site.

In addition to page properties the page definition can also specify the template to use when rendering the page. This allows pages to have a custom template which is different from the default site template:

```
  <template>mytemplate</template>
```

Another important element of page definitions is the ability to define page actions. Page actions are actions which are executed every time the page is requested. Page actions have access to all objects which are generally available to actions. The following is an example of a Beanshell page action:

```
  <page-action name="MyAction.bsh"/>
```

or of a Java page action:
```
  <page-action name="ca.flop.jpublish.SomeAction"/>
```