package app;

import models.Client;
import java.util.Scanner;
import static java.lang.System.exit;

public class TerminalApp {

    private static Client cliente;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Cadastro();
        EfeitoCarregando();

        while (true) {
            Menu();
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
                        opcaoInfo = ValidarEntradaInt("Escolha uma opcao para prosseguir");
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
                        System.out.println("Encerrando conta.");
                        EfeitoCarregando();
                        exit(0);
                    }

                }

                case 0 -> redirecionar = true;

                default -> System.out.println("Opcao invalida");
            }

        }
    }

    public static void Cadastro() {
        System.out.println("--- Cadastro de Cliente ---");
        String nomeCliente = ValidarEntradaString("Nome: ");
        System.out.println("Nome aceito!");

        double salarioCliente = ValidarEntradaDouble("Salário: ");
        if (salarioCliente < 500) {
            System.out.println("Sinto muito, seu salário não atende o plano mínimo!\nSaindo");
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
                    [2] Verificar limite disponível 
                    [3] Loja Online 
                    [4] Fatura 
                    [0] Sair
                    -Por favor escolha uma opcão para prosseguir:
                    """, cliente.getNome());
        int opcao = input.nextInt();
        input.nextLine();

        switch (opcao) {
            case 1 -> AccSettings();
            case 2 -> System.out.println("opcao 2");
            case 3 -> System.out.println("opcao 3");
            case 4 -> System.out.println("opcao 4");
            case 0 -> {System.out.println("Saindo"); EfeitoCarregando(); exit(0);}
            default -> System.out.println("Selecione uma opcão válida!");
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
            if (entrada.matches("[a-zA-Z]+")) {
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






