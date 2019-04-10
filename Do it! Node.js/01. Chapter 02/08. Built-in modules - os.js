/* The "os" built-in module (i.e. no need to install with npm, just use) provides information about the OS */
var os = require('os');

console.log('시스템의 hostname : %s', os.hostname());
console.log('시스템의 memory : %d / %d', os.freemem(), os.totalmem());
console.log('\n시스템의 CPU 정보');
console.dir(os.cpus());
console.log('\n시스템의 네트워크 인터페이스 정보');
console.dir(os.networkInterfaces());