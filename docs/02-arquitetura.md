# Arquitetura do Sistema

O sistema segue o padrão:

## Domain-Driven Design (DDD)

Divisão em camadas:

- domain
- application
- infrastructure
- interfaces

## Estrutura de Pacotes

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

## Princípios Aplicados

- SOLID
- Clean Architecture
- Separação clara entre domínio e infraestrutura
- Controllers apenas orquestram
- Use Cases concentram regras de negócio
