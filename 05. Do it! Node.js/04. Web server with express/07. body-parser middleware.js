/* 'body-parser' middleware
        Middleware used to read the request body of POST method requests,
            automatically parses the request body and puts it in the 'body' attribute of the request object
*/

// Basic Express modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), static = require('serve-static');
// Create Express server object
var app = express();

// Set port for the server
app.set('port', process.env.PORT || 3000);

// Parse 'application/x-www-form-urlencoded' data with the body-parser middleware
app.use(bodyParser.urlencoded({extended : false}));
// Parse 'application/json' data with the body-parser middleware
app.use(bodyParser.json());

app.use('/', static(path.join(__dirname, 'public')));

app.use(function(req, res, next) {
    console.log('첫번째 미들웨어에서 요청을 처리함.');

    var id = req.body.id || req.query.id; // POST method request parameters in req.body, GET method query string in req.query
    var password = req.body.password || req.query.password;

    res.writeHead(200, {'Content-Type' : 'text/html; charset=utf8'});
    res.write('<h1>Express 서버에서 응답한 결과입니다.</h1>');
    res.write('<div><p>Param id : ' + id + '</p></div>');
    res.write('<div><p>Param password : ' + password + '</p></div>');
    res.end();
});

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});