var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var session = require('express-session');
var flash = require('connect-flash')

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// custom middleware - 1
app.use(function(req, res, next) {
  console.log(req.url, '저도 미들웨어입니다.');
  next();
});

// custom middleware -2 (multiple middlewares with one app.use())
app.use('/', function(req, res, next) {
  console.log('첫번째 미들웨어');
  next();
}, function(req, res, next) {
  console.log('두번째 미들웨어');
  next();
}, function(req, res, next) {
  console.log('세번째 미들웨어');
  next();
})

// custom middleware - 3 (stop next())
// app.use(function(req, res, next) {
//   if(+new Date() % 2 === 0) {
//     return res.status(404).send('50% 실패');
//   } else {
//     console.log('50% 성공');
//     next();
//   }
// });

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser('secret code'));
app.use(express.static(path.join(__dirname, 'public')));

// session
app.use(session({
  resave: false, // whether to save the session per request, even if the session is not modified
  saveUninitialized: false, // whether to save the session, even if there is nothing to save
  secret: 'secret code', // mandatory, serves as secure key for cookie-parser (must be same as secret of cookie-parser)
  cookie: { // settings for session cookie
    httpOnly: true, // client cannot access this cookie with JavaScript
    secure: false, // this cookie can be used without HTTPS
  }
}));

// connect-flash (MUST BE PLACED AFTER cookie-parser AND express-session)
app.use(flash());

app.use('/', indexRouter);
app.use('/users', usersRouter);

/* catch 404 and forward to error handler
    => placed right after the routers above
    (if the routers fail to handle the request, it means there is no matching URL and thus it is an error)
 */
app.use(function(req, res, next) {
  next(createError(404)); // immediately sends to error handler (createError(404) becomes value of parameter "err")
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
