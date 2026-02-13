# Planejamento do Frontend

## Estrutura Recomendada

src/
├── modules/
├── pages/
├── components/
├── services/
├── hooks/
├── contexts/
└── routes/

## Módulos

- Auth
- Dashboard
- Academia
- Usuários
- Treinos
- Financeiro

## Comunicação

- Axios com interceptor JWT
- Tratamento global de erros
- Guardas de rota por role

## Telas Planejadas

ADMIN:

- Dashboard
- Gestão de academias
- Financeiro

PERSONAL:

- Lista de alunos
- Criar ficha
- Editar ficha

ALUNO:

- Visualizar ficha
- Marcar treino concluído
- Visualizar mensalidades
