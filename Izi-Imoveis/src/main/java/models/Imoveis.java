package models;


public class Imoveis {
    //Atributos
    private int imoveis_id;
    private String tipo;
    private String descricao; 
    private int garagem;
    private float preco_aluguel;
    private int idade;
    // private String foto;
    private String endereco; 
    private int metros_quadrados;


    //Construtor vazio
    public Imoveis() {
    
    }


    //Construtor com parâmetros
    public Imoveis(String tipo, String descricao , int garagem, float preco_aluguel, int idade, String endereco, int metros_quadrados) {
        this.tipo = tipo;
        this.descricao = descricao; 
        this.garagem = garagem;
        this.preco_aluguel = preco_aluguel;
        this.idade = idade;
        // this.foto = foto;
        this.endereco = endereco; 
        this.metros_quadrados = metros_quadrados;
    }


    //Métodos Getters e Setters 
    public void setImoveis_id(int imoveis_id) {
        this.imoveis_id = imoveis_id;
    }    

    public int getImoveis_id() {
        return imoveis_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo; 
    }


    public String getDescricao(){
        return this.descricao; 
    }

    public void setDescricao(String descricao){
        this.descricao = descricao; 
    }


    public int getGaragem() {
        return garagem;
    }

    public void setGaragem(int garagem){
        this.garagem = garagem; 
    }


    public float getPreco_aluguel() {
        return preco_aluguel;
    }

    public void setPreco_aluguel(float preco_aluguel){
        this.preco_aluguel = preco_aluguel; 
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade; 
    }

    // public String getFoto(){
    //     return this.foto; 
    // }

    // public void setFoto(String foto){
    //     this.foto = foto; 
    // }


    public String getEndereco(){
        return this.endereco; 
    }

    public void setEndereco(String endereco){
        this.endereco = endereco; 
    }


    public int getMetros_quadrados() {
        return metros_quadrados;
    }

    public void setMetros_quadrados(int metrosQuadrados){
        this.metros_quadrados = metrosQuadrados; 
    }

}
