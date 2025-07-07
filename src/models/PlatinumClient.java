package models;

public class PlatinumClient extends Client{

    public PlatinumClient(String nome, double salario) {
        super(nome, salario);
        setLimiteCredito(10000.0);
        setCreditoDisponivel(getLimiteDeCredito());
    }

}
