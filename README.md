# Gym Manager API

Sistema SaaS para gestão de academias, desenvolvido com **Java 17 + Spring Boot 3**, seguindo princípios de **DDD (Domain-Driven Design)**, **Clean Architecture** e boas práticas de segurança com **JWT + Role-Based Authorization**.

O projeto tem como objetivo fornecer uma base escalável e bem estruturada para:

- Gestão de usuários (ADMIN, PERSONAL, ALUNO)
- Gestão de academias
- Vínculos entre usuários
- Gestão de treinos
- Controle financeiro
- Dashboard administrativo

---

# Tecnologias Utilizadas

## Backend

- Java 17
- Spring Boot 3.2.1
- Spring Security + JWT
- Spring Data JPA
- H2 (ambiente dev)
- PostgreSQL (ambiente prod)
- Lombok
- Jakarta Validation
- Docker

## Frontend (Planejado)

- Vite.js
- React + TypeScript
- Axios
- React Router
- Context API ou Zustand

---

# Arquitetura

O projeto segue **DDD + Clean Architecture**, com clara separação de responsabilidades:

```
com.gymmanager
├── domain
│ ├── model
│ ├── repository
│ └── exception
├── application
│ ├── usecase
│ └── dto
├── infrastructure
│ ├── persistence
│ ├── security
│ └── config
└── interfaces
└── controller
```

### Princípios Aplicados

- SOLID
- Inversão de dependência
- Separação entre domínio e infraestrutura
- Use Cases como orquestradores de regras de negócio
- Controllers sem lógica de negócio

---

# Controle de Acesso

O sistema utiliza autenticação baseada em **JWT** com controle de roles:

- `ADMIN`
- `PERSONAL`
- `ALUNO`

As permissões são aplicadas diretamente nos endpoints.

---

# Módulos

## Implementados

- Autenticação (register/login)
- Controle de roles
- Gestão de usuários
- Gestão de academias
- Vínculos entre usuários

## Em Desenvolvimento

- Módulo de Treinos
- Módulo Financeiro
- Dashboard Administrativo
- Refatorações estruturais (SOLID improvements)

---

# Endpoints Principais

## Autenticação

`POST /api/auth/register`<br/>
`POST /api/auth/login`

## Usuário

`GET /api/users/me`

## Academias

`POST /api/academias (ADMIN)`<br/>
`GET /api/academias`<br/>
`GET /api/academias/{id}`<br/>

## Vínculos

`POST /api/vinculos (ADMIN)`<br/>
`GET /api/vinculos/academia/{academiaId}`<br/>
`GET /api/vinculos/personal/alunos (PERSONAL)`

## Treinos (Em desenvolvimento)

`POST /api/treinos/fichas (PERSONAL)`<br/>
`GET /api/treinos/fichas/aluno/{alunoId}`<br/>
`PUT /api/treinos/fichas/{id} (PERSONAL)`<br/>
`POST /api/treinos/fichas/{id}/concluir (ALUNO)`

## Financeiro (Em desenvolvimento)

`POST /api/financeiro/mensalidades (ADMIN)`<br/>
`PUT /api/financeiro/mensalidades/{id}/pagar (ADMIN)`<br/>
`GET /api/financeiro/mensalidades/aluno/{alunoId}`<br/>
`POST /api/financeiro/despesas (ADMIN)`<br/>
`GET /api/financeiro/despesas (ADMIN)`

## Dashboard (Em desenvolvimento)

`GET /api/dashboard (ADMIN)`

---

# Configuração e Execução

## Pré-requisitos

- Java 17
- Docker
- Maven

---

## Executando com Docker

```bash
docker-compose up --build
```

### Executando localmente

`./mvnw spring-boot:run`

### Perfis

`dev → H2`<br/>
`prod → PostgreSQL`

### Definir via variável:

```
export SPRING_PROFILES_ACTIVE=dev
```

### Variáveis de Ambiente

```
JWT_SECRET
SPRING_PROFILES_ACTIVE
DB_URL
DB_USERNAME
DB_PASSWORD
```

## Melhorias Futuras

Testes unitários e de integração
Auditoria de ações
Multi-tenancy real por academia
Integração com gateway de pagamento
Monitoramento e observabilidade

## Objetivo do Projeto

Este projeto foi desenvolvido com foco em:
Aplicação prática de DDD
Estrutura escalável
Separação de responsabilidades
Organização profissional de backend
Demonstração de maturidade arquitetural

## Autor

Gabriel Dantas <br/>
Desenvolvedor Full Stack<br/>
Natal, Brasil

LinkedIn
GitHub
Licença

Projeto para fins educacionais e demonstração técnica.
