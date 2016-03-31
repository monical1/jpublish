## Internationalization ##

Traditionally JPublish provides several different features which assist in building internationalized web sites. First, all page configuration properities can have a language attribute which indicates the locale for that property. For example, you could include the following in a page configuration:

```
    <page>
        <property name="title">Welcome</property>
        <property name="title" locale="fr">Bonjour</property>
        <property name="title" locale="de">Wilkommen</property>
        <property name="title" locale="de_AT">Gruss Got</property>
    </page>
```

Then, at request time you can specify the current Locale for a page using the Page.setLocale() method. If the locale is set then JPublish will attempt to locate a localized property value when a property is requested. If there is no localized value then JPublish will fall back to using the default value.

JPublish also supports setting alternative encodings based on the request path. Encoding maps can be specified in the jpublish.xml configuraton file as follows:

```
    <character-encoding-map path="some/path">
        <page-encoding>iso-8859-1</page-encoding>
        <template-encoding>iso-8859-1</template-encoding>
        <request-encoding>iso-8859-1</request-encoding>
        <response-encoding>iso-8859-1</response-encoding>
    </character-encoding-map>
```

The path can be any request path and can include the **wildcard. As the example above shows you can specify the following encodings:**

  * page-encoding: The encoding of the content which is used during content loading and parsing.
  * template-encoding: The encoding of the site template.
  * request-encoding: The encoding of request parameters (Servlet API 2.3 required)
  * response-encoding: The encoding which is sent to the web browser.

### Important! ###
For simple sites or where the above solution works, you will not have problems defining your localized values at the page level. However, for heavy duty sites with large amounts of text to be localized, we strongly recommend using the recently added JPublishI18n module. The source and a complete example of using the I18n in JPublish can be found in the [SVN repository](http://jpublish.googlecode.com/svn/trunk/modules/i18n/).

