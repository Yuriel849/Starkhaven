/* 'express-session' middleware
        Used to manipulate sessions
*/

// Basic Express modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), static = require('serve-static'), cookieParser = require('cookie-parser')
    expressSession = require('express-session');
// Create Express server object
var app = express();
// Get 'router' object from Express
var router = express.Router();

// Set port for the server
app.set('port', process.env.PORT || 3000);

// Request objects will now include cookies
app.use(cookieParser());
// Can now manipulate sessions
app.use(expressSession({
    secret : 'my key'
    , resave : true
    , saveUninitialized : true
}));

// Parse 'application/x-www-form-urlencoded' data with the body-parser middleware
app.use(bodyParser.urlencoded({extended : false}));
// Parse 'application/json' data with the body-parser middleware
app.use(bodyParser.json());

app.use('/', static(path.join(__dirname, 'public')));

// Access product page, check if session data for logged in user exists or not
router.route('/process/product').get(function(req, res) {
    console.log('/process/product 호출됨.');

    if(req.session.user) { // Session object named 'user' is accessed by 'req.session.user'
        res.redirect('/product.html');
    } else {
        res.redirect('/login2.html');
    }
});

// Log in; if session object 'user' exists, already logged in
router.route('/process/login').post(function(req, res) {
    console.log('/process/login 호출됨.');

    var id = req.body.id || req.query.id;
    var pwd = req.body.password || req.query.password;

    if(req.session.user) {
        console.log('이미 로그인되어 상품 페이지로 이동합니다.');

        res.redirect('/product.html');
    } else {
        req.session.user = { // Add 'user' object to 'session' attribute of 'request' object
            id : id
            , name : 'Alicia'
            , authorized : true
        };

        res.writeHead(200, {'Content-Type' : 'text/html; charset=utf8'});
        res.write('<h1>로그인 성공</h1>');
        res.write('<div><p>ID : ' + id + '</p></div>');
        res.write('<div><p>Password : ' + pwd + '</p></div>');
        res.write("<br><br><a href='/process/product'>상품 페이지로 이동하기</a>");
        res.end();
    }
});

// Log out
router.route('/process/logout').get(function(req, res) {
    console.log('/process/logout 호출됨.');

    if(req.session.user) {
        console.log('로그아웃합니다.');

        req.session.destroy(function(err) { // Destroy session data
            if(err) throw err;

            console.log('세션을 삭제하고 로그아웃되었습니다.');
            res.redirect('/login2.html');
        });
    } else {
        console.log('아직 로그인되어 있지 않습니다.');
        
        res.redirect('/login2.html');
    }
})

// Add 'router' object to 'express' object
app.use('/', router);

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});