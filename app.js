const express = require("express")
const app = express()
const handlebars = require("express-handlebars").engine
const bodyParser = require("body-parser")
const { initializeApp, applicationDefault, cert } = require('firebase-admin/app')
const { getFirestore, Timestamp, FieldValue } = require('firebase-admin/firestore')

const serviceAccount = require('./firebase/chave-firebase.json')

initializeApp({
  credential: cert(serviceAccount)
})

const db = getFirestore()

app.engine("handlebars", handlebars({
    defaultLayout: "main"
}))

app.set("view engine", "handlebars")

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json())

app.get("/", function(req, res){
    res.render("index")
})

app.post("/cadastrar", function(req, res){
    var result = db.collection('agendamentos').add({
        nome: req.body.nome,
        telefone: req.body.telefone,
        origem: req.body.origem,
        data_contato: req.body.data_contato,
        observacao: req.body.observacao
    }).then(function(){
        console.log('Documento adicionado!');
        res.redirect('/')
    })
})

app.get("/consulta", function(req, res){
    db.collection('agendamentos').get()
        .then((snapshot) => {
            const agendamentos = [];
            snapshot.forEach((doc) => {
                agendamentos.push({
                    id: doc.id,
                    data: doc.data()
                });
            });
            res.render("consultar", { agendamentos: agendamentos });
        })
        .catch((error) => {
            console.log("Erro ao recuperar dados:", error);
            res.status(500).send("Erro ao recuperar dados");
        });
});


app.get("/editar/:id", function(req, res){
    const agendamentoId = req.params.id;

    db.collection('agendamentos').doc(agendamentoId).get()
        .then((doc) => {
            if (!doc.exists) {
                res.status(404).send("Agendamento não encontrado");
            } else {
                res.render("editar", { agendamentos: { id: doc.id, data: doc.data() } });
            }
        })
        .catch((error) => {
            console.log("Erro ao recuperar agendamento:", error);
            res.status(500).send("Erro ao recuperar agendamento");
        });
});

app.post("/atualizar", function(req, res){
    const agendamentoId = req.body.id;

    const agendamentoAtualizado = {
        nome: req.body.nome,
        telefone: req.body.telefone,
        origem: req.body.origem,
        data_contato: req.body.data_contato,
        observacao: req.body.observacao
    };

    db.collection('agendamentos').doc(agendamentoId).update(agendamentoAtualizado)
        .then(() => {
            console.log('Documento atualizado com sucesso');
            res.redirect('/consulta');
        })
        .catch((error) => {
            console.log("Erro ao atualizar documento:", error);
            res.status(500).send("Erro ao atualizar documento");
    });
})

app.get("/excluir/:id", function(req, res) {
    const agendamentoId = req.params.id;

    db.collection('agendamentos').doc(agendamentoId).delete()
        .then(() => {
            console.log('Documento deletado com sucesso');
            res.redirect('/consulta');
        })
        .catch((error) => {
            console.log("Erro ao deletar documento:", error);
            res.status(500).send("Erro ao deletar documento");
        });
});

app.listen(8081, function(){
    console.log("Servidor ativo!")
})
