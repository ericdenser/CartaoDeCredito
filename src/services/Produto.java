package services;

public class Produto {

    private String nomeProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Produto(String nomeProduto, double valorProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = valorProduto;
    }

    public String getNomeProduto() {return nomeProduto;}

    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}

    public int getQuantidadeProduto() {return quantidadeProduto;}

    public void setQuantidadeProduto(int quantidadeProduto) {this.quantidadeProduto = quantidadeProduto;}

    public double getPrecoProduto() {return precoProduto;}

    public void setPrecoProduto(double precoProduto) {this.precoProduto = precoProduto;}

    @Override
    public String toString() {
        return String.format("%s - R$%.2f", nomeProduto, precoProduto);
    }

}
