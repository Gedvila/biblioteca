# Biblioteca RESTful API

Este projeto implementa uma API RESTful para gerenciamento de uma biblioteca, permitindo cadastrar livros, usuários e registrar empréstimos e devoluções. Desenvolvido com Spring Boot, Spring Data JPA e H2 Database (para desenvolvimento).

## 🚀 Tecnologias Utilizadas

* **Java 21+**
* **Spring Boot 3.4.5**
* **Spring Data JPA**
* **Hibernate**
* **H2 Database** (para desenvolvimento e testes, dados voláteis)
* **Maven** (Gerenciador de Dependências)

## ✨ Funcionalidades

A API oferece as seguintes funcionalidades principais:

* **Livros:** Cadastro, listagem (com paginação e filtro por gênero), busca por ID.
* **Usuários:** Cadastro de diferentes tipos de usuários (Aluno/Professor), listagem (com paginação), busca por ID.
* **Empréstimos:** Registro de novos empréstimos (associando livros e usuários existentes), registro de devoluções (alterando o status do empréstimo e a disponibilidade do livro), listagem de empréstimos atrasados, busca por ID.

## 🛠️ Como Executar o Projeto

Siga os passos abaixo para configurar e rodar o projeto em sua máquina local.

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

* JDK 24 ou superior
* Maven

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd seu-repositorio
    ```

2.  **Construa o projeto com Maven:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação será iniciada e estará acessível em `http://localhost:8080`.

### Acessando o H2 Console (Banco de Dados em Memória)

Durante o desenvolvimento, o banco de dados H2 é usado em memória. Você pode acessá-lo para visualizar os dados e executar queries.

* Abra seu navegador e acesse: `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:file:./src/main/resources/banco` (ou o que estiver configurado no seu `application.properties`)
* **Usuário:** `sa`
* **Senha:** (deixe em branco)
* Clique em "Connect".

## 📚 Endpoints da API

A API está acessível na porta `8080`.

### 📖 Livros (`/livro`)

| Método | Endpoint        | Descrição                                                                                                                                                                                                                           | Requisição (Body JSON)                                                                                                                                                   | Resposta (Exemplo JSON)                                                                                                                                                                                                                                                           |
|:-------|:----------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `POST` | `/livro`        | Cadastra um novo livro.                                                                                                                                                                                                             | ```json { "titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien", "editora": "HarperCollins", "anoDePublicacao": 1954, "genero": "Fantasia", "disponivel": true } ``` | ```json {"titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien", "editora": "HarperCollins", "anoDePublicacao": 1954, "genero": "Fantasia", "disponivel": true } ``` (Status 201 Created)                                                                            |
| `GET`  | `/livro`        | Lista todos os livros com paginação. <br> Parâmetros de consulta: `page`, `size`, `sort` (ex: `?page=0&size=10&sort=titulo,asc`)                                                                                                    | N/A                                                                                                                                                                      | ```json { "content": [ { "id": 1, "titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien", "editora": "HarperCollins", "anoDePublicacao": 1954, "genero": "Fantasia", "disponivel": true } ], "pageable": { ... }, "last": true, "totalPages": 1, "totalElements": 1, ... } ``` |
| `GET`  | `/livro/{id}`   | Busca um livro pelo seu ID.                                                                                                                                                                                                         | N/A                                                                                                                                                                      | ```json { "id": 1, "titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien", "editora": "HarperCollins", "anoDePublicacao": 1954, "genero": "Fantasia", "disponivel": true } ``` (Status 200 OK) <br> ou Status 404 Not Found se não existir.                                    |
| `GET`  | `/livro/genero` | Lista livros por gênero, onde a primeira letra do parâmetro deve ser obrigatoriamente maiúscula. <br> Parâmetro de consulta: `genero` (obrigatório) e `page`, `size`, `sort` (opcionais). <br> Ex: `?genero=Fantasia&page=0&size=5` | N/A                                                                                                                                                                      | ```json { "content": [ { "id": 1, "titulo": "O Hobbit", "genero": "Fantasia", ... } ], "page": { ... } } ``` (Status 200 OK)|
|`DELETE`|`/livro/{id}`| Exclui um livro pelo ID| N/A | (Status 204 NO CONTENT)

#### Modelo de Json
```
 {
      "titulo": "O Senhor dos Anéis",
      "autor": "J.R.R. Tolkien",
      "editora": "HarperCollins",
      "anoDePublicacao": 1954,
      "genero": "Fantasia",
      "disponivel": true
}
```

### 👤 Usuários (`/usuario`)

| Método | Endpoint             | Descrição                          | Requisição (Body JSON)                                                                | Resposta (Exemplo JSON)                                                                             |
|:-------|:---------------------|:-----------------------------------|:--------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------|
| `POST` | `/usuario/aluno`     | Cria um usuário do tipo aluno      | ``` json { "nome": "João", "telefone": "62999908787", "email": "joao@gmail.com" } ``` | ```json  { "id": 1,"nome": "João", "telefone": "62999908787", "email": "joao@gmail.com","tipo_usuario": "ALUNO"}```(Status 200 OK) |
| `POST` | `/usuario/professor` | Cria um usuário do tipo professor  | ```json { "nome": "João", "telefone": "62999908787", "email": "joao@gmail.com" } ```  | ```json { "id": 1,"nome": "João", "telefone": "62999908787", "email": "joao@gmail.com","tipo_usuario": "PROFESSOR"  }``` (Status 200 OK) | 
| `GET`  | `/usuario`           | Lista todos os Usuarios com paginação. <br> Parâmetros de consulta: `page`, `size`, `sort` (ex: `?page=0&size=10&sort=titulo,asc`) | N/A |  ```json "content": [{"id": 1,"nome": "João", "telefone": "62999908787", "email": "joao@gmail.com","tipo_usuario": "PROFESSOR"...}], "page":{...} ```|
| `GET`  | `/usuario/{id}`           | Busca o usuário por seu ID | N/A |  ```json { "id": 1,"nome": "João", "telefone": "62999908787", "email": "joao@gmail.com","tipo_usuario": "PROFESSOR" } ```|
|`DELETE`|`/usuario/{id}`| Exclui um usuario pelo ID| N/A | (Status 204 NO CONTENT)

#### Modelo de Json
```
{
 "nome": "João",
 "telefone": "62999908787",
 "email": "joao@gmail.com"
}
```

### 📁 Emprestimos (`/emprestimo`)

| Método | Endpoint             | Descrição                          | Requisição (Body JSON)                                                                | Resposta (Exemplo JSON)                                                                             |
|:-------|:---------------------|:-----------------------------------|:--------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------|
|`GET`   |`/emprestimo`|   Lista todos os Emprestimos com paginação. <br> Parâmetros de consulta: `page`, `size`, `sort` (ex: `?page=0&size=10&sort=titulo,asc`) | N/A| ```json {"content":[{"id":1,"dataRetirada":"2025-05-23","dataDevolucao":"2025-07-03"...],"page":{...}```(Status 200 OK)|
|`GET`|`/emprestimo/{id}`| Busca o Emprestimo pelo ID | N/A| | ```json{"id":1,"dataRetirada":"2025-05-23","dataDevolucao":"2025-07-03","idLivro":1,"idUsuario":1,"status":"DEVOLVIDO"}``` (Status 200 OK)|
|`GET`|`/emprestimo/ativo`| Lista todos os Emprestimos com o status de `ATIVO`| N/A| ``` json {"content":[{"id":1,"dataRetirada":"2025-05-23","dataDevolucao":"2025-07-03"...],"page":{...}``` (Status 200 OK)| 
|`GET`|`/emprestimo/atrasados`| Lista todos os Emprestimos com a data de devolução anteriores ao dia atual | N/A| ```json {"content":[{"id":1,"dataRetirada":"2025-05-23","dataDevolucao":"2025-07-03"...],"page":{...}``` (Status 200 OK)|
|`PUT`|`/emprestimo/devolucao{id}`| Registra a devolução de um Livro mudando a sua disponibilidade e o status do Emprestimo| N/A |  ``` json{"id":1,"dataRetirada":"2025-05-23","dataDevolucao":"2025-07-03","idLivro":1,"idUsuario":1,"status":"DEVOLVIDO"}``` (Status 200 OK)|
|`POST`|`/emprestimo`| Registra um novo emprestimo | ```json {"dataDevolucao": "2025-02-11", "idLivro": 2,"idUsuario": 2```| ```json { "id": 2,"dataRetirada": "2025-05-27", "dataDevolucao": "2025-07-03", "idLivro": 2, "idUsuario": 4, "status": "ATIVO"}``` (Status 200 OK)|
#### Modelo de Json
```
{
      "dataDevolucao": "2025-07-03",
      "idLivro": 1,
      "idUsuario": 1
}
```
