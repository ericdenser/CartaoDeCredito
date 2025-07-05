package models;

public class PlatinumClient extends Client{

    public PlatinumClient(String nome, double salario) {
        super(nome, salario);
    }

    public double getLimiteDeCredito() {
        return 10000.0;
    }
}
