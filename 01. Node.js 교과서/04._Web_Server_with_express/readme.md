## Web Server with 'express' Module

### Express adds additional functions to the request and response objects used by the "http" module, not to mention adding various helpful methods. Overall, using the express web server framework is much preferred over using just the "http" module.

### READ "./views/tutorial.pug"

### Contents
1. Express-generator
> Package to easily set up express application <br>
Install globally => "npm i -g express-generator" <br>
Set up express application => Move to directory and "express [project name] --view=pug" <br>
>> "--view=pug" => express-generator installs Jade as the default template engine, but Jade has long been replaced with PUG
>>> Use "--view=ejs" to install EJS as the template engine

> Setup of express application with express-generator
>> app.js => 핵심적인 서버 역할 <br>
bin/www => 서버를 실행하는 script, http모듈 & express모듈을 연결, port 지정 <br>
public => 클라이언트에서 접근 가능한 image, JS, CSS 위치 <br>
routes => 주소별 라우터가 위치 (MVC의 controller 역할) <br>
views => 템플릿 파일이 위치 (MVC의 view) <br>

2. Middlewares
> app.js에서 app.use(function) => function is middleware, connecting middleware to express <br>
요청과 응답 중간에 위치한다고 하여 middleware, 사실상 요청을 받고 응답을 하기까지의 모든 과정을 middleware에서 처리 <br>
next() => essential, this is what connects one middleware to the next <br>
>> if no argument, simply connects to the next middleware <br>
if argument is anything else, connects to the error handler and the argument is considered to the data about the error, and connects to the err parameter in the error handler <br>
if argument is 'route' AND WHEN USED WITH "express.Router()" object, skips middleware and connects to the next router with same address <br>
>>> ex) router.get('/', middleware1{next('route');}, middleware2, middleware3); router.get('/', middleware4); <br>
here, middleware1 is called, encounters "next('route')" and skips middleware2 & middleware3, proceeding to the next router with the same address and middleware4

> 1. morgan
>> used to print log about request on the console <br>
var logger = require('morgan'); app.use(logger('dev')); => prints HTTP request + route + HTTP status code + response time + response size in bytes (ex) GET / 200 51.267 ms - 1539) <br>
winston module used instead of morgan to print log in file or database <br>

> 2. body-parser
>> used to parse the body of requests, usually used for form data or Ajax requests <br>
from express 4.16.0 some of body-parser's functions are integrated into express, meaning require('body-parser') is not required, so just use app.use(express.json());app.use(express.urlencoded({ extended: false })); <br>
require('body-parser') IS required when parsing data other than JSON or URL-encoded, such as raw (body of request is buffer data) or text (body of request is text data)... in this case, use require('body-parser'); app.use(bodyParser.raw());app.use(bodyParser.text()); <br>
app.use(express.urlencoded({ extended: false }));... if extended is false, uses querystring module to handle the query, while if extended is true, uses the qs module to handle the query (qs is expanded, npm package of querystring (Node global module) module) <br>
body-parser parses the body of requests and puts it in "req.body" object <br>
body-parser CANNOT handle multipart/form-data <br>

> 3. cookie-parser
>> used to parse cookies, which are put in "req.cookies" object <br>
app.use(cookieParser('XXX'));... cookies are now signed with the XXX string, for security purposes, error occurs if a signed cookie is altered by the client <br>

> 4. static
>> used to handle static files <br>
designate the directory where static files (images, CSS, JS) are stored <br>
"public/stylesheets/styles.css" == "http://localhost:3000/stylesheets/styles.css" <br>
fs.readFile, etc. unnecessary because static files are provided automatically on request by client <br>
IF app.use('/asdf', express.static(path.join(__dirname, 'public')));... "public/stylesheets/styles.css" !== "http://localhost:3000/stylesheets/styles.css" AND ONLY "public/stylesheets/styles.css" == "http://localhost:3000/asdf/stylesheets/styles.css" <br>
IF static middleware finds a static file that matches the request, then it sends the static file as the response (following middlewares are NOT called)... static is usually situated near the head, so other middlewares don't have to used unnecessarily if it's just a request for a static file <br>

> 5. express-session
>> middleware to handle sessions <br>
not installed with express-generator <br>
var session = require('express-session');app.use(session(OBJECT));... OBJECT contains settings for session as attributes <br>
when used, sends a session cookie to the client... should be signed for security, so "secret" attribute of session should match "secret" value of signed cookie-parser <br>
creates req.session object in the request object... use this req.session object to handle the session... delete all with req.session.destroy(), check current session with req.sessionID <br>

> 6. connect-flash
>> used to send one-time messages to the browser(client) <br>
uses the cookie-parser & express-session middlewares, so MUST be placed after them <br>
flash middleware adds the req.flash method to the request object <br>
add a key and value with req.flash(key, value) and get the value for a key with req.flash(key) <br>

7. var router = express.Router();
> if router URL is "/users/:id" and client uses "/users/123?limit=5&skip=10", <br>
123 matches with :id, and is stored in req.params and limit : 5 is stored in req.query as <br>
req.params = { id : '123' } <br>
req.query = { limit : '5', skip : '10' }

> when router responds to client's request, uses <br>
>> res.send() => can be used to send buffer data, strings, HTML codes, JSON strings as responses <br>
res.sendFile() => sends file as response (argument is file's path) <br>
res.json() => sends JSON strings as response <br>
res.redirect() => redirects (argument is address) <br>
res.render() => used to render the template engine <br>

> only one response to one request, cannot send multiple responses <br>

> to manually set the HTTP status code when responding, <br>
ex) res.status(404).send('NOT FOUND');

8. Template engines => PUG & EJS
> Template engines enable the rendering of HTML files with JavaScript <br>
PUG => SEE "views/tutorial.pug" <br>
EJS
>> EJS is similar to Java's JSP & is HTML with JavaScript codes <br>
Variables => Surrounded by "<%= %>" <br>
JavaScript => Codes surrounded by "<% %>" <br>
To not change special characters into HTML entities => Use "<%- %>" instead of "<%= %>" <br>
To include HTML files => Use "<%- include([file path], [data]) %>"