/* Use 'http' module to request data from another server, with which to response to request

   Error when this file is run,
        perfectly natural as 'www.google.com' doesn't accept POST requests
*/
var http = require('http');

var opts = {
    host : 'www.google.com'
    , port : 80
    , method : 'POST'
    , path : '/'
    , headers : {}
}

var resData = '';
var req = http.request(opts, function(res) {
    // Handle response from the other server
    res.on('data', function(chunk) {
        resData += chunk;
    });

    res.on('end', function() {
        console.log(resData);
    });
});

// Unlike GET method, POST method requires user to set the request header and body
opts.headers['Content-Type'] = 'application/x-www-form-urlencoded';
req.data = 'q=actor'; // request's 'data' attribute is the request parameters
opts.headers['Content-Length'] = req.data.length;

req.on('error', function(err) {
    console.log('오류 발생 : ' + err.message);
});

req.write(req.data); // Set request parameters ('data' attribute) as body of request
req.end(); // Send request