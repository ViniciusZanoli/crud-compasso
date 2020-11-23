package br.com.compasso.crud.model.dto;

import br.com.compasso.crud.model.Cliente;

public class ClienteForm {

  private String nomeCompleto;
  private String sexo;
  private String dataDeNacimento;
  private int idade;
  private String cidade_id;

  public ClienteForm() {
  }

  public ClienteForm(String nomeCompleto, String sexo, String dataDeNacimento, int idade, String cidade_id) {
    this.nomeCompleto = nomeCompleto;
    this.sexo = sexo;
    this.dataDeNacimento = dataDeNacimento;
    this.idade = idade;
    this.cidade_id = cidade_id;
  }

  public static Cliente converte(ClienteForm form) {
    return new Cliente(form);

  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public String getSexo() {
    return sexo;
  }

  public String getDataDeNacimento() {
    return dataDeNacimento;
  }

  public int getIdade() {
    return idade;
  }

  public String getCidade_id() {
    return cidade_id;
  }


}
