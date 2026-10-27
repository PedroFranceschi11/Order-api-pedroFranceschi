# Order API

API REST em **Java / Spring Boot** para gestão de clientes e pedidos de um representante comercial regional.

Projeto desenvolvido por mim, do levantamento do problema até a implementação, a partir de uma necessidade real de negócio.

## O problema

O cliente é um representante comercial que atende **duas empresas totalmente distintas**, com muitos catálogos de produtos. Todo o controle era feito manualmente pelo WhatsApp, o que gerava dificuldade para saber:

- quem fez cada pedido e o que pediu;
- quando o pedido foi feito;
- de onde veio o pedido.
- qual fornecedor.

## A solução

Uma API que centraliza clientes, produtos, fornecedores e suas localizações e pedidos em um banco de dados relacional, com regras de negócio aplicadas no back-end e estrutura de banco versionada.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Flyway (versionamento e migração do banco)
- Swagger / OpenAPI (documentação dos endpoints)
- Maven
- Git e GitHub

## Destaques técnicos

- Modelagem do banco em três níveis (conceitual, lógico e físico) e fluxo desenhado em BPMN antes da implementação
- Tabelas criadas e versionadas por migrations com **Flyway**
- Organização do código seguindo princípios **SOLID**
- Regras de negócio separadas da camada de controle
- Tratamento global de exceções padronizado

## Como rodar localmente

### Pré-requisitos

- JDK 21
- PostgreSQL
- Maven (ou use o wrapper `./mvnw` incluído no projeto)

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/PedroFranceschi11/Order-api-pedroFranceschi.git
cd Order-api-pedroFranceschi
```

2. Crie um banco no PostgreSQL, por exemplo `order_api`.

3. Configure a conexão em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/order_api
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

4. Execute a aplicação (o Flyway cria as tabelas automaticamente na primeira execução):

```bash
./mvnw spring-boot:run
```

5. Acesse a documentação dos endpoints:

```
http://localhost:8080/swagger-ui/index.html
```

<!-- AJUSTE: confirme a URL do Swagger e a porta no seu projeto -->

## Estrutura do projeto

```
src/main/java      -> código da aplicação (controllers, services, repositories, entidades)
src/main/resources -> configurações e migrations do Flyway
```

## Status

🚧 Em desenvolvimento. Hoje a API já possui:

- CRUD básico (GET, POST, PUT) de todas entidades principais
- Banco de dados modelado e versionado com Flyway
- Regras de negócio iniciais e organização seguindo SOLID

Em construção:

- [ ] Fluxo completo de criação e acompanhamento de pedidos
- [ ] Padronização do tratamento global de exceções
- [ ] Testes automatizados
## Autor

**Pedro Franceschi**
Estudante de Análise e Desenvolvimento de Sistemas

[LinkedIn](https://www.linkedin.com/in/pedro-franceschi11/) · [GitHub](https://github.com/PedroFranceschi11)
