let counter = 0;

const intervalId = setInterval(
    () => {
        console.log('Hello World');
        counter++;

        if (counter === 5) {
            console.log('Done'); 
            clearInterval(intervalId);
        }
    },
    1000
);