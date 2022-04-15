# Quake Log Parser

## Descrição

O projeto consiste na exposição de uma API REST para consulta de kills por partida extraídas do log do jogo Quake 3 Arena.
As informações do log são extraídas e disponibilizadas pela API no formato JSON.

## Arquitetura

Abaixo a descrição da arquitetura escolhida para a solução.

- Linguagem de programação Java versão 8
- Container Web Apache Tomcat versão 9
- RESTEasy - Framework para construção de API REST
- Gson - Biblioteca Java para converção de Objetos para o formato JSON
- JUnit - Framework para o desenvolvimento de testes unitários

## Instalação

Gerar o pacote (war) do projeto através do Maven. O camando irá executar os testes unitários criados para o projeto.

	mvn clean install

Fazer o deploy do pacote gerado no container.
	
## Utilização

A API REST expõe 3 operações para consulta de kills por partida.

### Consultar todas as partidas

	http://<host>:<porta>/quake-log-parser/rest/quakelog
	
### Consultar partida por número

	http://<host>:<porta>/quake-log-parser/rest/quakelog/game/<gameNumber>
	
### Consultar partidas por nome do jogador

	http://<host>:<porta>/quake-log-parser/rest/quakelog/player/<player>