package models;

import java.util.Date;

public class Servico {

    // Atributos
    protected int servico_id;
    protected String servico_tipo;
    protected int imoveis_id;
    protected int pessoas_id;
    protected Date data_inicio;
    protected Date data_fim;
    protected float valor_total;

    // Construtor vazio
    public Servico(String servico_tipo2, int imoveis_id2, int pessoas_id2, Date data_inicio2, Date data_fim2, float valor_total2) {
    }

    // Construtor completo
    public Servico(int servico_id, String servico_tipo, int imoveis_id, int pessoas_id, Date data_inicio, Date data_fim, float valor_total) {
        this.servico_id = servico_id;
        this.servico_tipo = servico_tipo;
        this.imoveis_id = imoveis_id;
        this.pessoas_id = pessoas_id;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.valor_total = valor_total;
    }

    // Métodos Getters e Setters
    public int getServico_id() {
        return servico_id;
    }

    public void setServico_id(int servico_id) {
        this.servico_id = servico_id;
    }

    public String getServico_tipo() {
        return servico_tipo;
    }

    public void setServico_tipo(String servico_tipo) {
        this.servico_tipo = servico_tipo;
    }

    public int getImoveis_id() {
        return imoveis_id;
    }

    public void setImoveis_id(int imoveis_id) {
        this.imoveis_id = imoveis_id;
    }

    public int getPessoas_id() {
        return pessoas_id;
    }

    public void setPessoas_id(int pessoas_id) {
        this.pessoas_id = pessoas_id;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }
}
