# Fluxos de Uso

## Fluxo: Cadastro e Login

1. Usuário realiza cadastro
2. Sistema gera JWT
3. Token usado nas requisições subsequentes

## Fluxo: Criação de Ficha de Treino

1. PERSONAL acessa lista de alunos
2. Cria ficha
3. Sistema valida vínculo
4. Ficha fica ativa

## Fluxo: Pagamento de Mensalidade

1. ADMIN registra pagamento
2. Status alterado para PAGO
3. Dashboard recalcula receita
