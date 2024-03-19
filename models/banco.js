const Sequelize = require("sequelize");

// Criando uma instância do Sequelize para se conectar ao banco de dados MySQL
const sequelize = new Sequelize("test", "root", "", {
    host: "localhost",
    dialect: "mysql"
});

// Exportando o Sequelize e a instância sequelize para uso em outras partes do aplicativo
module.exports = {
    Sequelize: Sequelize,
    sequelize: sequelize
};
