# Static Resources #

Static resources are all files which will never be parsed by the view renderer. This includes images, PDF files, Word documents, video, audio and other binary media. Requests for static files will be handled prior to a lookup for a page object, thus you do not need to include page objects for static content.

Like the other JPublish subsystems, the Static resource loader is completely pluggable. JPublish includes an implementation for loading static resources from the file system which is used as the default loader, but other implementations can be created and installed through the jpublish.xml configuration file.

A new addition to JPublish 4 is the use of the Last-Modified header as a means of improving performance of websites by reducing traffic to the site for items. JPublish 4 will check and return the last modified time of any static resource and will include this value in the HTTP headers on each request. Web browsers which support the Last-Modified header will then close the IO stream immediately upon receiving the header thus freeing up the server connection for the next request.