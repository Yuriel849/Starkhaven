/* 'serve-static' middleware
        Used to serve static files from a given root directory to specific URL paths
 */

var express = require('express');
var http = require('http');
var path = require('path');
var static = require('serve-static');

var app = express();

// 1st parameter is the URL path, 2nd parameter designates directory with static() -> URL path mapped to designated directory
app.use('/', static(path.join(__dirname, 'public')));
    // ex) 'http://localhost:3000/images/Berlin.JPG' -> Accesses the file with path './public/images/Berlin.JPG'

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});