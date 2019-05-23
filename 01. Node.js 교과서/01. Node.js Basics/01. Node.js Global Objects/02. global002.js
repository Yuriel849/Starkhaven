const A = require('./01. global001');

global.message = '안녕하세요';
console.log(A());

/* "message" attribute in global object is the same, regardless of where it is accessed from
    BUT do not overuse, can later be difficult to find in which file the global object is accessed
*/

