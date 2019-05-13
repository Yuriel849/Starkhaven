/* 'url' module
        Can easily convert URL string to URL object, and vice versa
            url.parse() -> Parse URL string and create URL object
            url.format() -> Convert URL object into URL string
   'querystring' module
        Can easily manipulate URL query parameters
            querystring.parse() -> Parse query string and create request parameter object
            querystring.stringify() -> Convert request parameter object into query string
*/

var url = require('url');
var querystring = require('querystring');

var curUrl = url.parse('https://www.google.com/search?q=home&oq=home&aqs=chrome..69i57j0l5.1077j0j7&sourceid=chrome&ie=UTF-8');
var curStr = url.format(curUrl);
var param = querystring.parse(curUrl.query);

console.log('주소 문자열 : %s', curStr);
console.dir(curUrl);

console.dir(param);
console.log('요청 파라미터 중 query의 값 : %s', param.q);
console.log('원본 요청 파라미터 : %s', querystring.stringify(param));
