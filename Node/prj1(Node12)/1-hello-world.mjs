// Using ES Modules syntax in Node.js (Node 12+)
import http from 'http';

const server = http.createServer((req, res) => {
    res.end('Hello Node...\n');
});

// Using named imports. An alternative to the default import above would be:
//
// import { createServer } from 'http';
// const server = createServer((req, res) => {
//    res.end('Hello Node...\n');
// });

server.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});