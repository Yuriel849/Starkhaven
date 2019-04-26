// Microtasks -> process.nextTick & Promise -> prioritized over setImmediate & setTimeout
              // Microtasks are placed in a separate microtask queue, parallel to task queue (unlike tasks, no background)
    // Sequence 'nextTick' -> 'promise' -> 'timeout' -> 'immediate'


setImmediate(() => {
    console.log('immediate');
});

setTimeout(() => {
    console.log('timeout')
}, 0);

process.nextTick(() => {
    console.log('nextTick');
});

Promise.resolve().then(() => console.log('promise'));