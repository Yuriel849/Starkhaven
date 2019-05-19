/* 'router' middleware
        Included in the Express module
        To use,
            1. Get the 'router' object from Express -> ('express' obj).Router()
            2. Map URL paths and request methods to function -> ('router' obj).route(path).get/post/put/delete/all(function)
                'all' receives all request methods
            3. Add 'router' object to 'express' object
*/

// Basic Express modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), static = require('serve-static');
// Create Express server object
var app = express();
// Get 'router' object from Express
var router = express.Router();

// Set port for the server
app.set('port', process.env.PORT || 3000);

// Parse 'application/x-www-form-urlencoded' data with the body-parser middleware
app.use(bodyParser.urlencoded({extended : false}));
// Parse 'application/json' data with the body-parser middleware
app.use(bodyParser.json());

app.use('/', static(path.join(__dirname, 'public')));

// Map URL paths and request methods to function
router.route('/process/login').post(function(req, res) {
    console.log('/process/login 처리함.');

    var id = req.body.id || req.query.id; // POST method request parameters in req.body, GET method query string in req.query
    var password = req.body.password || req.query.password;

    res.writeHead(200, {'Content-Type' : 'text/html; charset=utf8'});
    res.write('<h1>Express 서버에서 응답한 결과입니다.</h1>');
    res.write('<div><p>Param id : ' + id + '</p></div>');
    res.write('<div><p>Param password : ' + password + '</p></div>');
    res.end();    
});

// Add 'router' object to 'express' object
app.use('/', router);

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});