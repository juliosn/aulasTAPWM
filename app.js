const express = require("express")
const app = express()
const bodyParser = require('body-parser');
const criacao = require("./banco.js")

// Utilizando o middleware body-parser para analisar corpos de solicitação
// A opção 'extended: true' permite a análise de objetos aninhados em solicitações de formulário
app.use(bodyParser.urlencoded({ extended: true }));

// Utilizando o middleware body-parser para analisar corpos JSON
app.use(bodyParser.json());

app.listen(8081, function(){
    console.log("O servidor está ativo!")
})
 
app.get("/", function(req, res){
    res.end("Página Inicial");
})

app.get("/cadastrar/", function(req, res){
    res.sendFile(__dirname + "/html/index.html")
})

app.post("/cadastrar", function(req, res){
    const { nome, endereco, bairro, cep, cidade, estado, observacao } = req.body;
    
    let mensagem = "Dados Inseridos: <br>" + 
                   "Nome: " + nome + "<br>";

    if (endereco) mensagem += "Endereço: " + endereco + "<br>";
    if (bairro) mensagem += "Bairro: " + bairro + "<br>";
    if (cep) mensagem += "CEP: " + cep + "<br>";
    if (cidade) mensagem += "Cidade: " + cidade + "<br>";
    if (estado) mensagem += "Estado: " + estado + "<br>";
    if (observacao) mensagem += "Observação: " + observacao + "<br>";

    criacao(nome, endereco || "", bairro || "", cep || "", cidade || "", estado || "", observacao || "");

    res.send(`
        <script>
            alert("Cadastro realizado com sucesso!");
            window.location.href = "/cadastrar/";
        </script>
    `);
});