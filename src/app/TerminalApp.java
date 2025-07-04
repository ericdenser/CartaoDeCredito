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

    }
}
