/* Important request object methods
    send([body]) -> Sends response
        Acceptable body data are HTML strings, Buffer objects, JSON objects, JSON arrays
    status(code) -> Sends HTTP status code, requires seaprate end() or send() to actually send it
    sendStatus(code) -> Sends HTTP status code, equivalent to using status() & send()
    redirect([status,] path) -> Requests that the client redirect to designated path
    render(view [, locals][, callback]) -> Sends view made with view engine
*/

var express = require('express'), http = require('http');
var app = express();

app.use(function(req, res, next) {
    console.log('첫번째 미들웨어에서 요청을 처리함.');
    
//    res.send({name : 'Alice', age : 20}); // Following middlewares not fired, response sent here
//    res.status(403).send('Forbidden');
//    res.sendStatus(403);
    res.redirect('http://www.google.com');
});

http.createServer(app).listen(3000, function() {
    console.log('Express 서버가 3000번 포트에서 시작됨.');
});