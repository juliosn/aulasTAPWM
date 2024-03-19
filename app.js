const express = require('express');
const app = express();
const handlebars = require('express-handlebars').engine;
const bodyParser = require("body-parser");
const post = require("./models/post");
const sequelize = require("./models/banco").sequelize;

// Configuração do Handlebars
app.engine('handlebars', handlebars({
    defaultLayout: 'main',
    helpers: {
        // Função helper para verificação de condição no Handlebars
        ifCond: function (v1, v2, options) {
            if (v1 === v2) {
                return options.fn(this);
            }
            return options.inverse(this);
        }
    }
}));

app.set('view engine', 'handlebars');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

// Rota principal
app.get('/', function (req, res) {
    res.render('index');
});

// Rota para ir para a página de atualização de um agendamento
app.get('/atualizar', function (req, res) {
    const id = req.query.id;
    sequelize.query('SELECT * FROM agendamentos WHERE id = ?', { 
        replacements: [id], 
        type: sequelize.QueryTypes.SELECT 
    })
    .then(function([agendamento]) {
        if (agendamento) {
            // Converter para o fuso horário local
            const dataContatoLocal = new Date(agendamento.data_contato.getTime() - agendamento.data_contato.getTimezoneOffset() * 60000);
            // Formatando a data no formato 'YYYY-MM-DD'
            agendamento.data_contato = dataContatoLocal.toISOString().split('T')[0];

            // Definindo todas as opções de origem
            const origens = ['Celular', 'Fixo', 'Whatsapp', 'Facebook', 'Instagram', 'Google Meu Negocio'];

            // Renderiza a view 'atualizar' passando o agendamento e as opções de origem como contexto
            res.render('atualizar', { agendamento: agendamento, origens: origens });
        } else {
            res.send('Agendamento não encontrado.');
        }
    })
    .catch(function(erro) {
        res.send("Falha ao buscar os dados: " + erro);
    });
});

// Rota para consultar todos os agendamentos
app.get('/consultar', function (req, res) {
    sequelize.query('SELECT * FROM agendamentos')
        .then(function([results, metadata]) {
            // Formatando a data para cada agendamento
            results.forEach(function(agendamento) {
                agendamento.data_contato = agendamento.data_contato.toLocaleDateString();
            });

            // Renderiza a view 'consultar' passando os resultados como contexto
            res.render('consultar', { agendamentos: results });
        })
        .catch(function(erro) {
            res.send("Falha ao listar os dados: " + erro);
        });
});

// Rota para cadastrar um novo agendamento
app.post('/cadastrar', function (req, res) {
    const { nome, telefone, origem, data_contato, observacao } = req.body;

    post.create({
        nome: nome,
        telefone: telefone,
        origem: origem,
        data_contato: data_contato,
        observacao: observacao  
    }).then(function(){
        res.send(
        `<script>
            alert("Cadastro realizado com sucesso!");
            window.location.href = "/";
        </script>`
        );
    }).catch(function(erro){
        res.send("Falha ao cadastrar os dados: " + erro)
    });
});

// Rota para atualizar um agendamento existente
app.post('/atualizar', function (req, res) {
    const id = req.body.id;
    const { nome, telefone, origem, data_contato, observacao } = req.body;

    // Convertendo a data do formato YYYY-MM-DD para um objeto Date
    const dataContato = new Date(data_contato + 'T00:00:00'); // Adicionando a hora para evitar inconsistências de fuso horário

    post.update({
        nome: nome,
        telefone: telefone,
        origem: origem,
        data_contato: dataContato, // Usando a data convertida
        observacao: observacao
    }, {
        where: {
            id: id
        }
    })
    .then(function() {
        res.redirect('/consultar');
    })
    .catch(function(erro) {
        res.send("Falha ao atualizar o registro: " + erro);
    });
});

// Rota para excluir um agendamento
app.post('/excluir', function (req, res) {
    const id = req.body.id;

    post.destroy({
        where: {
            id: id
        }
    })
    .then(function() {
        res.redirect('/consultar');
    })
    .catch(function(erro) {
        res.send("Falha ao excluir o registro: " + erro);
    });
});

// Inicia o servidor na porta 8081
app.listen(8081, function () {
    console.log('Servidor funcionando!');
});
