/* 'multer' middleware
        Used to handle file uploading, 'body-parser' middleware also required because file uploads are POST requests
*/
// Express basic modules
var express = require('express'), http = require('http'), path = require('path');
// Express middleware
var bodyParser = require('body-parser'), cookieParser = require('cookie-parser'), static = require('serve-static'), errorHandler = require('errorhandler');
// Error handler module
var expressErrorHandler = require('express-error-handler');
// Session module
var expressSession = require('express-session');
// File upload middleware
var multer = require('multer'), fs = require('fs');
// Module to allow CORS for ajax requests
var cors = require('cors');

// Express server object
var app = express();
// Set port
app.set('port', process.env.PORT || 3000);
// Parse 'application/x-www-form-urlencoded' data with the body-parser middleware
app.use(bodyParser.urlencoded({extended : false}));
// Parse 'application/json' data with the body-parser middleware
app.use(bodyParser.json());
// Map public and uploads directories
app.use('/', static(path.join(__dirname, 'public')));
app.use('/uploads', static(path.join(__dirname, 'uploads')));
// Set up to use cookies
app.use(cookieParser());
// Set up to use sessions
app.use(expressSession({
    secret : 'my key'
    , resave : true
    , saveUninitialized : true
}));
// Allow CORS for ajax requests
app.use(cors());

// File uploading -> Sequence !IMPORTANT -> 'body-parser > multer > router'
var storage = multer.diskStorage({
    destination : function(req, file, callback) { // Designate where uploaded files are to be saved
        callback(null, 'uploads')
    },
    filename : function(req, file, callback) { // Designate names for uploaded files
        callback(null, file.originalname + "_" + Date.now())
            // The uploaded file is in the 'file' attribute of the request object
    }
});
var upload = multer({
    storage : storage
    , limits : { // Limit the size, number, etc. of uploaded files
        files : 10 // Max. 10 files
        , fileSize : 1024 * 1024 * 1024 // Max. size 1GB
    }
});

// Get 'router' object from Express to set up routing functions
var router = express.Router();

router.route('/process/photo').post(upload.array('photo', 1), function(req, res) {
    console.log('/process/photo 호출됨.');

    try {
        var files = req.files;

        console.dir('#===== 업로드된 첫번째 파일 정보 =====#');
        console.dir(req.files[0]);
        console.dir('#=====#');

        // Variables to store data about the current file
        var originalname = ''
            ,filename = ''
            , mimetype = ''
            , size = 0;
        
        if(Array.isArray(files)) { // If the uploaded files are in an array
            console.log('배열에 들어있는 파일 갯수 : %d', files.length);

            for(var index = 0; index < files.length; index++) {
                originalname = files[index].originalname;
                filename = files[index].filename;
                mimetype = files[index].mimetype;
                size = files[index].size;
            }
        } else { // If not in an array (not used currently, as all files are set up in an array)
            console.log('파일 갯수 : 1');

            originalname = files[index].originalname;
            filename = files[index].filename;
            mimetype = files[index].mimetype;
            size = files[index].size;
        }

        console.log('현재 파일 정보 : ' + originalname + ', ' + filename + ', ' + mimetype + ', ' + size);

        // Send response to client
        res.writeHead(200, {'Content-Type' : 'text/html; charset=utf8'});
        res.write('<h3>파일 업로드 성공</h3>');
        res.write('<hr/>');
        res.write('<p>원본 파일 이름 : ' + originalname + ' -> 저장 파일명 : ' + filename + '</p>');
        res.write('<p>MIME TYPE : ' + mimetype + '</p>');
        res.write('<p>파일 크기 : ' + size + '</p>');
        res.end();
    } catch(err) {
        console.dir(err.stack);
    }
});

// Add 'router' object to 'express' object
app.use('/', router);

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});