package app;

import models.Client;

import java.util.Scanner;

public class TerminalApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nomeCliente = input.nextLine();

        System.out.print("Salário: ");
        int salarioCliente = input.nextInt();
        input.nextLine();

        Client cliente = Client.classificarCliente(nomeCliente, salarioCliente);
        System.out.printf("Parabéns %s, sua conta foi criada com sucesso! Analisamos seu salário e" +
                "você se encaixa perfeitamente em nosso plano %s\n", cliente.getNome(), cliente.getTipoPlano());


        String[] loading = {
                "   ",
                ".  ",
                ".. ",
                "...",
        };

        System.out.print("Estamos te redirecionando para nosso Menu");
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
            redirect ++;
        }

        while (true) {
            System.out.printf("""
            \n--- Menu ---
            Olá %s! Seja bem-vindo ao menu do banco Cheddar.
            
            -Configuracões da conta (1)
            -Verificar limite disponível (2)
            -Loja Online (3)
            -Pagar fatura (4)
            -Por favor escolha uma opcão para prosseguir:
            """, cliente.getNome());
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("opcao 1");
                    break;
                case 2:
                    System.out.println("opcao 2");
                    break;
                case 3:
                    System.out.println("opcao 3");
                    break;
                case 4:
                    System.out.println("opcao 4");
                    break;
                default:
                    System.out.println("Selecione uma opcão válida!");
                    break;
            }
        }
    }
}
