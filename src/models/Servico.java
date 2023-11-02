package models;

public class Servico {
    // Atributos
    protected String servico_tipo;
    protected int imoveis_id;
    protected int pessoas_id;

    // Construtor vazio
    public Servico() {

    }

    public Servico(String servico_tipo, int imoveis_id, int pessoas_id){
        this.servico_tipo = servico_tipo;
        this.imoveis_id = imoveis_id;
        this.pessoas_id = pessoas_id;
    }
    // Métodos Getters e Setters
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
}
