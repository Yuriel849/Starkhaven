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

const session = {}; // object within server (does not go to client) to store the user's name and cookie's date of expiry
                    // cookie is simply a number (cookie's time of creation as a number)

http.createServer((req, res) => {
    const cookies = parseCookies(req.headers.cookie);
    if(req.url.startsWith('/login')) {
        const { query } =  url.parse(req.url); // 'req.url' 속성 값을 분해하고 그 중 'query' 속성 값을 'query' 변수에 매칭
        const { name } = qs.parse(query);
        const expires = new Date();
        expires.setMinutes(expires.getMinutes() + 5); // 'expires' 변수의 시각을 현재시각 + 5분 후로 재설정
        const randomInt = +new Date(); // '+' casts the date into a number
        console.log(new Date());
        console.log(+new Date());
        session[randomInt] = {
            name,
            expires,
        };
        res.writeHead(302, {
            Location : '/',
            'Set-Cookie' : `session=${randomInt}; Expires=${expires.toGMTString()}; HttpOnly; Path=/`,
                            // encodeURIComponent() => header에 한글을 설정할 수 없기 때문에 name 변수 값을 인코딩
        });
        res.end();
    } else if(cookies.session && session[cookies.session].expires > new Date()) {
        res.writeHead(200, { 'Content-Type' : 'text/html; charset=utf-8' });
        res.end(`${session[cookies.session].name}님 안녕하세요`);
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