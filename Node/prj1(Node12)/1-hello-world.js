// Using commonJS syntax in Node.js (Node 12 and earlier)

const http = require('http');

const server = http.createServer((req, res) => {
    res.end('Hello Node...\n');
});

server.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});