const msg = delay => {
    console.log(`Hello after ${delay} seconds`);
}

setTimeout(msg, 4000, 4);
setTimeout(msg, 8000, 8);