# 🍔 FoodTech API

## 📌 Visão Geral

O **FoodTech** é uma API REST desenvolvida em **Java 17**, seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**. O objetivo do projeto é fornecer uma base sólida, organizada e de fácil manutenção, isolando a lógica de negócio das tecnologias externas, como frameworks, banco de dados e infraestrutura.

Essa abordagem garante maior **flexibilidade**, **testabilidade** e **facilidade de evolução** do sistema.

---

## 🏗️ Arquitetura

O projeto utiliza a **Arquitetura Hexagonal**, que separa o sistema em três grandes áreas:

* **Core (Domínio)**: contém as regras de negócio, entidades e serviços, sem dependência de frameworks.
* **Ports**: interfaces que definem como o core se comunica com o mundo externo.
* **Adapters**: implementações responsáveis pela comunicação com frameworks, banco de dados e APIs externas.

Essa estrutura permite a substituição de dependências externas sem impacto direto no core da aplicação.

---

## 🧱 Estrutura do Projeto

* **Controllers**: expõem os endpoints REST da aplicação.
* **Services**: concentram a lógica de negócio.
* **Repositories**: realizam a comunicação com o banco de dados.
* **DTOs**: responsáveis pelo transporte de dados entre as camadas.
* **Exceptions**: tratamento centralizado de erros.
* **Config**: configurações de segurança, Swagger e exception handler global.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **H2 Database (em memória)**
* **Maven 3.9**
* **Docker & Docker Compose** (preparados para uso futuro)
* **Swagger / OpenAPI**
* **Postman** (testes manuais)

---

## 🐳 Docker

O projeto possui arquivos **Dockerfile** e **docker-compose**, porém, nesta fase, não há containers ativos. Isso ocorre porque o banco de dados utilizado é o **H2**, que roda em memória e não exige infraestrutura adicional.

Os arquivos Docker foram mantidos no repositório para facilitar **futuras implementações**, como a integração com bancos de dados externos ou outros serviços.

---

## ▶️ Executando o Projeto Localmente

### Pré-requisitos

* Java 17
* Apache Maven 3.9
* IDE de sua preferência (recomendado: **IntelliJ IDEA**)

### Passo a passo

1. Clone o repositório:

   ```bash
   git clone <url-do-repositorio>
   ```

2. Acesse o diretório do projeto e execute o build:

   ```bash
   mvn clean install
   ```

3. Execute a aplicação:

   * Localize a classe `FoodTechApplication.java`
   * Execute a aplicação pela IDE

4. A aplicação estará disponível em:

   ```
   http://localhost:8080
   ```

---

## 🔗 Endpoints Principais

### 🔐 Autenticação

* `POST /v1/auth/login` – Realiza o login do usuário

### 👤 Usuários

* `POST /v1/usuarios` – Cadastro de usuário
* `GET /v1/usuarios` – Listagem de usuários
* `GET /v1/usuarios/{id}` – Busca usuário por ID
* `PUT /v1/usuarios/{id}` – Atualização de usuário
* `DELETE /v1/usuarios/{id}` – Exclusão de usuário

### 🔑 Senha

* `PUT /v1/senhas` – Atualização de senha

---

## 🧪 Testes Manuais

Os testes manuais foram realizados utilizando o **Postman**, validando:

* Cadastro de usuário
* Autenticação (login)
* Atualização de senha
* Listagem, busca, atualização e exclusão de usuários

Os testes confirmaram o correto funcionamento dos endpoints, validações e códigos de status HTTP.

---

## ✅ Boas Práticas Utilizadas

* Adoção das convenções do Spring Boot
* Arquitetura Hexagonal
* Princípio da Responsabilidade Única (SOLID)
* Separação de camadas (Controller, Service, Repository)
* Uso de DTOs
* Tratamento global de exceções
* Padronização de nomenclaturas

---

## 📚 Documentação da API

A documentação da API pode ser acessada via **Swagger**, após subir a aplicação:

```
http://localhost:8080/swagger-ui.html
```

---

## 🚀 Considerações Finais

O projeto foi desenvolvido com foco em **organização, boas práticas e escalabilidade**, servindo como uma base sólida para evolução futura, integração com novos serviços e adoção de novas tecnologias.

---

👩🏾‍💻 Desenvolvido para fins acadêmicos e de aprendizado.
