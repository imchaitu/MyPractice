// VAL1=10 VAL2=20 node 9-custom-env-variables.js
//
// OR
//
// export VAL1=10
// export VAL2=20
// node 9-custom-env-variables.js

console.log('Current user is', process.env.USER);

console.log('\nScript executed with:');
console.log('VAL1 equals to:', process.env.VAL1);
console.log('VAL2 equals to:', process.env.VAL2);


// process.argv -> Command line arguments passed to the script
// First item in the array is the path to the Node.js executable and the second item is the path to the script being executed. Any additional items are the arguments passed to the script.
// For example, if you run `node 9-custom-env-variables.js arg1 arg2`, then process.argv will be an array like ['path/to/node', 'path/to/9-custom-env-variables.js', 'arg1', 'arg2'].
console.log(process.argv)