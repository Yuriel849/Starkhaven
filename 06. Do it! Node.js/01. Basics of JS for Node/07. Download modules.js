var nconf = require('nconf');
nconf.env();

console.log('OS 환경 변수의 값 : %s', nconf.get('OS'));

/* The nconf package must be downloaded -> Type "npm install nconf" in the terminal and hit enter
        Downloaded packages can be found in the "node_modules" directory
        When the main node file is run, the program scans the current directory for the "node_modules",
            and if none are found, scans the parent directory and so on
*/