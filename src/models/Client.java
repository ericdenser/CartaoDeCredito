package models;

public class Client {

    private String nome;
    private double salario;
    private double limiteCredito;

    public void setNome(String nome) {this.nome = nome;}
    public void setSalario(double salario) {this.salario = salario;}
    public void setLimiteCredito(double limiteCredito) {this.limiteCredito = limiteCredito;}

    public String getNome() {return nome;}
    public double getSalario() {return salario;}
    public double getLimiteDeCredito() {return limiteCredito;}

    public Client(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

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
        --- Info ---
        Nome do cliente: %s
        Salario informado: %.2f
        Plano atual: %s
        Limite de crédito: %.2f""", nome, salario, getTipoPlano(), getLimiteDeCredito());
    }
}
