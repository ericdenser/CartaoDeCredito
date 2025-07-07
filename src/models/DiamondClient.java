package models;

public class DiamondClient extends Client{

    public DiamondClient(String nome, double salario) {
        super(nome, salario);
        setLimiteCredito(50000.0);
        setCreditoDisponivel(getLimiteDeCredito());
    }

}
