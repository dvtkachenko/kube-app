// This is pretty simple js server to run angular bundle on somewhere we have node.js 
// to run type in shell "node server.js"

const http = require('http');
const path = require('path');
const express = require('express');

const app = express();

app.use(express.static(path.join(__dirname, 'dist/hw-app')));

app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname, 'dist/hw-app/index.html'));
});

// process.env.PORT for Heroku
const port = process.env.PORT || '3000';
app.set('port', port);

const server = http.createServer(app);
server.listen(port, () => console.log('Server is running. Port : ', port));