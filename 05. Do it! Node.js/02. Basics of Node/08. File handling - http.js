/* Upon receiving a request, read the designated file and send response */
var fs = require('fs');
var http = require('http');
var server = http.createServer(function(req, res) {
    var instream = fs.createReadStream('./output.txt');
    instream.pipe(res); // req & res are both streams as well
});

server.listen(7000, '127.0.0.1'); // Wait for a request coming from IP : 127.0.0.1 (== localhost) and Port : 7000