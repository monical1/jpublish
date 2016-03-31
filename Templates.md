# Templates #

Templates are the files which are used by graphic designers to provide the visual "shell" for your web site.

Most sites will only have one template which is applied to all pages within the web site. By default this file is located in the **templates** directory and is called **basic.html**. You could also provide default site-wide templates for other types of content, WML for example, by putting an appropriate template file in the templates example. In the case of WML you could call it basic.wml and then all requests which end with .wml would use that primary template.

Templates use the same template language as content for including dynamic content. Velocity is used by default as the template language, but the template engine can be changed to other supported template engines such as FreeMarker or WebMacro. Designers have access to the same context elements as content authors.

## DETAILS ##

As described before, templates are used by designers to provide the appearance of your web site. Thanks to JPublish's template system, designers can work on a single template which can be applied to the whole site.  Changes to the template will be reflected throughout the site without having to go through the tedious process of updating every single page. Additionally, because the template wraps the page, as opposed to the page including parts of the template, template designers can be assured that their designs will remain intact throughout the site.

Typically templates will be placed in the templates directory which is found in the web application's root directory. You have many templates in this directory and you can even refer to templates which are in nested subdirectories. The template directory can be changed by specifying the template root in the jpublish.xml configuration file using the format `<template-root>path</template-root>`.

Template names follow the format of name.extension. JPublish specifies a default template with the name basic which is used on pages where no template is specified. For every named template you will have one version for each request type where the request type is indicated by the extension. Thus, the default template for HTML content would be found in templates/basic.html whereas a template for WML could be called templates/basic.wml. JPublish automtically determines the appropriate template type at request-time based on the request path.

The default template name can be changed globally in the jpublish.xml configuration file by including the XML element:

`<default-template>template_name</default-template>`

Replacing template\_name with the name of your default template. The template is specified as a path relative to the templates root directory and must not include a file suffix (such as .html).

You can also override the global template on a page-by-page basis by inserting the following element in the page configuration for the specific page:

`<template>template_name</template>`

Replacing template\_name with the name of the template. Like the default-template setting in the jpublish.xml configuration, your template can be specified as a path relative to the templates root directory and must not include a file suffix.

All templates are loaded through a TemplateManager. JPublish includes a default TemplateManager implementation which retrieves templates from the local file system. This file system template manager includes a simple cache which will store your templates in memory only reloading them when they are modified. This caching helps improve response time by reducing template loading.

While JPublish comes with a default template manager implementation you are also free to create your own template manager implementations by implementing the org.jpublish.TemplateManager interface. Since templates are used on every request and there is usually a very small number of templates in a web site it is a good idea to cache Template objects to improve performance. The `org.jpublish.template.TemplateCacheEntry `provides a simple wrapper for caching template objects with their last modified time. Review the source code in `org.jpublish.template.filesystem.FileSystemTemplateManager` for an example.

As a convenience you can also extend from the `org.jpublish.template.AbstractTemplateManager` class. This class provides some basic functionality shared by all template managers as well as a default no-op implementation of the `loadConfiguration()` method.

Templates can also have actions associated with them. This is done by having a template configuration file in the same directory as the template file with the template name and the extension .xml. Therefore, for the template basic.html the template configuration file would be basic.xml. The following is an example of a template configuration file:


The TemplateManager interface includes methods which are designed to allow construction of content management facilities easier. The TemplateManager interface includes methods for getting, putting and deleting content as well as methods for making new directories and deleting directories. Finally the TemplateManager interface includes methods for listing all of the files in the TemplateManager. Implementations are not required to implement any of the content management methods aside from the getTemplate() method.