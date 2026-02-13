# Modelo de Domínio

## Usuário

- id
- nome
- email
- senha
- role (ADMIN, PERSONAL, ALUNO)

## Academia

- id
- nome
- endereco

## FichaTreino

- id
- alunoId
- personalId
- diaSemana
- grupoMuscular
- ativa
- dataCriacao

## Exercicio

- id
- fichaTreinoId
- nome
- series
- repeticoes
- observacoes
- ordem

## HistoricoTreino

- id
- fichaTreinoId
- alunoId
- dataConclusao

## Mensalidade

- id
- alunoId
- valor
- mesReferencia
- dataVencimento
- status (PENDENTE, PAGO)
- dataPagamento

## Despesa

- id
- academiaId
- descricao
- valor
- tipo (RECORRENTE, PONTUAL)
- dataDespesa
