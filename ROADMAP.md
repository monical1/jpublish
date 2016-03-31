# JPublish ROADMAP #


### Version 4.00 ###
**ORM support**
  * Cayenne (via JPCayenneModule)![http://jpublish.googlecode.com/svn/trunk/images/accept.png](http://jpublish.googlecode.com/svn/trunk/images/accept.png)

**[DWR/Ajax support](http://code.google.com/p/jpublish/wiki/JPublishDWR)**
  * ability to remotely execute JPublish Actions ![http://jpublish.googlecode.com/svn/trunk/images/accept.png](http://jpublish.googlecode.com/svn/trunk/images/accept.png)

**REST support**
  * resource mapping and routing support similar to Rails ![http://jpublish.googlecode.com/svn/trunk/images/accept.png](http://jpublish.googlecode.com/svn/trunk/images/accept.png) [blog](http://weblog.flop.ca/2009/03/18/1237421760000.html)

**Pluggable Authentication Modules**
  * OpenID support
  * add the concept of secured Action, secured Resource (core JPublish)
  * evaluative roles and resource based permission
  * UI admin for user roles, permissions and resource access management
  * pluggable permission evaluator


(x) --Hibernate support via JPHibernate-- we're supporting Cayenne ORM out of the box. Hibernate support can be handled by the user. [Here's an example](http://weblog.flop.ca/2004/06/06/1086553824000.html)


### Version 4.01 ###
**JPublish**
  * break the dependency with the VFS
  * reduce the dependencies with the PostEdenLib
  * add Javolution

**Quartz scheduler**
  * running scheduled jobs; cron-like plugin
  * UI admin for scheduled/running jobs

**Distributed cache support through Terracotta**

**Components and Modules**
  * ...


### Version 4.02 ###
**JPublish**
  * engine
  * sitelet
  * router