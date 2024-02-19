/*
Mensagem Inicial - Parte 01:
console.log("Primeira aplicação com node")

Cálculos simples entre duas variáveis - Parte 02:
var n1 = 10
var n2 = 10

var soma = n1 + n2 
var subtracao = n1 - n2
var multiplicacao = n1 * n2
var divisao = n1 / n2

console.log("Dado os seguintes valores: " + n1 + " e " + n2)

console.log("Soma: " + soma)
console.log("Subtração: " + subtracao)
console.log("Multiplicação: " + multiplicacao)
console.log("Divisão: " + divisao)

Estrutura de condição - Parte 03:
if(soma <= 10){
    console.log("O valor da soma é menor que 10")
}else{
    console.log("O valor da soma é maior que 10")
}

Solicitando funções de arquivos externos - Parte 04:
var soma = require('./soma.js')
var subtracao = require('./subtracao.js')
var multiplicacao = require('./multiplicacao.js')
var divisao = require('./divisao.js')

console.log("Soma: " + soma(10,10))
console.log("Subtração: " + subtracao(10,10))
console.log("Multiplicação: " + multiplicacao(10,10))
console.log("Divisão: " + divisao(10,10))
*/

//Criação de servidor local requisição http:
var http = require('http')

http.createServer(function(req, res){
    res.end("Mensagem Direcionada")
}).listen(8081)

console.log("Servidor está ativo!")