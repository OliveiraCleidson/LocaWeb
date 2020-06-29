# LocaWeb
Um projeto de locadora de jogos, desenvolvido em Java, utilizando JavaFX e MySql (JDBC), arquitetura MVC e design pattern DAO para a disciplina de Tecnicas de Programação 1.

# Detalhes
Fiz este projeto em 2018, não tinha muito conhecimento na época por tanto não preparei ele para outras pessoas rodarem, para executar ele em seu computador será necessário possuir um servidor MySQL, criar o banco de dados manualmente e configurá-lo no arquivo db.properties

Não utilizei ORM por querer aprender como funcionava o JDBC e o padrão DAO, estava aprendendo sobre design patterns naquele tempo e não tinha conhecimento de Clean Code. Este projeto é monolítico e cheio de gambiarras (eu não sabia usar event e callbacks, por tanto dei meu jeito pra funcionar).

### Conceitos
Fiz validação de dados direto no FrontEnd com gambiarras, meu padrão DAO talvez esteja 'cagado', mas aprendi bastante sobre interface, chaves estrangeiras podem estar bagunçadas e não sabia o conceito/usar Join.

# Você tem total liberdade para modificar este projeto como preferir
Caso tenha caído aqui de parequedas, aqui tem um exemplo (não muito bom) de como utilizar JavaFX. Se quiser mandar PR colocando ORM, refatorar o código para também aprender como eu aprendi, fique a vontade, se quiser falar comigo e tirar dúvidas meu email está na descrição do perfil. 

# Como Contribuir
1. Fork este Repositorio
2. Clone para seu computador
```sh
$ git clone git@github.com:OliveiraCleidson/LocaWeb.git 'your-folder'
$ cd `your-folder`
```
3. Crie uma nova Branch para contribuir
```sh
$ git checkout -b branching_name_to_contribute
```
4. Commit e Pull Request!
