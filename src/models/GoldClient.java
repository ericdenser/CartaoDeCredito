package models;

public class GoldClient extends Client {

    public GoldClient(String nome, double salario) {
        super(nome, salario);
        setLimiteCredito(5000.0);
        setCreditoDisponivel(getLimiteDeCredito());
    }

}
