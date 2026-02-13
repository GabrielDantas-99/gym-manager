# Gym Management API

API REST do sistema gerenciador de academias desenvolvida com Spring Boot 3, seguindo princípios de Clean Architecture e DDD.

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Security
- JWT
- PostgreSQL
- H2 Database
- Docker
- Lombok

## Profiles

- `dev`: H2 Database
- `prod`: PostgreSQL

## Como executar

### Desenvolvimento (H2)

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Produção (Docker)

```bash
docker-compose up
```
