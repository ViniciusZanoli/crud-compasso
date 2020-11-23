package br.com.compasso.crud.model;

import br.com.compasso.crud.model.dto.ClienteForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String estado;

  public Cidade() {

  }

  public Cidade(Long id, String nome, String estado) {
    this.id = id;
    this.nome = nome;
    this.estado = estado;
  }

  public Cidade(ClienteForm form){
    this.id = Long.parseLong(form.getCidade_id());
  }


  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEstado() {
    return estado;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
