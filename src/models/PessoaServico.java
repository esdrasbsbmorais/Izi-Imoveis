package models;

public class PessoaServico extends Servico{
    private int pessoaServicoID; 
    private int data;
    private float valor;


    // Construtor sem parâmetros
    public PessoaServico(){

    }

    // Construtor com parâmetros
    public PessoaServico(int data, float valor) {
        this.data = data;
        this.valor = valor;
    }

    public PessoaServico(String servico_tipo, int imoveis_id, int pessoas_id, int data, float valor) {
        super(servico_tipo, imoveis_id, pessoas_id);
        this.data = data;
        this.valor = valor;
    }


    //Métodos Getters e Setters
    
    public int getPessoaServidoId(){
        return this.pessoaServicoID; 
    }
    
    
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
