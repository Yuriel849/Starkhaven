// 'app.js' is application's main entry points

// Call basic modules for Express
var express = require('express'), http = require('http');

var app = express(); // Create express server object

/* Express server object methods
        set() -> Set attributes
        get() -> Get attributes
        use() -> Use middleware
            Middleware & Routers are simply put functions with independent abilities,
                middleware are used to process requests and responses
                routers are used to route a certain request to a certain function
*/

app.set('port', process.env.PORT || 3000); // Set port as attribute of express server object
/* Default attributes of express server object
        env -> Set the server's mode
        views -> Set folder where the views are
        view engine -> Set default view engine, usually 'ejs' or 'pug',
            view engine creates a view from user-made templates to send as the reponse
*/

http.createServer(app).listen(app.get('port'), function(){ // Create and start express server
    console.log('익스프레스 서버를 시작했습니다 : ' + app.get('port'));
})