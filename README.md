# Trabalho - Raízes do Nordeste

API REST desenvolvida em Spring Boot para o Projeto Multidisciplinar da disciplina Projeto Multidisciplinar Back-End da UNINTER.

O projeto tem como objetivo atender ao cenário da empresa fictícia Raízes do Nordeste, permitindo o gerenciamento básico de clientes, produtos, pedidos, usuários e perfis de acesso por meio de uma API REST com persistência em banco de dados MySQL.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Swagger/OpenAPI
- GitHub

## Funcionalidades implementadas

A aplicação possui as seguintes funcionalidades principais:

- Cadastro, listagem, atualização e exclusão de clientes.
- Cadastro, listagem, atualização e exclusão de produtos.
- Cadastro, listagem, atualização e exclusão de pedidos.
- Registro do canal de origem do pedido por meio do campo `canalPedido`.
- Controle de status do pedido por meio do campo `statusPedido`.
- Controle de status do pagamento por meio do campo `statusPagamento`.
- Atualização específica do status do pedido.
- Simulação de pagamento mock aprovado ou recusado.
- Cadastro, listagem, atualização, exclusão e busca de usuários.
- Modelagem de perfis de acesso por meio do campo `role`.
- Documentação dos endpoints com Swagger/OpenAPI.
- Collection do Postman incluída no repositório para testes da API.

## Observação sobre autenticação e perfis

Nesta versão do MVP, os perfis de acesso foram modelados por meio da entidade `Usuario` e do campo `role`, permitindo representar papéis como `ADMIN`, `GERENTE`, `ATENDENTE`, `COZINHA` e `CLIENTE`.

A autenticação JWT e o bloqueio efetivo de rotas por perfil não foram implementados nesta versão, sendo previstos como evolução futura do projeto.

## Estrutura principal do projeto

```text
src/main/java/com/example/tarefaapi
```

Pacotes principais:

```text
controller
model
repository
```

Entidades principais:

```text
Cliente
Produto
Pedido
Usuario
```

Controllers principais:

```text
ClienteController
ProdutoController
PedidoController
UsuarioController
```

Repositories principais:

```text
ClienteRepository
ProdutoRepository
PedidoRepository
UsuarioRepository
```

## Endpoints de clientes

### Listar clientes

```http
GET /clientes
```

### Cadastrar cliente

```http
POST /clientes
```

Exemplo de body:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "telefone": "11999999999"
}
```

## Endpoints de produtos

### Listar produtos

```http
GET /produtos
```

### Cadastrar produto

```http
POST /produtos
```

Exemplo de body:

```json
{
  "nome": "Cuscuz Premium",
  "descricao": "Produto típico nordestino",
  "preco": 18.50,
  "estoque": 20
}
```

### Atualizar produto

```http
PUT /produtos/{id}
```

### Excluir produto

```http
DELETE /produtos/{id}
```

## Endpoints de pedidos

### Listar pedidos

```http
GET /pedidos
```

### Cadastrar pedido

```http
POST /pedidos
```

Exemplo de body:

```json
{
  "cliente": "Enrique",
  "produto": "Cuscuz Premium",
  "quantidade": 5,
  "valorTotal": 92.50,
  "canalPedido": "TOTEM",
  "statusPedido": "CRIADO",
  "statusPagamento": "AGUARDANDO_PAGAMENTO"
}
```

### Atualizar pedido

```http
PUT /pedidos/{id}
```

### Excluir pedido

```http
DELETE /pedidos/{id}
```

### Atualizar status do pedido

```http
PUT /pedidos/{id}/status
```

Exemplo de body:

```json
{
  "statusPedido": "EM_PREPARO"
}
```

### Pagamento mock

```http
POST /pedidos/{id}/pagamentos/mock
```

Exemplo de pagamento aprovado:

```json
{
  "statusPagamento": "APROVADO"
}
```

Resposta esperada:

```json
{
  "pedidoId": 7,
  "statusPagamento": "APROVADO",
  "statusPedido": "PAGO",
  "mensagem": "Pagamento mock aprovado com sucesso"
}
```

Exemplo de pagamento recusado:

```json
{
  "statusPagamento": "RECUSADO"
}
```

Resposta esperada:

```json
{
  "pedidoId": 9,
  "statusPagamento": "RECUSADO",
  "statusPedido": "PAGAMENTO_RECUSADO",
  "mensagem": "Pagamento mock recusado"
}
```

## Endpoints de usuários e perfis

### Listar usuários

```http
GET /usuarios
```

### Cadastrar usuário

```http
POST /usuarios
```

Exemplo de usuário administrador:

```json
{
  "nome": "Administrador",
  "email": "admin@raizes.com",
  "senha": "123456",
  "role": "ADMIN"
}
```

Exemplo de usuário atendente:

```json
{
  "nome": "Atendente",
  "email": "atendente@raizes.com",
  "senha": "123456",
  "role": "ATENDENTE"
}
```

### Atualizar usuário

```http
PUT /usuarios/{id}
```

Exemplo de body:

```json
{
  "nome": "Atendente Atualizado",
  "email": "atendente@raizes.com",
  "senha": "123456",
  "role": "GERENTE"
}
```

### Excluir usuário

```http
DELETE /usuarios/{id}
```

### Buscar usuário por email

```http
GET /usuarios/email/{email}
```

Exemplo:

```http
GET /usuarios/email/admin@raizes.com
```

## Swagger/OpenAPI

A documentação da API pode ser acessada com o projeto em execução pelo endereço:

```text
http://localhost:8083/swagger-ui/index.html
```

O Swagger permite visualizar e testar os endpoints implementados na aplicação.

## Postman

A collection do Postman foi exportada e incluída no repositório com o nome:

```text
Trabalho_Raizes_do_Nordeste_Postman.json
```

Essa collection contém requisições para testar os principais endpoints da API, incluindo clientes, produtos, pedidos, atualização de status, pagamento mock e usuários com perfis.

## Banco de dados

A aplicação utiliza banco de dados MySQL para persistência das informações.

As tabelas principais utilizadas são:

```text
clientes
produtos
pedidos
usuarios
```

## Como executar o projeto

1. Clonar o repositório.
2. Abrir o projeto em uma IDE compatível com Java e Spring Boot, como Eclipse ou IntelliJ.
3. Configurar o banco de dados MySQL no arquivo `application.properties`.
4. Executar a classe principal da aplicação.
5. Acessar os endpoints pelo Postman ou pelo Swagger.

## Evoluções futuras

Como evolução futura, o projeto pode receber:

- Autenticação com JWT.
- Bloqueio efetivo de rotas por perfil de usuário.
- Criptografia de senhas.
- Controle de estoque por unidade.
- Programa de fidelização.
- Logs de auditoria.
- Tratamento padronizado de erros.
- Consultas avançadas por canal do pedido e status.

## Autor

Enrique Ferreira Bernardo  
RU: 4680241
