const path = require('path');
const fs = require('fs');

const files = ['.bashrc', 'kjkjhh', '.bash_history'];

files.forEach(file => {
  try {
    const filePath = path.resolve(process.env.HOME, file);
    const data = fs.readFileSync(filePath);
    console.log('File data is', data);
  } catch (err) {
    console.log('File not found');
  }
});
