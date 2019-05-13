/* Handle unregistered URL paths WITH 'express-error-handler' middleware
    i.e. If the user sends a request to an unregistered URL path, how to handle the 404 Not Found error
    
    'express-error-handler',
        return a specific view as the response for a specific HTTP error code
*/

// Basic Express modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), static = require('serve-static'), expressErrorHandler = require('express-error-handler');
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
router.route('/process/login/:name').post(function(req, res) {
    console.log('/process/login/:name 처리함.');

    var name = req.params.name; // URL parameter specified with ':' is found in req.params
    var id = req.body.id || req.query.id; // POST method request parameters in req.body, GET method query string in req.query
    var password = req.body.password || req.query.password;

    res.writeHead(200, {'Content-Type' : 'text/html; charset=utf8'});
    res.write('<h1>Express 서버에서 응답한 결과입니다.</h1>');
    res.write('<div><p>Param name : ' + name + '</p></div>');
    res.write('<div><p>Param id : ' + id + '</p></div>');
    res.write('<div><p>Param password : ' + password + '</p></div>');
    res.write("<br><br><a href='/login3.html'>로그인 페이지로 돌아가기</a>");
    res.end();    
});

// Add 'router' object to 'express' object
app.use('/', router);

// Handle 404 Not Found errors
var errorHandler = expressErrorHandler({
    static : {
        '404' : './public/404.html'
    }
});
app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});