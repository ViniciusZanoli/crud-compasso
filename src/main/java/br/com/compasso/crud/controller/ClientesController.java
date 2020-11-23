package br.com.compasso.crud.controller;

import br.com.compasso.crud.model.Cliente;
import br.com.compasso.crud.model.dto.AtualizaClienteDto;
import br.com.compasso.crud.model.dto.ClienteAtualizadoDto;
import br.com.compasso.crud.model.dto.ClienteDto;
import br.com.compasso.crud.model.dto.ClienteForm;
import br.com.compasso.crud.repository.CidadesRepository;
import br.com.compasso.crud.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

  @Autowired
  private ClientesRepository clientesRepository;

  @Autowired
  private CidadesRepository cidadesRepository;

  @GetMapping
  public List<ClienteDto> listar() {
    List<Cliente> clientes = clientesRepository.findAll();
    return ClienteDto.converteList(clientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteDto> detalhe(@PathVariable Long id) {
    Optional<Cliente> cliente = clientesRepository.findById(id);

    return cliente.map(value -> ResponseEntity.
            ok(new ClienteDto(value))).
            orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/nome")
  public ClienteDto detalhePorNome(@RequestParam String nome) {
    Cliente cliente = clientesRepository.findByNomeCompleto(nome);
    return ClienteDto.converter(cliente);
  }

  @Transactional
  @PostMapping
  public ClienteDto cadastrar(@RequestBody ClienteForm form) {

    Cliente cliente = ClienteForm.converte(form);

    ExampleMatcher modelMatcher = ExampleMatcher.matching().withMatcher("nome", ignoreCase());

    Example<Cliente> example = Example.of(cliente, modelMatcher);

    if (clientesRepository.exists(example)) {
      throw new EntityExistsException(cliente.getNomeCompleto() + " Já existe");
    }

    return ClienteDto.converter(clientesRepository.save(cliente));
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<ClienteAtualizadoDto> alterar(@PathVariable Long id, @RequestBody @Valid AtualizaClienteDto form) {
    Optional<Cliente> optional = clientesRepository.findById(id);
    if (optional.isPresent()) {
      Cliente cliente = form.atualizar(id, clientesRepository);
      return ResponseEntity.ok(new ClienteAtualizadoDto(cliente));
    }
    return ResponseEntity.notFound().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    Optional<Cliente> optional = clientesRepository.findById(id);
    if (optional.isPresent()) {
      clientesRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
