package br.com.compasso.crud.model.dto;

import br.com.compasso.crud.model.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ClienteDto {
  private Long id;
  private String nomeCompleto;
  private String sexo;
  private LocalDate dataDeNacimento;
  private int idade;
  private CidadeDto cidade;

  public ClienteDto() {
  }

  public ClienteDto(Long id, String nomeCompleto, String sexo, LocalDate dataDeNacimento, int idade, CidadeDto cidade) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
    this.sexo = sexo;
    this.dataDeNacimento = dataDeNacimento;
    this.idade = idade;
    this.cidade = cidade;
  }

  public ClienteDto(Cliente cliente) {
    this.id = cliente.getId();
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

  public Long getId() {
    return id;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public String getSexo() {
    return sexo;
  }

  public LocalDate getDataDeNacimento() {
    return dataDeNacimento;
  }

  public int getIdade() {
    return idade;
  }

  public CidadeDto getCidade() {
    return cidade;
  }
}