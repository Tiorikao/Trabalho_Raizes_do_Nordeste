# Trabalho - Raízes do Nordeste

API REST desenvolvida em Spring Boot para o Projeto Multidisciplinar da disciplina Projeto Multidisciplinar Back-End da UNINTER.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Eclipse IDE
- Postman

## Como executar o projeto

1. Clone este repositório:

```bash
git clone https://github.com/Tiorikao/Trabalho_Raizes_do_Nordeste.git
```

2. Abra o projeto no Eclipse.

3. Configure o banco de dados MySQL.

4. Edite o arquivo:

```
src/main/resources/application.properties
```

com as configurações do seu banco de dados.

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/raizes_nordeste
spring.datasource.username=root
spring.datasource.password=sua_senha
```

5. Execute a classe:

```
TarefaapiApplication.java
```

6. A aplicação estará disponível em:

```
http://localhost:8083
```

## Endpoints da API

### Clientes

| Método | Endpoint |
|---------|----------|
| GET | /clientes |
| POST | /clientes |
| PUT | /clientes/{id} |
| DELETE | /clientes/{id} |

### Produtos

| Método | Endpoint |
|---------|----------|
| GET | /produtos |
| POST | /produtos |
| PUT | /produtos/{id} |
| DELETE | /produtos/{id} |

### Pedidos

| Método | Endpoint |
|---------|----------|
| GET | /pedidos |
| POST | /pedidos |
| PUT | /pedidos/{id} |
| DELETE | /pedidos/{id} |

## Ferramentas utilizadas

- Eclipse IDE
- Git
- GitHub
- Postman
- MySQL Workbench

## Autor

**Enrique Ferreira Bernardo**

RU: **4680241**

Projeto desenvolvido para a disciplina **Projeto Multidisciplinar Back-End - UNINTER**.
