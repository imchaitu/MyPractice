process.stdin.on('readable', () => {
    const chunk = process.stdin.read();
    if (chunk !== null) {
        process.stdout.write(`You entered: ${chunk}`);
    }
});

// OR
// process.stdin.pipe(process.stdout);