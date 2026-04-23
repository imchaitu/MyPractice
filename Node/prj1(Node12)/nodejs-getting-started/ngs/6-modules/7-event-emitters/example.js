const EventEmitter = require('events');

// Streams are Event Emitters
// process.stdin, process.stdout

const myEmitter = new EventEmitter();

myEmitter.emit('TEST_EVENT');

// setImmediate(() => {
//   myEmitter.emit('TEST_EVENT');
// });

myEmitter.on('TEST_EVENT', () => {
  console.log('TEST_EVENT was emitted!');
});

myEmitter.emit('TEST_EVENT');

myEmitter.emit('TEST_EVENT');

myEmitter.emit('NOT_TEST_EVENT');