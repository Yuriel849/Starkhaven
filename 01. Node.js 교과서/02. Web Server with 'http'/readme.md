## Web Server with 'http' Module

### Contents
1. Basics of creating a web server with Node.js
2. REST API
3. 'https' & 'http2' modules
> 'https' module adds SSL encryption to the web server
>> however, an SSL certificate is required (Let's Encrypt offers a free certificate) <br>
this file can only be run properly with a certificate
> 'http2' module adds SSL encryption to the web server & utilizes the latest HTTP/2 protocol
>> HTTP/2 is faster and more efficient than HTTP/1.1 <br>
even in 2019, 4 years after its release, not all websites support HTTP/2
4. 'cluster' module
> Node.js uses a single thread, but the 'cluster' module allows the creation of multiple processes using multiple CPU cores
>> Pros => Can handle multiple requests more quickly and efficiently <br>
Cons => Cannot share a common session (Can be fixed with Redis)