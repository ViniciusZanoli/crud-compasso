package br.com.compasso.crud.model.dto;

import br.com.compasso.crud.model.Cliente;
import com.github.dozermapper.core.Mapping;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.RepresentationModel;


public class ClienteDto extends RepresentationModel{

  @Mapping("id")
  private Long key;
  private String nomeCompleto;
  private String sexo;
  private LocalDate dataDeNacimento;
  private int idade;
  private CidadeDto cidade;

  public ClienteDto() {
  }

  public ClienteDto(Long key, String nomeCompleto, String sexo, LocalDate dataDeNacimento, int idade, CidadeDto cidade) {
    this.key = key;
    this.nomeCompleto = nomeCompleto;
    this.sexo = sexo;
    this.dataDeNacimento = dataDeNacimento;
    this.idade = idade;
    this.cidade = cidade;
  }

  public ClienteDto(Cliente cliente) {
    this.key = cliente.getId();
    this.nomeCompleto = cliente.getNomeCompleto();
    this.sexo = cliente.getSexo();
    this.dataDeNacimento = cliente.getDataDeNacimento();
    this.idade = cliente.getIdade();
    this.cidade = CidadeDto.converter(cliente.getCidade());
  }

  public static List<ClienteDto> converteList(List<Cliente> clientes) {
    return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
  }

  public static ClienteDto converter(Cliente cliente) {
    return new ClienteDto(cliente);
  }

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
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

  public CidadeDto getCidade() {
    return cidade;
  }

  public void setCidade(CidadeDto cidade) {
    this.cidade = cidade;
  }
}