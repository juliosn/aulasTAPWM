const express = require("express")
const criacao = require("./banco.js")
const app = express()

app.listen(8081, function(){
    console.log("O servidor está ativo!")
})
 
app.get("/", function(req, res){
    res.end("Página Inicial");
})

app.get("/cadastrar", function(req, res){
    res.end("Página de Cadastro");
})

app.get("/cadastrar/:nome/:endereco?/:bairro?/:cep?/:cidade?/:estado?/:observacao?", function(req, res){
    const { nome, endereco, bairro, cep, cidade, estado, observacao } = req.params;
    
    let mensagem = "Dados Inseridos: <br>" + 
                   "Nome: " + nome + "<br>";

    if (endereco) mensagem += "Endereço: " + endereco + "<br>";
    if (bairro) mensagem += "Bairro: " + bairro + "<br>";
    if (cep) mensagem += "CEP: " + cep + "<br>";
    if (cidade) mensagem += "Cidade: " + cidade + "<br>";
    if (estado) mensagem += "Estado: " + estado + "<br>";
    if (observacao) mensagem += "Observação: " + observacao + "<br>";

    res.send(mensagem);
    criacao(nome, endereco || "", bairro || "", cep || "", cidade || "", estado || "", observacao || "");
});

