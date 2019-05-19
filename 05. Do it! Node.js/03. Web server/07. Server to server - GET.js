/* Use 'http' module to request data from another server, with which to response to request */
var http = require('http');

var options = {
    host: 'www.google.com'
    , port : 80
    , path : '/'
};

var req = http.get(options, function(res) { // Use GET method
    // Handle response from the other server
    var resData = '';
    res.on('data', function(chunk) { // 'data' event fired while receiving data
        resData += chunk;
    });
    res.on('end', function() { // 'end' event fired when all data has been received
        console.log(resData);
    });
});

req.on('error', function(err) {
    console.log('오류 발생 : ', err.message);
});