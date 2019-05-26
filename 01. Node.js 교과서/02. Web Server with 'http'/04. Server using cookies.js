const http = require('http');
const fs = require('fs');
const url = require('url');
const qs = require('querystring');

const parseCookies = (cookie = '') =>
    cookie
        .split(';')
        .map(v => v.split('='))
        .map(([k, ...vs]) => [k, vs.join('=')])
        .reduce((acc, [k, v]) => {
            acc[k.trim()] = decodeURIComponent(v);
            return acc;
        }, {}); // changes cookie string such as 'name=puppy;age=10' into object { name : 'puppy', age = '10' })

http.createServer((req, res) => {
    const cookies = parseCookies(req.headers.cookie);
    if(req.url.startsWith('/login')) {
        const { query } =  url.parse(req.url); // 'req.url' 속성 값을 분해하고 그 중 'query' 속성 값을 'query' 변수에 매칭
        const { name } = qs.parse(query);
        const expires = new Date();
        expires.setMinutes(expires.getMinutes() + 5); // 'expires' 변수의 시각을 현재시각 + 5분 후로 재설정
        res.writeHead(302, {
            Location : '/',
            'Set-Cookie' : `name=${encodeURIComponent(name)}; Expires=${expires.toGMTString()}; HttpOnly; Path=/`,
                            // encodeURIComponent() => header에 한글을 설정할 수 없기 때문에 name 변수 값을 인코딩
                /*
                    options when creating cookies
                        <cookie name>=<cookie value>
                        Expires=<date>
                            Cookie deleted after designated time is reached; default value is when client is terminated.
                        Max-age=<seconds>
                            Cookie deleted after designated seconds have passed.
                            Can be used instead of Expires, takes precedence over Expires.
                        Domain=<domain name>
                            Cookie only sent to designated domains; default value is the current domain.
                        Path=<URL>
                            Cookie only sent when request includes the designated URL.
                            Default value is '/', and cookie is sent with all requests.
                        Secure
                            Cookie is only sent when a request is made with SSL and HTTPS.
                        HttpOnly
                            Cookie cannot be accessed with JavaScript, to secure the cookie against tampering.
                 */
        });
        res.end();
    } else if(cookies.name) {
        res.writeHead(200, { 'Content-Type' : 'text/html; charset=utf-8' });
        res.end(`${cookies.name}님 안녕하세요`);
    } else {
        fs.readFile('./server4.html', (err, data) => {
            if(err) {
                throw err;
            }
            res.end(data);
        });
    }
}).listen(3000, () => {
    console.log('3000번 포트에서 서버 대기 중입니다!');
});