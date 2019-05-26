const http = require('http');
const fs = require('fs');

http.createServer((req, res) => {
    fs.readFile('./server2.html', (err, data) => {
        if(err) {
            throw err;
        }
        res.end(data);
    });
}).listen(3000, () => {
    console.log('3000번 포트에서 서버 대기 중입니다!');
});