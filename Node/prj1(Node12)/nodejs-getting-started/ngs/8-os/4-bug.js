function convertArrayToObject(arr) {
  return arr.reduce((curr, acc) => {
    acc[curr[0]] = curr[1];
    return acc;
  }, {});
}

const obj = convertArrayToObject([
  [1, 'One'],
  [2, 'Two'],
  [3, 'Three'],
  [4, 'Four'],
  [5, 'Five'],
]);

console.log(obj);
/* Should output:

  {
    1: 'One',
    2: 'Two',
    3: 'Three'
    4: 'Four',
    5: 'Five'
  }

*/


// Run: node --inspect-brk 4-bug.js
// Open "chrome://inspect" in chrome.
// You should see a link to Node debugger and clicking on it, browser debugger will open for your Node code.
