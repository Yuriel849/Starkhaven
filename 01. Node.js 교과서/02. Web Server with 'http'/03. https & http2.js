const https = require('https'); // to use 'http2', simply replace "require('https')" with "require('http2')"
const fs = require('fs');

/* https.createServer(A, B) =>
    1st argument A is an object with optional data about the certificate,
    2nd argument B is the server logic
 */
https.createServer({ // to use 'http2', simply replace "createServer()" with "createSecureServer()"
    cert : fs.readFileSync('<domain certificate path>'),
    key : fs.readFileSync('<domain key path>'),
    ca : [
        fs.readFileSync('<higher-level certificate path>'),
        fs.readFileSync('<higher-level certificate path>'),
    ],
}, (req, res) => {
    res.write('<h1>Hello Node!</h1>');
    res.end('<p>Hello Server!</p>');
}).listen(443, () => {
    console.log('443번 포트에서 서버 대기 중입니다!');
})
