// Importando o módulo que contém a instância do Sequelize
const db = require("./banco");

// Definindo o modelo Agendamentos usando a instância do Sequelize
const Agendamentos = db.sequelize.define('agendamentos', {
    // Definindo os campos da tabela 'agendamentos'
    nome: {
        type: db.Sequelize.STRING // Campo para o nome do agendamento
    },
    telefone: {
        type: db.Sequelize.STRING // Campo para o telefone do agendamento
    },
    origem: {
        type: db.Sequelize.STRING // Campo para a origem do agendamento
    },
    data_contato: {
        type: db.Sequelize.DATE // Campo para a data de contato do agendamento
    },
    observacao: {
        type: db.Sequelize.TEXT // Campo para observações do agendamento
    }
});

// Sincronizando o modelo com o banco de dados:
//Agendamentos.sync({force: true})
//Obs.: A opção {force: true} recria a tabela sempre que o aplicativo é iniciado

// Exportando o modelo Agendamentos para uso em outras partes do aplicativo
module.exports = Agendamentos;
