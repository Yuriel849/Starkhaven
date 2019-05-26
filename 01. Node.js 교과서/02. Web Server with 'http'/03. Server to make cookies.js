const http = require('http');

const parseCookies = (cookie = '') =>
    cookie
        .split(';')
        .map(v => v.split('='))
        .map(([k, ...vs]) => [k, vs.join('=')])
        .reduce((acc, [k, v]) => {
            acc[k.trim()] = decodeURIComponent(v);
            return acc;
        }, {}); // changes cookie string such as 'name=puppy;age=10' into object { name : 'puppy', age = '10' }

http.createServer((req, res) => {
    const cookies = parseCookies(req.headers.cookie); // cookie from browser is found in 'req.headers.cookie'
    console.log(req.url, cookies);
    res.writeHead(200, { 'Set-Cookie' : 'mycookie=test' }); // 'Set-Cookie' tells browser to set 'mycookie=test' as cookie
    res.end('Hello Cookie');
}).listen(3000, () => {
    console.log('3000번 포트에서 서버 대기 중입니다!');
})

/*
    There are two lines logged when a browser sends this server a request for the first time.
        The first is the request from the browser and the log is "/ { '': '' }"
            because the access URL is localhost:3000 and there is no cookie yet
        The second is the browser's request for the favicon and the log is "/favicon.ico { mycookie : 'test' }"
    
    The favicon (favorite icon) is the icon that shows up on the browser's tab (ex) Google uses a stylized "G"),
        and if the HTML page doesn't specify the favicon,
            the browser makes a second request to the server to ask for the favicon.
        Thus a second request is logged, which shows the cookie the browser sent to this server.
 */
