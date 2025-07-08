🧀 Banco Cheddar – Sistema de Terminal com Loja Online

Projeto desenvolvido em Java que simula um sistema bancário de cartão de crédito com funcionalidades de cadastro de cliente, verificação de crédito, loja virtual, fatura e muito mais – tudo via terminal.

Futuras melhorias: 
- Refatorar a aplicação para orientação a objetos completa, evitando o uso excessivo de static
- Guardar os dados do cliente em um banco de dados
- Adicionar autenticação de login/senha para múltiplos usuários.

FUNCIONALIDADES:

📇 Cadastro de Cliente com análise de salário e classificação em planos:
- Basic (até R$2000)
- Gold (até R$5000)
- Platinum (até R$15000)
- Diamond (acima de R$15000)

💳 Gestão de Crédito
- Limite de crédito baseado no plano
- Compra de produtos desconta do crédito disponível
- Verificação de crédito atual a qualquer momento

🛒 Loja Online
- Lista de produtos com nome e preço
- Adição e remoção de produtos do carrinho
- Finalização de compra com verificação de saldo
- Histórico de compras pendentes (fatura)

🧾 Fatura
- Exibe todos os produtos comprados
- Mostra o valor total da fatura
- Permite pagamento da fatura e restauração do crédito

⚙️ Configurações da Conta
- Edição de nome e salário
- Reclassificação automática do plano
- Validações para impedir mudanças com fatura pendente
