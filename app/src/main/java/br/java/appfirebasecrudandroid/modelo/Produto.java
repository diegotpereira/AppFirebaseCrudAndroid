package br.java.appfirebasecrudandroid.modelo;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String marca;
    private String preco;
    private String key;

    public Produto() {
    }

    public Produto(String nome, String marca, String preco, String key) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", preco='" + preco + '\'' +
                '}';
    }
}
