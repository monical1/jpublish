# Content Directories #

Content directories contain parsed content which is included in your pages. Most often there will be one piece of content for each page found in the pages directory as well as global content such as menus, headers and footers. Content which is placed in an HTML template should not include the html, head and body tags.


# Details #

Content used in a JPublish web site can be pulled from many different sources including the file system, databases or even other web sites. Content is acquired through a repository. All repositories implement the org.jpublish.Repository interface. JPublish includes four implementations of this interface:

|org.jpublish.filesystem.FileSystemRepository|Default repository which retrieves content from the local file system.|
|:-------------------------------------------|:---------------------------------------------------------------------|
|org.jpublish.filesystem.ExtendedFileSystemRepository|	Extension of the FileSystemRepository which allows actions to be associated with content.|
|org.jpublish.db.DBRepository                |	Repository which pulls content from a database.                      |
|org.jpublish.web.WebRepository              | Repository which pulls content from web sites.                       |

Any JPublish application can include multiple repositories. The empty skeleton application which is distributed with JPublish is already configured with a FileSystemRepository connected to the content directory. This will often be all that is necessary for your content needs. You can place snippets of HTML or other content in the content directory which can then be accessed by your template or from other content.

Any time that content is loaded from a Repository it will be passed through the site's ViewRenderer unless specified otherwise. The default view renderer for JPublish is the Velocity engine from the Apache Jakarta but other view renderers can be used by specifying a different view renderer in the jpublish.xml configuration. JPublish currently supports a raw view which means that content is not parsed as well as the following template engines: Velocity, FreeMarker and WebMacro.

Repositories are accessed programtically through the SiteContext and are also exposed automatically in the view. To access a repository from within a Velocity template you can refer to the repository by name since all repositories are automatically exposed to the view. For example, the basic.html template in the templates directory includes the line:

`$fs_repository.get($page.Path)`

This line of Velocity will first look for an object called fs\_repository which has already been made available to the Velocity engine. Once a reference to fs\_repository is located, the get() method is called passing $page.Path as the value. $page.Path resolves to the current request path. If the requested page is http://www.mysite.com/contact.html then `$page.Path` would equal /contact.html.

If you are using Velocity or FreeMarker as your view renderer you can also use the template-language specific methods for including content. Velocity has two forms: #include and #parse. #include will insert the raw content while #parse will parse the content first. When including content with Velocity you will need to specify exact URIs for the content. For example, to include a file called /includes/header.html which is in the fs\_repository you would need to write the following:

`#include("repository:fs_repository://includes/header.html")`

Or to include parsed content:

`#parse("repository:fs_repository://includes/header.html")`

The format of the URI is: repository:name://path where name is the repository name and path is the path of the content relative to the repository's root.

To include content when using FreeMarker, use either:

`<#include "repository:fs_repository://includes/header.html">` for parsed content or

`<#include "repository:fs_repository://includes/header.html" parsed=false>` for unparsed content.

FreeMarker also supports the use of relative paths in includes. The path will be relative to the URI of the content which contains the include directive.

The Repository interface includes methods which are designed to allow construction of content management facilities easier. The Repository interface includes methods for getting, putting and deleting content as well as methods for making new directories and deleting directories. Finally the Repository interface includes methods for listing all of the files in the Repository. Implementations are not required to implement any of the content management methods aside from the get() method.

More details on view renderers can be found in the View Renderers section later in this guide.