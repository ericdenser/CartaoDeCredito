package models;

public class PlatinumClient extends Client{

    public PlatinumClient(String nome, double salario) {
        super(nome, salario);
    }

    public double getLimiteCredito() {
        return 50000.0;
    }
}
