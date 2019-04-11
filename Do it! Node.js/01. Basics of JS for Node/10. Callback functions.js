/* Providing an anonymous function as a parameter when calling function 'add' */
function add(a, b, callback) {
    var result = a + b;
    callback(result);
}

add(10, 10, function(result) {
    console.log('add > 파라미터로 전달된 콜백 함수 호출됨.');
    console.log('더하기 (10, 10)의 결과 : %d', result);
})

/* Returning a new function 'history' as the result of function 'add2' */
function add2(a, b, callback) {
    var result = a + b;
    callback(result);

    var count = 0;
    var history = function() {
        count++;
        return count + ' : ' + a + ' + ' + b + ' = ' + result;
    }
    return history;
}

var add_history = add2(10, 10, function(result) {
    console.log('add2 > 파라미터로 전달된 콜백 함수 호출됨.');
    console.log('더하기 (10, 10)의 결과 : %d', result);
})

/* Closure -> Continued access to 'count' variable of 'add2' function in which 'history' function was created */
console.log('결과 값으로 받은 함수 실행 결과 : ' + add_history());
console.log('결과 값으로 받은 함수 실행 결과 : ' + add_history());
console.log('결과 값으로 받은 함수 실행 결과 : ' + add_history());