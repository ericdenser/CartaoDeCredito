package services;

public class Produto {

    private String nomeProduto;
    private double precoProduto;

    public Produto(String nomeProduto, double valorProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = valorProduto;
    }

    public String getNomeProduto() {return nomeProduto;}

    public double getPrecoProduto() {return precoProduto;}



    @Override
    public String toString() {
        return String.format("%s - R$%.2f", nomeProduto, precoProduto);
    }

}
