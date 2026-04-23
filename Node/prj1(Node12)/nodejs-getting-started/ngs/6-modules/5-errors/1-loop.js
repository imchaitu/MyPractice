const path = require('path');
const fs = require('fs');

const files = ['.bashrc', '.bash_history'];

files.forEach(file => {
  const filePath = path.resolve(process.env.HOME, file);
  const data = fs.readFileSync(filePath);
  console.log('File data is', data);
});
