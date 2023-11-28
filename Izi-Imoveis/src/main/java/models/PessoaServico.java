package models;

import java.util.Date;

public class PessoaServico extends Servico {

    // Atributos adicionais da classe PessoaServico
    private int pessoaServicoID;
    private Date data;
    private float valor;

    // Construtor com parâmetros
    public PessoaServico(int pessoaServicoID, Date data, float valor, int servico_id, String servico_tipo, int imoveis_id, int pessoas_id, Date data_inicio, Date data_fim, float valor_total) {
        super(servico_id, servico_tipo, imoveis_id, pessoas_id, data_inicio, data_fim, valor_total);
        this.pessoaServicoID = pessoaServicoID;
        this.data = data;
        this.valor = valor;
    }    

    // Métodos Getters e Setters
    public int getPessoaServicoID() {
        return pessoaServicoID;
    }

    public void setPessoaServicoID(int pessoaServicoID) {
        this.pessoaServicoID = pessoaServicoID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
