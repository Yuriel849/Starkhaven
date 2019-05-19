var http = require('http');

var server = http.createServer(); // Create a server object

// Start the server and make it listen at port 3000
// var port = 3000;
// server.listen(port, function() { // Callback function called once server has been started
//     console.log('웹 서버가 시작되었습니다 : %d', port);
//     server.close(function() { // Terminates the server
//         console.log('웹 서버를 종료했습니다.');
//     })
// })

// Start the server and make it listen at designated IP 192.160.0.5 and port 3000
var host = '10.10.10.188'; // This is the IPv4, check in cmd with "ipconfig /all"
var port = 3000;
server.listen(port, host, '50000', function() {
    /* '50000'
        The backlog,
            specifies max. number of queued (waiting for their turn) connections
            should be at least 0
    */
    console.log('웹 서버가 시작되었습니다 : %s, %d', host, port);
})
