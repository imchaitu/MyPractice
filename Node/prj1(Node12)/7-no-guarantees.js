setTimeout(
    () => console.log('Hello after 0.5 seconds. MAYBE!'),
    500,
);

for (let i = 0; i < 1e10; i++) {
    // Blocking Node Synchronously for a long time
}

// The above code will block the event loop, and the setTimeout callback will not execute until the loop finishes.
// Therefore, "Hello after 0.5 seconds. MAYBE!" will be logged much later than expected, demonstrating that there are no guarantees on when the callback will be executed if the event loop is blocked.