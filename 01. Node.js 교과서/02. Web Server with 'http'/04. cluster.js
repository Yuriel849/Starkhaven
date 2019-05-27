const cluster = require('cluster'); // 'pm2' module is a better way to use clusters
const http = require('http');
const numCPUs = require('os').cpus().length;

if(cluster.isMaster) {
    console.log(`마스터 프로세스 ID : ${process.pid}`);
    // Create as many worker processes as there are CPUs
    for(let i = 0; i < numCPUs; i++) {
        cluster.fork(); // Creates a single worker process
    }
    // When a worker process terminates
    cluster.on('exit', (worker, code, signal) => {
        console.log(`${worker.process.pid}번 워커가 종료되었습니다.`);
        cluster.fork(); // Everytime a worker process terminates, a new one is created
    });
} else { // Each worker process comes here (because "cluster.isMaster === false")
    // The worker process listens at the designated port
    http.createServer((req, res) => {
        res.write('<h1>Hello Node!</h1>');
        res.end('<p>Hello Cluster!</p>');
        setTimeout(() => {
            process.exit(1);
        }, 1000);
        /*
            Each worker process terminates 1 second after receiving a request from a client
            The server only shuts down when all worker processes have terminated,
                meaning even if an error occurs,
                only the relevant worker process is affected and the server can continue to function
         */
    }).listen(3000);

    console.log(`${process.pid}번 워커 실행`);
}