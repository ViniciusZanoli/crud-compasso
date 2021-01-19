package br.com.compasso.crud.model;

import br.com.compasso.crud.model.dto.ClienteForm;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nomeCompleto;
  private String sexo;
  private LocalDate dataDeNacimento;
  private int idade;
  @OneToOne
  private Cidade cidade;

  public Cliente() {
  }

  public Cliente(Long id, String nomeCompleto, String sexo, LocalDate dataDeNacimento, int idade, Cidade cidade) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
    this.sexo = sexo;
    this.dataDeNacimento = dataDeNacimento;
    this.idade = idade;
    this.cidade = cidade;
  }

  public Cliente(ClienteForm form) {
    this.nomeCompleto = form.getNomeCompleto();
    this.sexo = form.getSexo();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(form.getDataDeNacimento(), formatter);
    this.dataDeNacimento = date;
    this.idade = form.getIdade();
    this.cidade = new Cidade(form);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public LocalDate getDataDeNacimento() {
    return dataDeNacimento;
  }

  public void setDataDeNacimento(LocalDate dataDeNacimento) {
    this.dataDeNacimento = dataDeNacimento;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }
}
