/* This is the main file
        require(filename) calls the module with the designated filename (i.e. calls the designated JS file)
            -> the filename should NOT include the '.js' extension
            -> if a file with the designated filename does not exist, node searches for a directory with the filename;
                    if a directory with the filename exists, then node will instead call the 'index.js' file within that directory
        require(filename) returns the exports object within the designated module,
            and then the returned object can be assigned to a variable in the main file;
            thus the returned object == exports object within the designated module
*/

var calc = require('./calc');
console.log('모듈로 분리한 후 - calc.add 함수 호출 결과 : %d', calc.add(10,10));

var calc2 = require('./calc2');
console.log('모듈로 분리한 후 - calc2.add 함수 호출 결과 : %d', calc2.add(10,10));