package models;

public class Client {
    private String nome;
    private double salario;

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public Client(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    //public abstract double getLimiteDeCredito();


    public String getTipoPlano() {
        return this.getClass().getSimpleName();
    }

    public static Client classificarCliente(String nome, double salario) {
        if (salario <= 2000) {
            return new BasicClient(nome, salario);
        } else if (salario <= 5000) {
            return new GoldClient(nome, salario);
        } else if (salario <= 15000) {
            return new PlatinumClient(nome, salario);
        } else {
            return new DiamondClient(nome, salario);
        }
    }

    @Override
    public String toString() {
        return String.format("""
        Nome do cliente: %s
        Salario informado: %.2f
        Plano atual: %s
        """, nome, salario, getTipoPlano());
    }
}
