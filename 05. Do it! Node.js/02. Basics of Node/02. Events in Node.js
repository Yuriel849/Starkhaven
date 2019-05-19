/* Event
        Like status messages being sent back and forth; Node provides 'EventEmitter' in 'events' module to handle events
        Methods (must inherit EventEmitter to use these)
            on(event, listener) -> Creates event listener for designated event
            once(event, listener) -> Creates one-time event listener for designated event (listener removed after one use)
            removeListener(event, listener) -> Removes listener for designated event

    Event Listener
        Method standing by to receive event with designated ID and do something when said event has been received
*/

process.on('exit', function() {
    console.log('exit 이벤트 발생함.');
})
console.log('2초 후에 시스템 종료를 시도함.');
setTimeout(function() {
    process.exit();
}, 2000);

// 'tick' is user-created event (<> 'exit' is default event provided by Node)
process.on('tick', function(count) {
    console.log('tick 이벤트 발생함 : %s', count);
})
console.log('1.5초 후에 tick 이벤트 전달을 시도함.');
setTimeout(function() {
    process.emit('tick', '1.5');
}, 1500)

var Calc = require('./calc');

var calc = new Calc();
calc.emit('stop');

console.log(Calc.title + '에 stop 이벤트 전달함.');
console.log(Calc.title + '에서 더하기 연산함 : %d', calc.add(5, 10));