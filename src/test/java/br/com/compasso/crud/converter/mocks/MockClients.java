package br.com.compasso.crud.converter.mocks;

import br.com.compasso.crud.model.Cliente;
import br.com.compasso.crud.model.dto.ClienteDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockClients {


  public Cliente mockEntity(){
    return mockEntity(0);
  }

  public ClienteDto mockDto(){
    return mockDto(0);
  }

  public List<Cliente> mockEntityList(){
    List<Cliente> clientes = new ArrayList<>();
    for (int i = 0; i < 15; i++){
      clientes.add(mockEntity(i));
    }
    return clientes;
  }

  public List<ClienteDto> mockDtoList(){
    List<ClienteDto> clientes = new ArrayList<>();
    for (int i = 0; i < 15; i++){
      clientes.add(mockDto(i));
    }
    return clientes;
  }


  private Cliente mockEntity(Integer number){
    Cliente cliente = new Cliente();
    cliente.setId(0L);
    cliente.setNomeCompleto("Teste" + number);
    cliente.setSexo(number % 2 == 0 ? "M" : "F");
    cliente.setDataDeNacimento(LocalDate.now());
    cliente.setIdade(20);
    return cliente;
  }

  private ClienteDto mockDto(Integer number){
    ClienteDto cliente = new ClienteDto();
    cliente.setKey(0L);
    cliente.setNomeCompleto("Teste" + number);
    cliente.setSexo(number % 2 == 0 ? "M" : "F");
    cliente.setDataDeNacimento(LocalDate.now());
    cliente.setIdade(20);
    return cliente;
  }

}
//  private Long id;
//  private String nomeCompleto;
//  private String sexo;
//  private LocalDate dataDeNacimento;
//  private int idade;