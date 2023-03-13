import * as http from 'http';
import * as fs from 'fs/promises';
import * as pg from 'pg';


const pool = new pg.Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'linketinder',
    password: '179550',
    port: 5432,
  });

const server = http.createServer((req:http.IncomingMessage, res:http.ServerResponse)=>{
    const headers = {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST',
        'Access-Control-Max-Age': 2592000, 
      };

    if (req.method == 'GET'){
        if(req.url === "/index.html" || req.url === "/"){
        fs.readFile(`${process.cwd()}/dist/index.html`)
      
        .then(contents => {
            res.setHeader("Content-Type", "text/html");
            res.writeHead(200, headers);
            res.end(contents);
        })
    }
    else if(req.url === "/index.css"){
        fs.readFile(`${process.cwd()}/dist/index.css`)
        .then(contents => {
            res.setHeader("Content-Type", "text/css");
            res.writeHead(200, headers);
            res.end(contents);
        })
    }
    
    else if(req.url === "/app.min.js"){
        fs.readFile(`${process.cwd()}/dist/app.min.js`)
        .then(contents => {
            res.setHeader("Content-Type", "text/javascript");
            res.writeHead(200, headers);
            res.end(contents);
        })
    }
    }

    if(req.method == 'POST'){
        
        let requestBody = '';
        req.on('data', (chunk) => {
          requestBody += chunk.toString();
        });
        req.on('end', () => {
            const payload = JSON.parse(requestBody);
          res.end('Requisição POST recebida!');

        const sql:string =  'INSERT INTO users (name, email, country, state, password, category, doc, zipcode) VALUES ($1, $2, $3, $4, $5, $6, $7, $8)'
        const values = [payload.userName, payload.email, payload.country, payload.state, 'senha', payload.qualification? "candidate" : "enterprise", payload.doc, payload.zipCode]

          pool.query(sql, values, (err, result)=>{
            if (err) {
                console.error('Erro ao inserir os dados no banco de dados:', err.stack);
                res.statusCode = 500;
                res.end();
              } else {
                console.log('Dados inseridos com sucesso!');
                res.statusCode = 201;
                res.end();
              }
          })
        });
    }
})

server.listen(3000, ()=> {
    console.log("Server is Running")    

})