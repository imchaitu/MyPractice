const timerId = setTimeout(
    () => console.log('This will not be logged'),
    0
);

// setImmediate -> setTimeout with 0 delay

clearTimeout(timerId);
// clearInterval -> For setInterval timers
// clearImmediate -> For setImmediate timers