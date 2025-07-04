package models;

public class BasicClient extends Client{

    public BasicClient(String nome, double salario) {
        super(nome, salario);
    }

    public double getLimiteCredito() {
        return 2000.0;
    }
}
