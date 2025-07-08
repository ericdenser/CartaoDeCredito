package app;

import models.Client;
import services.Produto;
import services.Shop;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class TerminalApp {

    private static Shop loja = new Shop();
    private static Client cliente;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Cadastro();
        EfeitoCarregando();

        while (true) {
            Menu();
        }
    }


    public static void Cadastro() {
        System.out.println("--- Cadastro de Cliente ---");
        String nomeCliente = ValidarEntradaString("Nome: ");
        System.out.println("Nome aceito!");

        double salarioCliente = ValidarEntradaDouble("Salário: ");
        if (salarioCliente < 500) {
            System.out.println("Sinto muito, seu salário não atende o plano mínimo de nosso banco!\nSaindo");
            EfeitoCarregando();
            exit(0);
        }

        cliente = Client.classificarCliente(nomeCliente, salarioCliente);
        System.out.printf("Parabéns %s, sua conta foi criada com sucesso! Analisamos seu salário e" +
                " você se encaixa perfeitamente em nosso plano %s\n", cliente.getNome(), cliente.getTipoPlano());

        System.out.println("Estamos te redirecionando para o menu!");
    }

    public static void Menu() {
        System.out.printf("""
                \n--- Menu ---
                Olá %s! Seja bem-vindo ao menu do banco Cheddar.
                
                [1] Configuracões da conta
                [2] Verificar crédito disponível
                [3] Loja Online
                [4] Fatura
                [0] Sair
                """, cliente.getNome());
        int opcaoMenu = ValidarEntradaInt("-Por favor escolha uma opcão para prosseguir:");

        switch (opcaoMenu) {
            case 1 -> AccSettings();
            case 2 -> VerificarLimite();
            case 3 -> OnlineShop();
            case 4 -> Fatura(loja.getProdutosComprados());
            case 0 -> {
                System.out.println("Saindo");
                EfeitoCarregando();
                exit(0);
            }
            default -> System.out.println("Selecione uma opcão válida!");
        }
    }

    public static void AccSettings() {

        boolean redirecionar = false;

        while (!redirecionar) {
            System.out.print("""
                    \n--- Configuracões ---
                    [1] Exibir informacões
                    [2] Editar informacões
                    [3] Encerrar conta
                    [0] Voltar ao menu
                    """);

            int opcaoConf = ValidarEntradaInt("Escolha uma opcao para prosseguir ");

            switch (opcaoConf) {

                case 1 -> {

                    System.out.println(cliente);
                    System.out.println("\n[1] Retornar ao menu anterior\n[2] Retornar ao menu principal");

                    int opcaoInfo = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");

                    while (opcaoInfo < 1 || opcaoInfo > 2) {
                        System.out.println("\nOpcao invalida");
                        opcaoInfo = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");
                    }

                    if (opcaoInfo == 2) {
                        redirecionar = true;
                    }
                }

                case 2 -> {
                    System.out.println("""
                            --- Editar ---
                            [1] Nome
                            [2] Salario
                            """);

                    int opcaoEditar = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");

                    while (opcaoEditar < 1 || opcaoEditar > 2) {
                        System.out.println("Opcao invalida");
                        opcaoEditar = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");
                    }

                    if (opcaoEditar == 1) {

                        String nomeAntigo = cliente.getNome();

                        System.out.print("Novo nome: ");
                        cliente.setNome(input.nextLine());
                        System.out.println("Nome alterado de " + nomeAntigo + " para " + cliente.getNome() + " com sucesso!");

                    } else {

                        if (!loja.getProdutosComprados().isEmpty()) {
                            System.out.println("\nVocê tem uma fatura em andamento! Pague antes de atualizar");
                            break;
                        }

                        double novoSalario = ValidarEntradaDouble("Novo salário: ");

                        String antigoPlano = cliente.getTipoPlano();

                        cliente.setSalario(novoSalario);

                        cliente = Client.classificarCliente(cliente.getNome(), novoSalario);

                        if (antigoPlano.equals(cliente.getTipoPlano())) {
                            System.out.println("Salário insuficiente para troca de plano!");
                        } else {
                            System.out.println("Seu plano foi atualizado para " + cliente.getTipoPlano());
                            System.out.printf("Seu limite novo é de R$%.2f! ", cliente.getLimiteDeCredito());
                        }

                    }
                }

                case 3 -> {
                    System.out.print("""
                            \nVocê tem certeza que deseja encerrar sua conta?
                            [1] Sim, quero encerrar minha conta
                            [2] Não, voltar ao menu
                            """);
                    int opcaoEncerrar = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");

                    while (opcaoEncerrar < 1 || opcaoEncerrar > 2) {
                        System.out.println("Opcao invalida");
                        opcaoEncerrar = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");
                    }

                    if (opcaoEncerrar == 1) {
                        if (!loja.getProdutosComprados().isEmpty()) {
                            System.out.println("\nVocê não pode encerrar sua conta até pagar a fatura em aberto!");
                        } else {
                            System.out.println("Encerrando conta.");
                            EfeitoCarregando();
                            exit(0);
                        }
                    }

                }

                case 0 -> redirecionar = true;

                default -> System.out.println("Opcao invalida");
            }

        }
    }

    public static void VerificarLimite() {
        System.out.printf("""
            Limite do plano: R$%.2f
            Limite disponível: R$%.2f
            """, cliente.getLimiteDeCredito(), cliente.getCreditoDisponivel());

        ValidarEntradaInt("Digite qualquer número para voltar: ");


    }

    public static void OnlineShop() {

        boolean redirecionar = false;
        List<Produto> produtos = loja.getListaDeProdutos();
        List<Produto> produtosNoCarrinho = loja.getProdutosNoCarrinho();
        List<Produto> produtosComprados = loja.getProdutosComprados();

        while (!redirecionar) {
            System.out.println("""
                    \n--- Loja Cheddar ---
                    [1] Comprar produtos
                    [2] Ver carrinho
                    [3] Voltar ao menu
                    """);

            int opcaoLoja = ValidarEntradaInt("Escolha uma opcao para prosseguir: ");
            switch (opcaoLoja) {
                case 1 -> {

                    MostrarProdutos(produtos);
                    System.out.println("""
                        \n[1] Adicionar produto
                        [2] Voltar
                        """);
                    int opcaoProdutos = ValidarEntradaInt("Escolha uma opção para prosseguir: ");
                    while (opcaoProdutos < 1 || opcaoProdutos > 2) {
                        opcaoProdutos = ValidarEntradaInt("Opção inválida, tente novamente: ");
                    }

                    if (opcaoProdutos == 1) {

                        int produtoInteressado = ValidarEntradaInt("Informe a numeração do produto: ");

                        while (produtoInteressado < 1 || produtoInteressado > (loja.getListaDeProdutos().size())) {
                            produtoInteressado = ValidarEntradaInt("Produto não encontrado, tente novamente: ");
                        }

                        loja.adicionarNoCarrinho(produtoInteressado, cliente);

                    }

                }

                case 2 -> {

                    if (produtosNoCarrinho.isEmpty()) {
                        System.out.println("\nSeu carrinho está vazio!");
                        break;
                    }

                    double totalCarrinho = VerCarrinho(produtosNoCarrinho);

                    System.out.println("""
                        \n[1] Finalizar compra
                        [2] Limpar carrinho
                        [3] Voltar
                        """);
                    int opcaoCarrinho = ValidarEntradaInt("Escolha uma opção para prosseguir: ");
                    while (opcaoCarrinho < 1 || opcaoCarrinho > 3) {
                        opcaoCarrinho = ValidarEntradaInt("Opção inválida, tente novamente: ");
                    }

                    if (opcaoCarrinho == 1) {
                        if (cliente.getCreditoDisponivel() >= totalCarrinho) {
                            produtosComprados.addAll(produtosNoCarrinho);
                            cliente.setCreditoDisponivel(cliente.getCreditoDisponivel() - totalCarrinho);
                            loja.getProdutosNoCarrinho().clear();
                            System.out.printf("Compra realizada com sucesso! Seu crédito disponível é agora de R$%.2f", cliente.getCreditoDisponivel());

                        } else {
                            System.out.println("Crédito insuficiente para finalizar a compra.");
                        }

                    } else if (opcaoCarrinho == 2) {
                        loja.getProdutosNoCarrinho().clear();
                        System.out.println("\nCarrinho limpado com sucesso!");
                    }

                }
                
                case 3 -> redirecionar = true;

                default -> System.out.println("Opcão inválida");
            }
        }
    }

    public static void MostrarProdutos(List<Produto> produtos) {
        System.out.println("\n--- Produtos ---");
        final int[] index = {1};
        produtos.forEach(produto -> {
            System.out.printf("[%02d] %-45s R$%8.2f\n",
                    index[0]++,
                    produto.getNomeProduto(),
                    produto.getPrecoProduto());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static double VerCarrinho(List<Produto> produtosNoCarrinho) {

        System.out.println("\n--- Carrinho ---");
        final int[] index = {1};
        produtosNoCarrinho.forEach(produto -> {
            System.out.printf("[%02d] %-45s R$%8.2f\n",
                    index[0]++,
                    produto.getNomeProduto(),
                    produto.getPrecoProduto());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        double totalCarrinho = produtosNoCarrinho.stream()
                .mapToDouble(Produto::getPrecoProduto)
                .sum();

        System.out.printf("\nO valor total de seu carrinho é R$%.2f", totalCarrinho );
        return totalCarrinho;
    }

    public static void Fatura(List<Produto> produtosComprados) {

        if (produtosComprados.isEmpty()) {
            System.out.println("""
            Sua fatura esta em dia!
            Voltando ao menu""");
            EfeitoCarregando();
            return;
        }

        double faturaTotal = 0.0;

        System.out.println("--- Fatura ---");
        for (Produto produto : produtosComprados) {
            System.out.println(produto.toString());
            faturaTotal += produto.getPrecoProduto();

        }
        System.out.printf("""
            \nValor total da fatura = R$%.2f
            Deseja pagar agora?
            [1] Sim
            [2] Não, voltar\n""", faturaTotal);
        int opcaoFatura = ValidarEntradaInt("Escolha uma opcão para prosseguir: ");
        while (opcaoFatura < 1 || opcaoFatura > 2) {
            opcaoFatura = ValidarEntradaInt("Opção inválida, tente novamente: ");
        }

        if (opcaoFatura == 1) {

            cliente.setCreditoDisponivel(cliente.getLimiteDeCredito());
            loja.getProdutosComprados().clear();
            System.out.println("\nA fatura foi paga com sucesso! Seu crédito foi restaurado e esta pronto" +
                    " para ser usado!");
        }
    }

    public static int ValidarEntradaInt(String mensagem) {
        int opcao;
        while (true) {
            System.out.print(mensagem);
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine();
                break;
            } else {
                System.out.println("\nEntrada inválida! Somente inteiros");
                input.nextLine();
            }
        }
        return opcao;
    }

    public static double ValidarEntradaDouble(String mensagem) {
        double entrada;

        while (true) {
            System.out.print(mensagem);
            String converter = input.nextLine().replace(",", ".").trim();
            try {
                entrada = Double.parseDouble(converter);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
            }
        }
        return entrada;
    }

    public static String ValidarEntradaString(String mensagem) {
        String entrada;

        while (true) {
            System.out.print(mensagem);
            entrada = input.nextLine();
            if (entrada.matches("[\\p{L} ]+")) {
                break;
            } else {
                System.out.println("Entrada inválida");
            }
        }
        return entrada;
    }

    public static void EfeitoCarregando() {
        String[] loading = {"   ", ".  ", ".. ", "..."};
        int redirect = 0;
        while (redirect < 5) {
            for (int i = 0; i <= 3; i++) {
                try {
                    System.out.print("\r" + loading[i]);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Problema ao executar redirecionamento");
                }
            }
            redirect++;
        }
    }
}






