package models;


public class Imoveis {
    //Atributos
    private int imoveis_id;
    private int qtd_quartos;
    private int qtd_banheiro;
    private int qtd_andares; 
    private String tipo;
    private String descricao; 
    private int garagem;
    private float preco;
    private int idade;
    private int suite;
    private String foto;
    private String endereco; 
    private int metros_quadrados;


    //Construtor vazio
    public Imoveis() {
    
    }


    //Construtor com parâmetros
    public Imoveis(int qtd_quartos, int qtd_banheiro, int qtd_andares , String tipo, String descricao , int garagem, float preco, int idade, int suite, String foto, String endereco, int metros_quadrados) {
        this.qtd_quartos = qtd_quartos;
        this.qtd_banheiro = qtd_banheiro;
        this.qtd_andares = qtd_andares;
        this.tipo = tipo;
        this.descricao = descricao; 
        this.garagem = garagem;
        this.preco = preco;
        this.idade = idade;
        this.suite = suite;
        this.foto = foto;
        this.endereco = endereco; 
        this.metros_quadrados = metros_quadrados;
    }



    //Métodos Getters e Setters 

    public int getImoveis_id() {
        return imoveis_id;
    }

    
    public int getQtd_quartos() {
        return qtd_quartos;
    }

    public void setQtd_quartos(int quartos){
        this.qtd_quartos = quartos; 
    }


    public int getQtd_banheiro() {
        return qtd_banheiro;
    }

    public void setQtd_banheiro(int banheiros){
        this.qtd_banheiro = banheiros; 
    }


    public int getAndares(){
        return this.qtd_andares;
    }

    public void setAndares(int andares){
        this.qtd_andares = andares;
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


    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco){
        this.preco = preco; 
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade; 
    }


    public int getSuite() {
        return suite;
    }

    public void setSuite(int suite){
        this.suite = suite; 
    }


    public String getFoto(){
        return this.foto; 
    }

    public void setFoto(String foto){
        this.foto = foto; 
    }


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
