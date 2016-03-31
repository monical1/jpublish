## JPublish Directory Structure ##

JPublish stores files in several different directories. The default structure of a  JPublish site is:

  * actions
  * content
  * pages
  * static
  * templates
  * WEB-INF
    * lib

> This directory structure is designed to reflect the way in which development groups are typically organized. Content is held in one location so it easy for content developers to work with while templates are contained in another directory for template designers, and so on.

All directories can can have nested sub directories and files.

## Actions ##

The actions folder contains scripts which are executed at runtime. Actions can be executed when the server is started, when a particular page is requested or when a path is matched (with wildcards). Actions in this directory can be written in any scripting language which supports the Bean Scripting Framework.

Actions should be used as the glue between the presentation layer and the business object layer. Actions will typically execute business methods and place the result objects in the Velocity context to be rendered on the current page. Actions may also choose to redirect to a different URL.

## Pages ##

The pages folder contains XML documents which provide configuration information for a single web page. This may include defining actions which should be executed when the page is requested or specifying page properties. Since the JPublish page manager is pluggable this directory may or may not be used.


## Templates ##

The templates folder contains templates which will be rendered at runtime using specific content. Templates usually contain common site elements such as headers, footers and navigational elements. Templates are merged with content using Velocity and so can contain any Velocity code.


## Static ##

The static folder contains static data such as images, video and audio which should be returned in raw format without Velocity merging. Any file in this folder will be served to the user **as is**. _In the near future this folder will be named **public**_


## Content ##

The content folder contains actual HTML snippets which are used during page rendering. The template specifies what content should be rendered and JPublish maps the requested content name to an actual content object. Content may be standard across many pages or may be content for a single page. Since the JPublish repository system is pluggable this directory may or may not be used.

Content is not limited to files in the filesystem. Any class which implements the org.jpublish.Respository interface can be used to load content. JPublish currently includes a file system repository and a database repository. Repositories can be specified in the jpublish.xml file.


## WEB-INF ##

The WEB-INF directory contains all of the JPublish configuration files as well as files required by the servlet engine.

Note that except for the WEB-INF directory all of the other directories can be specified in the JPublish configuration file using relative or full paths.

## Examples ##
**1.**A JPublish web application where the Developer defines the folders described earlier inside the WEB-INF folder, leaving only the public (static) folder in the root of the web application:

  * public
  * WEB-INF
    * lib
    * templates
    * actions
    * content
    * pages

For the directory structure above, your JPublish configuration file (WEB-INF/jpublish.xml) will contain the following relevant details:
_snapshot_

```
    <static-root>public</static-root>
    <action-root>WEB-INF/actions</action-root>
    <template-root>WEB-INF/templates</template-root>
    <page-root>WEB-INF/pages</page-root>
    ...
    <repository name="fs_repository" classname="org.jpublish.repository.filesystem.FileSystemRepository">
        <root>WEB-INF/content</root>
    </repository>

```


**2.**Obviously you can use fully qualified folder addresses:
```
    <static-root>/Users/flop/Public/Dropbox</static-root>
    <action-root>WEB-INF/actions</action-root>
....
```
With the configuration above, JPublish will serve public content from: **/Users/flop/Public/Dropbox**