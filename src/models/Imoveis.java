package models;

import java.io.Serial;

public class Imoveis {
    // Atributos
    private Serial imoveis_id;
    private int qtd_quartos;
    private int qtd_banheiro;
    private String tipo;
    private int garagem;
    private float preco;
    private float idade;
    private int suite;
    private int metros_quadrados;

    // Construtor vazio
    public Imoveis() {
    
    }

    // Construtor com parâmetros
    public Imoveis(Serial imoveis_id, int qtd_quartos, int qtd_banheiro, String tipo, int garagem, float preco, float idade, int suite, int metros_quadrados) {
        this.imoveis_id = imoveis_id;
        this.qtd_quartos = qtd_quartos;
        this.qtd_banheiro = qtd_banheiro;
        this.tipo = tipo;
        this.garagem = garagem;
        this.preco = preco;
        this.idade = idade;
        this.suite = suite;
        this.metros_quadrados = metros_quadrados;
    }

    public Serial getImoveis_id() {
        return imoveis_id;
    }

    public void setImoveis_id(Serial imoveis_id) {
        this.imoveis_id = imoveis_id;
    }

    // Métodos Getters e Setters
    public int getQtd_quartos() {
        return qtd_quartos;
    }

    public int getQtd_banheiro() {
        return qtd_banheiro;
    }

    public String getTipo() {
        return tipo;
    }

    public int getGaragem() {
        return garagem;
    }

    public float getPreco() {
        return preco;
    }

    public float getIdade() {
        return idade;
    }

    public int getSuite() {
        return suite;
    }

    public int getMetros_quadrados() {
        return metros_quadrados;
    }
}
