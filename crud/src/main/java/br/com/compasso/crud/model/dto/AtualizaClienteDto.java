package br.com.compasso.crud.model.dto;

import br.com.compasso.crud.model.Cidade;
import br.com.compasso.crud.model.Cliente;
import br.com.compasso.crud.repository.ClientesRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AtualizaClienteDto {

  @NotNull
  @NotEmpty
  private String nomeCompleto;
  @NotNull
  @NotEmpty
  private String sexo;
  @NotNull
  @NotEmpty
  private String dataDeNacimento;
  @NotNull
  private int idade = 0;
  @NotNull
  @NotEmpty
  private String cidade_id;

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

  public Cliente atualizar(Long id, ClientesRepository clientesRepository) {
    Cliente cliente = clientesRepository.getOne(id);
    cliente.setNomeCompleto(this.nomeCompleto);
    cliente.setSexo(this.sexo);
    cliente.setIdade(this.idade);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(this.dataDeNacimento, formatter);
    cliente.setDataDeNacimento(date);


    if (this.cidade_id != null) {
      Cidade cidade = new Cidade();
      cidade.setId(Long.parseLong(this.cidade_id));
      cliente.setCidade(cidade);
    }

    return cliente;
  }
}
