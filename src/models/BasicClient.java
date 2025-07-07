package models;

public class BasicClient extends Client{

    public BasicClient(String nome, double salario) {
        super(nome, salario);
        setLimiteCredito(2000.0);
        setCreditoDisponivel(getLimiteDeCredito());
    }
}
