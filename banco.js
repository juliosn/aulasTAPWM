const Sequelize = require("sequelize")
const sequelize = new Sequelize("exemplo", "root", "",{
    host: "localhost",
    dialect: "mysql"
})
 
sequelize.authenticate().then(function(){
    console.log("Servidor Ativo!")
}).catch(function(erro){
    console.log("Falha de conexão: " + erro)
})
 
const agendamentos = sequelize.define("agendamentos",{
    nome:{
        type: Sequelize.STRING
    },
    endereco:{
        type: Sequelize.STRING
    },
    bairro:{
        type: Sequelize.STRING
    },
    cep:{
        type: Sequelize.STRING
    },
    cidade:{
        type: Sequelize.STRING
    },
    estado:{
        type: Sequelize.STRING
    },
    observacao:{
        type: Sequelize.STRING
    }
 
})

//agendamentos.sync({force: true})
agendamentos.sync({})

agendamentos.create({
    nome: "Júlio Neves",
    endereco: "Rua Sebastião da Decepção",
    bairro: "Jardim Peri",
    cep: "03033-0303",
    cidade: "São Paulo",
    estado: "SP",
    observacao: "Testando..."
}) 