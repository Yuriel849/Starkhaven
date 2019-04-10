/* In order to calculate how much time it takes to run a certain section of code,
        use console.time(id) before & console.timeEnd(id) after the section

    The time required to run all the code between console.time & console.timeEnd will be printed on the console
*/
var result = 0;

console.time('duration_sum');

for(var i = 1; i <= 10000000; i++) {
    result += i;
}

console.timeEnd('duration_sum');
console.log('1부터 10000000까지 더한 결과물 : %d', result);

/* __filename & __dirname are global variables, and as such can be used anywhere */
console.log('현재 실행한 파일의 이름 : %s', __filename);
console.log('현재 실행한 파일의 경로 : %s', __dirname);

var Person = {'name' : 'Elisabeth', 'age' : 20, 'gender' : 'female'};
console.dir(Person);
/* console.dir is often used to check the contents of JS objects */