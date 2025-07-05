package models;

public class GoldClient extends Client {

    public GoldClient(String nome, double salario) {
        super(nome, salario);
    }

    public double getLimiteDeCredito() {
        return 5000.0;
    }
}
