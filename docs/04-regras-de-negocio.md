# Regras de Negócio

## Autenticação

- JWT obrigatório em endpoints protegidos
- Roles obrigatórias

## Academia

- Apenas ADMIN pode criar academias

## Vínculos

- ADMIN vincula usuários
- ALUNO pode estar vinculado a um PERSONAL

## Treinos

- PERSONAL cria ficha apenas para seus alunos
- ALUNO visualiza apenas suas fichas
- ALUNO pode marcar treino como concluído

## Financeiro

- Apenas ADMIN gerencia mensalidades
- ALUNO visualiza apenas suas mensalidades

## Dashboard

- Apenas ADMIN acessa
- Dados agregados por academia
