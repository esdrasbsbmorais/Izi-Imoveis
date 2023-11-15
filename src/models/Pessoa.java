package models;


public class Pessoa {
  // Atributos
  private int pessoa_id;
  private String nome;
  private String telefone;
  private String email;
  private int dt_nasc;
  private String senha;
  private boolean status;

  // Construtor vazio
  public Pessoa(){
    
  }

  // Construtor com parâmetros
  public Pessoa(int pessoa_id, String nome, String telefone, String email, int dt_nasc, String senha, boolean status){
    this.pessoa_id = pessoa_id;
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.dt_nasc = dt_nasc;
    this.senha = senha;
    this.status = status;
  }

  public int getPessoa_id() {
    return pessoa_id;
  }

  public void setPessoa_id(int pessoa_id) {
    this.pessoa_id = pessoa_id;
  }

  // Métodos Getters e Setters
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getDt_nasc() {
    return dt_nasc;
  }

  public void setDt_nasc(int dt_nasc) {
    this.dt_nasc = dt_nasc;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

}
