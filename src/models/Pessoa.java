package models;

import java.io.Serial;

public class Pessoa {
  // Atributos
  private Serial pessoa_id;
  private String nome;
  private String telefone;
  private String endereco;
  private String email;
  private int dt_nasc;
  private String tipo_usuario; //Lembrar de perguntar o tipo
  private String senha;
  private boolean status;

  // Construtor vazio
  public Pessoa(){
    
  }

  // Construtor com parâmetros
  public Pessoa(Serial pessoa_id, String nome, String telefone, String endereco, String email, int dt_nasc, String tipo_usuario, String senha, boolean status){
    this.pessoa_id = pessoa_id;
    this.nome = nome;
    this.telefone = telefone;
    this.endereco = endereco;
    this.email = email;
    this.dt_nasc = dt_nasc;
    this.tipo_usuario = tipo_usuario;
    this.senha = senha;
    this.status = status;
  }

  public Serial getPessoa_id() {
    return pessoa_id;
  }

  public void setPessoa_id(Serial pessoa_id) {
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

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
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

  public String getTipo_usuario() {
    return tipo_usuario;
  }

  public void setTipo_usuario(String tipo_usuario) {
    this.tipo_usuario = tipo_usuario;
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
