package models;

public class Pessoa {
  // Atributos
  private int pessoa_id;
  private String nome;
  private String login;
  private String telefone;
  private String email;
  private String senha;


  // Construtor vazio
  public Pessoa(){
    
  }

  // Construtor com parâmetros
  public Pessoa(String nome, String login, String telefone, String email, String senha){
    this.nome = nome;
    this.login = login;
    this.telefone = telefone;
    this.email = email;
    this.senha = senha;
  }

  // Métodos Getters e Setters
  public int getPessoa_id() {
    return pessoa_id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
