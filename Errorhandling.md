## Error Handling ##

JPublish's error handling mechanism can be overridden to provide custom error handlers. This is useful for sending notifications upon errors as well as error logging. JPublish provides a way to specify the default error handler as well as path-based error handling.

To set default error handlers, include the following in the jpublish.xml configuration:
```
    <default-error-handlers>
    	<error-handler class="org.jpublish.error.DefaultErrorHandler"/>
    </default-error-handlers>
```

You can include as many error handler implementations as necessary by adding additional error-handler elements as children of the default-error-handlers element.

To set path-based error handlers, include the following in the jpublish.xml configuration:
```
    <error-handler-map path="/*">
    	<error-handler class="org.jpublish.error.DefaultErrorHandler"/>
    </error-handler-map>
```
You can include as many error handler implementations as necessary by adding additional error-handler elements as children of the error-handler-map element. Each error handler map must have a unique path attribute value. The path attribute can include wildcards.

Any class which is used as an error handler must implement the `org.jpublish.ErrorHandler` interface or extend from the abstract base class `org.jpublish.error.AbstractErrorHandler`. The `handleError(JPublishError error)` method will be invoked whenever an error occurs. The JPublishError class provides access to the original exception, the **JPublishContext** and provides a method for consuming the error. If an error handler consumes an error then no further error handlers for that particular request will be executed.

[Go back](JPublishUserGuide.md)