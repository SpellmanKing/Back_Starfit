# StarFit Backend

Backend para landing page StarFit desenvolvido com Spring Boot e Java.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security** (Autenticação e Autorização)
- **JWT** (JSON Web Tokens)
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (produção)
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+ (ou use o Maven Wrapper)
- PostgreSQL (opcional, para produção)

## 🔧 Instalação e Execução

### 1. Clone o repositório (se aplicável)

```bash
git clone <url-do-repositorio>
cd StarFit
```

### 2. Compile o projeto

```bash
mvn clean install
```

### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### Health Check
- **GET** `/api/landing/health` - Verifica se a API está funcionando

### Autenticação (Área do Aluno)
- **POST** `/api/auth/registrar` - Registra um novo usuário
  ```json
  {
    "email": "usuario@example.com",
    "senha": "Senha123!@#",
    "nome": "João Silva",
    "telefone": "(11) 99999-9999",
    "cpf": "12345678900"
  }
  ```
  **Validação de senha:** 8+ caracteres, 1 maiúscula, 1 número e 1 símbolo

- **POST** `/api/auth/login` - Realiza login e retorna token JWT
  ```json
  {
    "email": "usuario@example.com",
    "senha": "Senha123!@#"
  }
  ```
  **Resposta:**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "tipo": "Bearer",
    "usuarioId": 1,
    "email": "usuario@example.com",
    "nome": "João Silva",
    "temAssinaturaAtiva": true
  }
  ```

### Planos
- **GET** `/api/planos` - Lista todos os planos ativos (STAR BÁSICO e STAR PREMIUM)
- **GET** `/api/planos/{id}` - Busca um plano por ID
- **GET** `/api/planos/nome/{nome}` - Busca um plano por nome (ex: "STAR BÁSICO")

### Assinaturas
- **POST** `/api/assinaturas` - Cria uma nova assinatura
  ```json
  {
    "usuarioId": 1,
    "planoId": 1
  }
  ```

- **GET** `/api/assinaturas/usuario/{usuarioId}/ativa` - Busca assinatura ativa do usuário
- **GET** `/api/assinaturas/usuario/{usuarioId}` - Lista todas as assinaturas do usuário
- **POST** `/api/assinaturas/{assinaturaId}/visita-amigo` - Registra visita de amigo (limite de 5x/mês)

### Contato
- **POST** `/api/landing/contato` - Cria um novo contato
  ```json
  {
    "nome": "João Silva",
    "email": "joao@example.com",
    "telefone": "(11) 99999-9999",
    "mensagem": "Gostaria de mais informações"
  }
  ```

- **GET** `/api/landing/contatos` - Lista todos os contatos

### Newsletter
- **POST** `/api/landing/newsletter` - Inscreve um email na newsletter
  ```json
  {
    "email": "usuario@example.com"
  }
  ```

- **GET** `/api/landing/newsletters` - Lista todos os emails da newsletter

## 🗄️ Banco de Dados

### Desenvolvimento (H2)
O H2 é um banco de dados em memória usado para desenvolvimento. Acesse o console em:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:starfitdb`
- Usuário: `sa`
- Senha: (vazio)

### Produção (PostgreSQL)
Para usar PostgreSQL em produção, descomente as configurações em `application.properties` e configure as variáveis de ambiente.

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/starfit/
│   │   ├── StarFitApplication.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── DataInitializer.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── PlanoController.java
│   │   │   ├── AssinaturaController.java
│   │   │   └── LandingPageController.java
│   │   ├── model/
│   │   │   ├── Usuario.java
│   │   │   ├── Plano.java
│   │   │   ├── Assinatura.java
│   │   │   ├── Contato.java
│   │   │   └── Newsletter.java
│   │   ├── dto/
│   │   │   ├── LoginDTO.java
│   │   │   ├── UsuarioDTO.java
│   │   │   ├── PlanoDTO.java
│   │   │   ├── AssinaturaDTO.java
│   │   │   ├── AuthResponseDTO.java
│   │   │   ├── ContatoDTO.java
│   │   │   └── NewsletterDTO.java
│   │   ├── repository/
│   │   │   ├── UsuarioRepository.java
│   │   │   ├── PlanoRepository.java
│   │   │   ├── AssinaturaRepository.java
│   │   │   ├── ContatoRepository.java
│   │   │   └── NewsletterRepository.java
│   │   └── service/
│   │       ├── JwtService.java
│   │       ├── AuthService.java
│   │       ├── PlanoService.java
│   │       ├── AssinaturaService.java
│   │       └── LandingPageService.java
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
```

## 🔒 Validações

A API inclui validações automáticas:
- Email válido e único
- Senha com requisitos: 8+ caracteres, 1 maiúscula, 1 número e 1 símbolo
- CPF único (quando fornecido)
- Campos obrigatórios
- Tamanho máximo de campos

## 🔐 Segurança

- **Spring Security** configurado para autenticação
- **JWT (JSON Web Tokens)** para autenticação stateless
- **BCrypt** para hash de senhas
- Endpoints públicos: `/api/auth/**`, `/api/planos/**`, `/api/landing/**`
- Endpoints protegidos: `/api/assinaturas/**` (pode ser expandido com filtros JWT)

## 📋 Planos Disponíveis

### STAR BÁSICO (R$ 89,90)
- ✅ Acesso à musculação
- ✅ Sem taxa de adesão
- ✅ Horário livre
- ✅ Acesso outras unidades
- ✅ Levar amigos (5x/mês)

### STAR PREMIUM (R$ 119,90)
- ✅ Acesso ILIMITADO
- ✅ Levar amigos (5x/mês)
- ✅ Cadeira de massagem
- ✅ SPA liberado
- ✅ Todas as unidades Starfit

Os planos são inicializados automaticamente na primeira execução da aplicação.

## 🌐 CORS

A API está configurada para aceitar requisições de qualquer origem (`*`). Em produção, configure as origens permitidas adequadamente.

## 📝 Licença

Este projeto é privado.

