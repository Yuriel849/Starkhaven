var http = require('http');
var fs = require('fs');

var server = http.createServer(); // Create a server object

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

// Handle client request event
server.on('request', function(req, res) {
    console.log('클라이언트 요청이 들어왔습니다.');
    // View all data of the incoming request
//  console.dir(req);

    // Send standard HTML data as response
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
    res.end(); // indicates 'done writing the response', sends response at this point
                    // if data is given as parameter, data is included in response
                    // if callback is given as parameter, called after sending response

});

// Handle server termination event
server.on('close', function() {
    console.log('서버가 종료됩니다.');
});