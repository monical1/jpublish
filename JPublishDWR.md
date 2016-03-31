![http://groups.google.com/group/jpublish-user/web/jpublish-title.png](http://groups.google.com/group/jpublish-user/web/jpublish-title.png)
![http://getahead.org/images/dwr-logo-200.gif](http://getahead.org/images/dwr-logo-200.gif)

We decided to use the [DWR framework](http://getahead.org/dwr) for offering Direct Web Remoting to any JPublish web application. DWR allows Javascript in a browser to interact with Java on a server and helps you manipulate web pages with the results. With the JPublishDWR support not only that you can use DWR _a la carte_ ;) but you can also invoke any JPublish Actions, such as: BSH, JS, jRuby, Groovy, JPython and obviously compiled Java Actions.

Before starting, be sure that you have the latest JPublish and JPublishDWR builds from our SVN.

# Configuration #
Everything is controlled via a JPublish Module configuration that can be included in the WEB-INF/jpublish.xml configuration file.

### The jpublish.xml file ###
Below is a simple JPublishDWR module configuration:
```
<jpublish>
...
  <module classname="ca.flop.jpublish.dwr.DWRModule">
    <dwr-config-file>/WEB-INF/dwr.xml</dwr-config-file>
    <dwr-path-prefix>/dwr</dwr-path-prefix>

    <dwr-servlet-parameters>
        <activeReverseAjaxEnabled>true</activeReverseAjaxEnabled>
        <initApplicationScopeCreatorsAtStartup>true</initApplicationScopeCreatorsAtStartup>
        <maxWaitAfterWrite>500</maxWaitAfterWrite>
        <debug>true</debug>
    </dwr-servlet-parameters>
  </module>
...
</jpublish>
```

For those of you already familiar with DWR the above configuration will not look strange.

JPublish will look for the dwr.xml configuration file in the path declared by the 

&lt;dwr-config-file&gt;

 node. Then all the web requests mapped on /dwr will be filtered by the JPublishDWR module. Basically JPublish acts like a fake Servlet Container for the DWR Servlet mapping the DWR Servlet on specific JPublish web requests. Very simple but efficient.

The next block, the **dwr-servlet-parameters** will contain anything you want to transmit to the DWR "servlet". Think of the Servlet init-parameters. For more details about the parameters expected by the DWR Servlet please follow the original documentation: [Configuring DWR](http://getahead.org/dwr/server/servlet). JPublish is just passing these parameters to DWR.

_Any changes you're doing to the jpublish.xml will be visible to your application immediately without requiring a restart, useful when you want to play with DWR in debug mode. I highly recommend to set debug to **false** for production!_

### The dwr.xml file ###
As you know from the DWR documentation, the dwr.xml file is used configure DWR.

Here is a simple example taken from our demo web application:
```
<!DOCTYPE dwr PUBLIC "-//GetAhead Limited//DTD Direct Web Remoting 2.0//EN"
        "http://getahead.ltd.uk/dwr/dwr20.dtd">

<dwr>
    <init>
        <creator id="jpublish" class="ca.flop.jpublish.dwr.JPublishCreator"/>
    </init>

    <allow>
        <create creator="new" javascript="FilePing" scope="application">
            <param name="class" value="ca.flop.jpublish.dwr.demo.FilePing"/>
        </create>

        <!--Test a JPublish Action-->
        <create creator="jpublish" javascript="InfoAction" scope="application">
            <param name="class" value="ca.flop.jpublish.dwr.DWRJPublishActionManager"/>
            <param name="actionName" value="InfoAction.bsh"/>
        </create>
    </allow>

    <signatures>
        <![CDATA[
          import java.util.Map;
          import ca.flop.jpublish.dwr.DWRJPublishActionManager;
          DWRJPublishActionManager.execute( Map<String, String>);
          ]]>
    </signatures>
</dwr>

```


_work in progress_ todo: describe the internals of the JPublishCreator

## Executing JPublish Actions ##
[Now](http://weblog.flop.ca/2007/10/27/1193531279888.html) is also possible to remotely execute any JPublish actions. This can be accomplished in a very simple way (I hope you'll agree), as illustrated below:

  * modify the dwr.xml file and define the Action you want to publish through the DWR.
```
    <init>
       <creator id="jpublish" class="ca.flop.jpublish.dwr.JPublishCreator"/>
    </init>
   ...
    <allow>
        <convert converter="bean" match="org.jpublish.util.*"/>
        
        <create creator="jpublish" javascript="InfoAction" scope="application">
           <param name="class" value="ca.flop.jpublish.dwr.DWRJPublishActionManager"/>
           <param name="actionName" value="InfoAction.bsh"/>
        </create>
    ...
    </allow>
```

  * then define the **[InfoAction.bsh](http://jpublish.googlecode.com/svn/trunk/samples/simpledwr/web/actions/InfoAction.bsh)**. Here is the code from our SVN example:
```
    String infoMsg = "Hi, this is JPublish 4";
    context.put("infoMsg", infoMsg);
    context.put("serverDate", new Date());
    print( infoMsg);
```

  * now let's call the **[InfoAction.bsh](http://jpublish.googlecode.com/svn/trunk/samples/simpledwr/web/actions/InfoAction.bsh)** in an AJAXified HTML page and display the **infoMsg** and the **serverDate** values returned by the **[InfoAction.bsh](http://jpublish.googlecode.com/svn/trunk/samples/simpledwr/web/actions/InfoAction.bsh)** script, a server side BSH script executed by JPublish. Ho hard could that be? What do you think? With JPublish everything should be simple ;) Here is the code:
```
    ..
    <script type='text/javascript' src='$request.ContextPath/dwr/interface/InfoAction.js'/>
    ...
    Below is a simple demonstration of the ability to invoke JPublish Actions via DWR:<br/>
    <input type="button" value="Info" onclick='InfoAction.execute( params, function(response) {
        dwr.util.setValue("serverDate", response.serverDate);
        dwr.util.setValue("info", response.infoMsg);
      });'/><br/>

    Messages received from server:<br/>
    <div class="dateField" id="serverDate">-server date-</div>
    <div class="salute" id="info">JPublish Salute...</div>
```

(Note: `the code above is using Velocity syntax`)

However, in the current implementation, the JPublishContext -required by the Actions- is a  limited one, used to carry on the input parameters and the Action response. In a very near version, we will provide a better JPublishContext to the Actions that can be executed via DWR calls. Recently the following JPublish objects were added:
```
    JPublishContext.JPUBLISH_REQUEST
    JPublishContext.JPUBLISH_RESPONSE
    JPublishContext.JPUBLISH_SESSION
    JPublishCreator.APPLICATION
    JPublishContext.JPUBLISH_CHARACTER_ENCODING_MAP
    JPublishContext.JPUBLISH_URL_UTILITIES
    JPublishContext.JPUBLISH_DATE_UTILITIES
    JPublishContext.JPUBLISH_NUMBER_UTILITIES
    JPublishCreator.DWR_CURRENT_PAGE
```

_todo: More examples_