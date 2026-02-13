# Especificação da API

## Autenticação

POST /api/auth/register
POST /api/auth/login

## Usuários

GET /api/users/me

## Academias

POST /api/academias (ADMIN)
GET /api/academias
GET /api/academias/{id}

## Vínculos

POST /api/vinculos (ADMIN)
GET /api/vinculos/academia/{academiaId}
GET /api/vinculos/personal/alunos (PERSONAL)

## Treinos

POST /api/treinos/fichas (PERSONAL)
GET /api/treinos/fichas/aluno/{alunoId}
PUT /api/treinos/fichas/{id} (PERSONAL)
POST /api/treinos/fichas/{id}/concluir (ALUNO)

## Financeiro

POST /api/financeiro/mensalidades (ADMIN)
PUT /api/financeiro/mensalidades/{id}/pagar (ADMIN)
GET /api/financeiro/mensalidades/aluno/{alunoId}
POST /api/financeiro/despesas (ADMIN)
GET /api/financeiro/despesas (ADMIN)

## Dashboard

GET /api/dashboard (ADMIN)
