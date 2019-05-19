var http = require('http');

var server = http.createServer(function(req, res) { // Create a server object
    console.log('클라이언트 요청이 들어왔습니다.');

    res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"}); // response header
    res.write("<!DOCTYPE html>"); // response body
    res.write("<html>");
    res.write("<head>");
    res.write("<title>응답 페이지</title>");
    res.write("</head>");
    res.write("<body>");
    res.write("<h1>Node.js로부터의 응답 페이지</h1>");
    res.write("</body>");
    res.write("</html>");
    res.end();
});

// Start the server and make it listen at port 3000
var port = 3000;
server.listen(port, function() {
    console.log('웹 서버가 시작되었습니다 : %d', port);
});

// Handle client connection event
server.on('connection', function(socket) { // Socket object given as parameter for callback
    var addr = socket.address();
    console.log('클라이언트가 접속했습니다 : %s, %d', addr.address, addr.port);
});

// Handle server termination event
server.on('close', function() {
    console.log('서버가 종료됩니다.');
});