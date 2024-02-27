const express = require("express")
const app = express()

app.listen(8081, function(){
    console.log("Servidor rodando na porta 8081")
})

app.get("/cadastrar", function(req, res){
    res.end("Página de cadastro")
})

app.get("/cadastrar/:produto", function(req, res){
    res.end("Produto: " + req.params.produto)
})

app.get("/cadastrar/:produto/:modelo", function(req, res){
    res.end("Produto: " + req.params.produto +  "\nModelo: "  + req.params.modelo)
})

app.get("/contato", function(req, res){
    res.end("Página de contato")
})


app.get("/contato/:sac", function(req, res){
    res.end("Página de contato. \nValor do SAC:" + req.params.sac)
})

app.get("/contato/:sac", function(req, res){
    res.end("Página de contato. \nValor do SAC:" + req.params.sac + "\nProduto: " + req.params.produto)
})
