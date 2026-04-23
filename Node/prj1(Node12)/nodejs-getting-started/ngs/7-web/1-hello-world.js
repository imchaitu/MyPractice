// Same Hello World example as before, but writtern in low-level for better understanding of how Node.js works under the hood.

const http = require('http');

const requestListener = (req, res) => {
  res.write('Hello World\n');
  res.end();
}
const server = http.createServer();
server.on('request', requestListener);

server.listen(4242, () => {
  console.log('Server is running...');
});


// npm install -g nodemon
// nodemon 1-hello-world.js
// This will automatically restart the server whenever you make changes to the code.
// This is just a development tool and should not be used in production.