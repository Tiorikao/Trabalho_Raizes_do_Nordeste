# Trabalho - Raízes do Nordeste

API REST desenvolvida em Spring Boot para o Projeto Multidisciplinar da UNINTER.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Como executar

1. Clone o repositório

```
git clone https://github.com/Tiorikao/Trabalho_Raizes_do_Nordeste.git
```

2. Abra o projeto no Eclipse

3. Configure o banco de dados MySQL.

4. Altere o arquivo:

```
src/main/resources/application.properties
```

com seu usuário e senha do MySQL.

Exemplo:

```
spring.datasource.url=jdbc:mysql://localhost:3306/raizes
spring.datasource.username=root
spring.datasource.password=suaSenha
```

5. Execute a classe

```
TarefaapiApplication.java
```

6. A API estará disponível em

```
http://localhost:8083
```

## Endpoints disponíveis

### Cliente

- GET /clientes
- POST /clientes
- PUT /clientes/{id}
- DELETE /clientes/{id}

### Produto

- GET /produtos
- POST /produtos
- PUT /produtos/{id}
- DELETE /produtos/{id}

### Pedido

- GET /pedidos
- POST /pedidos
- PUT /pedidos/{id}
- DELETE /pedidos/{id}

## Autor

Enrique Ferreira Bernardo

Projeto desenvolvido para a disciplina Projeto Multidisciplinar - Back-End - UNINTER.
