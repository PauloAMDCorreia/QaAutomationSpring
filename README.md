# 🧪 QA Automation DemoQA

Este projeto implementa uma **automação completa de testes de API** utilizando a API pública do [DemoQA Book Store](https://demoqa.com/swagger/#/BookStore).  
O fluxo cobre desde a criação de um usuário até a associação de livros ao perfil, validando todo o ciclo de autenticação e consumo de endpoints REST.

---

## 🚀 Tecnologias Utilizadas

| Categoria | Ferramenta |
|------------|-------------|
| **Framework de Testes** | JUnit 4 |
| **Automação de API** | Rest Assured |
| **Injeção e Gerenciamento de Contexto** | Spring Boot Test |
| **DSL de Cenários** | Cucumber (BDD) |
| **Modelos de Dados** | Lombok |
| **Build Tool** | Maven |

---

## 🧩 Funcionalidades Automatizadas

O fluxo automatizado cobre:

1. 🧍 **Criação de um novo usuário** (`POST /Account/v1/User`)  
2. 🔑 **Geração de token de acesso** (`POST /Account/v1/GenerateToken`)  
3. ✅ **Verificação de autorização do usuário** (`POST /Account/v1/Authorized`)  
4. 📚 **Listagem de livros disponíveis** (`GET /BookStore/v1/Books`)  
5. 📖 **Aluguel de dois livros** (`POST /BookStore/v1/Books`)  
6. 🔍 **Confirmação de que os livros estão associados ao usuário** (`GET /Account/v1/User/{userId}`)

---

## 🧱 Estrutura do Projeto

src/test/java/com/example/demo/
├── config/
│ └── CucumberSpringConfiguration.java # Integração entre Cucumber e Spring Boot
├── context/
│ └── TestContext.java # Armazena dados entre as etapas (userId, token, livros)
├── payloads/
│ ├── UserPayload.java # Payload para criação de usuário e token
│ ├── Book.java # Modelo de livro retornado pela API
│ ├── BooksListResponse.java # Modelo de resposta da listagem de livros
│ └── BookCollectionPayload.java # Payload para aluguel de livros
├── steps/
│ └── FluxoUsuariosSteps.java # Implementação dos passos do Cucumber (RestAssured)
├── runners/
│ └── RunCucumberTest.java # Runner principal do Cucumber
└── resources/
└── features/
└── fluxoUsuarios.feature # Cenário BDD do fluxo completo

yaml
Copiar código

---

## 🧠 Exemplo de Cenário (`fluxoUsuarios.feature`)

```gherkin
Feature: Fluxo completo de usuário e livros

  Scenario: Criar usuário, gerar token e associar livros
    Given que desejo criar um usuário
    When gero um token de acesso para o usuário criado
    And verifico se o usuário está autorizado
    And listo os livros disponíveis
    And alugo dois livros de minha escolha
    Then confirmo que os livros estão associados ao usuário
⚙️ Como Executar os Testes
Clonar o repositório:

bash
Copiar código
git clone https://github.com/SEU_USUARIO/qa-automation-demoqa.git
cd qa-automation-demoqa
Executar os testes:

bash
Copiar código
mvn test -Dtest=RunCucumberTest
(Opcional) Executar com relatórios Cucumber:

bash
Copiar código
mvn clean verify
🧾 Dependências Principais (pom.xml)
xml
Copiar código
<dependencies>
    <!-- Spring Boot Test (com suporte JUnit4) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- JUnit 4 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Rest Assured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.32</version>
        <scope>provided</scope>
    </dependency>

    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
🧩 Exemplo de Execução (Saída Esperada)
bash
Copiar código
Feature: Fluxo completo de usuário e livros
  Scenario: Criar usuário, gerar token e associar livros
    Given que desejo criar um usuário             ✅
    When gero um token de acesso para o usuário   ✅
    And verifico se o usuário está autorizado     ✅
    And listo os livros disponíveis               ✅
    And alugo dois livros de minha escolha        ✅
    Then confirmo que os livros estão associados  ✅

✔ BUILD SUCCESS
🧰 Requisitos
Java 17+

Maven 3.9+

Acesso à Internet (para consumir a API DemoQA)

🧑‍💻 Autor
Seu Nome Aqui
📧 seu.email@exemplo.com
💼 LinkedIn
🐙 GitHub

🪶 Licença
Este projeto é distribuído sob a licença MIT.
Sinta-se livre para usar, modificar e contribuir.

