// Call Express basic modules
var express = require('express')
    , http = require('http')
    , path = require('path');
// Call middleware for Express
var bodyParser = require('body-parser')
    , cookieParser = require('cookie-parser')
    , static = require('serve-static')
    , errorHandler = require('errorhandler');
// Use errorHandler's module
var expressErrorHandler = require('express-error-handler');
// Call session middleware
var expressSession = require('express-session');
// Create express server object
var app = express();
// Set default port
app.set('port', process.env.PORT || 3000);
// Use body-parser to parse 'application/x-www-form-urlencoded' data
app.use(bodyParser.urlencoded({ extended : false }));
// Use body-parser to parse 'application/json' data
app.use(bodyParser.json);
// Map URL path to designated directory 'public'
app.use('/', static(path.join(__dirname, 'public')));
// Use cookie-parser
app.use(cookieParser());
// Set up session
app.use(expressSession({
    secret : 'my key'
    , resave : true
    , saveUninitialized : true
}));

// Get 'router' object from Express
var router = express.Router();

// Routing for login - Compare with data in the database
router.route('/process/login').post(function(req, res) {
    console.log('/process/login 호출됨.');
});

// Register 'router' object
app.use('/', router);

// Handle 404 error
var errorHandler = expressErrorHandler({
    static : {
        '404' : './public/404.html'
    }
});
app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);

// Start server
http.createServer(app).listen(app.get('port'), function() {
    console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));
});