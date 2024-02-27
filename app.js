/*var n1 = 10
var n2 = "teste"

if(n1 < 10){
    console.log("N1 menor que 10")
}else{
    console.log("N1 igual ou maior que 10")
}

for(var n3 = 0; n3 <= 10; n3++){
    console.log("N3: " + n3)
}

var n4 = 0

while(n4 <= 10){
    console.log("N4: " + n4)
    n4++
}*/

const express = require("express")
const app = express()

app.listen(8081, function(){
    console.log("Servidor rodando na porta 8081")
})

app.get("/", function(req, res){
    res.end("Seja bem vindo ao Node JS")
})

/*app.get("/cadastrar", function(req, res){
    res.end("Página de cadastro")
})*/

//1 parâmetro
app.get("/cadastrar/:item", function(req, res){
    res.end(req.params.item)
})

//2 parâmetros
app.get("/cadastrar/:item/:qtd", function(req, res){
    res.end("Item: " + req.params.item +  "\nQuantidade: "  + req.params.qtd)
})

app.get("/contato", function(req, res){
    res.end("Página de contato")
})

app.get("/visualizar", function(req, res){
    res.end("Página de visualização")
})