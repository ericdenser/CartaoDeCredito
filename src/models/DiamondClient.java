package models;

public class DiamondClient extends Client{

    public DiamondClient(String nome, double salario) {
        super(nome, salario);
    }

    public double getLimiteCredito() {
        return 50000.0;
    }

}
