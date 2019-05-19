/* 'cookie-parser' middleware
        Used to manipulate cookies
*/

// Basic Express modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), static = require('serve-static'), cookieParser = require('cookie-parser');
// Create Express server object
var app = express();
// Get 'router' object from Express
var router = express.Router();

// Set port for the server
app.set('port', process.env.PORT || 3000);

// Request objects will now include cookies
app.use(cookieParser());

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

router.route('/process/showCookie').get(function(req, res) {
    console.log('/process/showCookie 호출됨.');

    res.send(req.cookies);
});

router.route('/process/setUserCookie').get(function(req, res) {
    console.log('/process/setUserCookie 호출됨.');

    // Cookie settings
    res.cookie('user', {
        id : 'A02B'
        , name : 'Alicia'
        , authorized : true
    });

    res.redirect('/process/showCookie');
})

// Add 'router' object to 'express' object
app.use('/', router);

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});