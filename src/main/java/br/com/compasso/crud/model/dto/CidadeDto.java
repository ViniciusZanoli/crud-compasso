package br.com.compasso.crud.model.dto;

import br.com.compasso.crud.model.Cidade;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CidadeDto {
  private Long id;
  private String nome;
  private String estado;

  public CidadeDto() {
  }

  public CidadeDto(Long id, String nome, String estado) {
    this.id = id;
    this.nome = nome;
    this.estado = estado;
  }

  public CidadeDto(Cidade cidade) {
    this.id = cidade.getId();
    this.nome = cidade.getNome();
    this.estado = cidade.getEstado();
  }

  public static List<CidadeDto> converteList(List<Cidade> cidades) {
    return cidades.stream().map(CidadeDto::new).collect(Collectors.toList());
  }

  public static CidadeDto converter(Cidade cidade) {
    return new CidadeDto(cidade);
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
}
