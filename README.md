# Crud: Handlebars
<h2>Cadastrar</h2>
<img src="https://github.com/juliosn/aulasTAPWM/assets/99426563/e5cad4e5-bb19-487f-bd89-c42b98675f15">

<h2>Consultar</h2>
<img src="https://github.com/juliosn/aulasTAPWM/assets/99426563/d96f2ed5-a25f-41c1-b8d6-239e552c3aab">

<h2>Atualizar</h2>
<img src="https://github.com/juliosn/aulasTAPWM/assets/99426563/1c7d766c-347a-460d-98f7-7b1ee2fc0f1d">

<h2>Estrutura do projeto</h2>
<img src="https://github.com/juliosn/aulasTAPWM/assets/99426563/6b9a2a12-2d99-4112-92d1-c98b2874c232">


> Projeto Web que consiste em um CRUD utilizando Node.js, Express.js, Express-Handlebars, MySQL e Sequelize.


### Melhorias e ajustes
O projeto ainda está em desenvolvimento, ainda tendo as seguintes pendências:

- [ ] Otimizar desempenho ao realizar as requisições
- [ ] Obter maneiras de proteger os dados cruciais dos registros
- [ ] Corrigir falhas de inserção de dados do formulário e das rotas

## 💻 Pré-Requisitos

Antes de utilizar verifique se você possui as ferramentas a seguir:

- Versão 18.16.0 ou superior do Node.JS
- Versão 18.16.0 ou superior do XAMPP

## ☕ Instalando o Crud-Handlebars

Para utilizar o Crud-Handlebars, siga as seguintes etapas:

- Baixe o projeto - https://github.com/juliosn/aulasTAPWM/tree/8-18_03-CRUD_Handlebars
- Acesse o diretório do projeto através do prompt de comando (CMD)
- Utilize os comandos na ordem a seguir:
  - npm install express --save
  - npm install express-handlebars --save
  - npm install sequelize --save
  - npm install mysql2 --save
  - npm install body-parser --save
  - No Xampp, crie um banco de dados com o nome 'test'. Caso queira alterar o nome do banco, vá em 'models/banco.js' e altere o seguinte parâmetro:
  ![img4](https://github.com/juliosn/aulasTAPWM/assets/99426563/ee861bbc-3fd3-4dae-937b-c2af82f4a588)
    >Obs.: Caso você tenha alterado as configurações do ambiente seu XAMPP, altere os valores necessários neste arquivo.

- Caso você utilize o VsCode como sua IDE, utilize o comando: 'code .' para abrir o projeto dentro da IDE
- Vá no arquivo 'post.js' e remova as barras '//' do seguinte comentário:
  ![img5](https://github.com/juliosn/aulasTAPWM/assets/99426563/7cf71202-d5ba-4e35-aa42-cfdbdb98fc27)
    >Assim, você poderá criar a tabela agendamentos. OBS.: Coloque as barras na linha apresentada após a execução, caso contrário, o seu banco será recriado a cada ação que você realizar.
- Vá no prompt de comando, utilize o comando: 'cd models'
- Dentro de models, utilize o comando: 'node post.js'. Com isso, você irá criar a tabela agendamentos
- Utilize o comando: 'cd ..' para voltar para o diretório principal
- Utilize o comando: 'node app.js' para ativar o servidor local
- Abra o navegador e insira a seguinte URL: 'http://localhost:8081/'. Agora, você terá acesso ao projeto e suas funcionalidaades!
