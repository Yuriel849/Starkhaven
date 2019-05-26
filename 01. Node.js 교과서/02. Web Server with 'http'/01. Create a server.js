const http = require('http');

// createServer([callback]) -> callback is optional; callback called for each request to this server
const server = http.createServer((req, res) => { // 'req' contains data about the request, 'res', about the response
    res.write('<h1>Hello Node!</h1>');
    res.end('<p>Hello Server!</p>');
        // res.end() ends the response; if there's argument, it's also sent to client before ending response
});
server.listen(3000);

server.on('listening', () => {
    console.log('3000번 포트에서 서버 대기 중입니다!');
});

server.on('error', (error) => {
    console.error(error);
});