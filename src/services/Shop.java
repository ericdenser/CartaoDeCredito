package services;

import models.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shop {

    private double valorCarrinho;
    private List<Produto> listaDeProdutos;
    private List<Produto> produtosNoCarrinho;

    public Shop() {
        this.valorCarrinho = 0.0;
        this.listaDeProdutos = new ArrayList<>();
        this.produtosNoCarrinho = new ArrayList<>();

        this.listaDeProdutos.addAll(Arrays.asList(
                new Produto("Smartphone Samsung Galaxy S24", 4999.99),
                new Produto("Notebook Dell Inspiron 15", 3899.00),
                new Produto("Smart TV LG 55 polegadas 4K", 2750.50),
                new Produto("Fone de ouvido Bluetooth Sony", 899.90),
                new Produto("Console PlayStation 5", 4500.00),
                new Produto("Camiseta Masculina Algodão Pima", 79.90),
                new Produto("Calça Jeans Feminina Skinny", 129.99),
                new Produto("Tênis Esportivo Nike Air Max", 549.00),
                new Produto("Cadeira de Escritório Ergonômica", 890.00),
                new Produto("Robô Aspirador Inteligente", 1500.00),
                new Produto("Bicicleta Aro 29 Mountain Bike", 1800.00),
                new Produto("Barraca de Camping 4 Pessoas", 299.90),
                new Produto("Parafusadeira Elétrica Bosch", 210.00)
        ));
    }

    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public List<Produto> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public void adicionarNoCarrinho(int numeracao, Client cliente) {
        numeracao--;
        Produto produtoSelecionado = listaDeProdutos.get(numeracao);
        produtosNoCarrinho.add(produtoSelecionado);
        System.out.printf("\nProduto %s adicionado ao carrinho com sucesso!\n", produtoSelecionado.getNomeProduto());
    }



}
