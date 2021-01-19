package br.com.compasso.crud.controller;

import br.com.compasso.crud.converter.DozerConverter;
import br.com.compasso.crud.model.Cliente;
import br.com.compasso.crud.model.dto.AtualizaClienteDto;
import br.com.compasso.crud.model.dto.ClienteAtualizadoDto;
import br.com.compasso.crud.model.dto.ClienteDto;
import br.com.compasso.crud.model.dto.ClienteForm;
import br.com.compasso.crud.repository.CidadesRepository;
import br.com.compasso.crud.repository.ClientesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

  @Autowired
  private ClientesRepository clientesRepository;

  @Autowired
  private CidadesRepository cidadesRepository;

  @GetMapping
  public Page<ClienteDto> listar(
          @RequestParam(value="page", defaultValue="0") int page,
          @RequestParam(value="limit", defaultValue="12") int limit,
          @RequestParam(value="direction", defaultValue = "asc") String direction,
          @RequestParam(value="orderBy", defaultValue = "id") String orderBy) {

    var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

    Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, orderBy));

    var pagina = clientesRepository.findAll(pageable);

    return pagina.map(this::converteToCienteDto);
  }

  private ClienteDto converteToCienteDto(Cliente cliente) {
    return DozerConverter.parseObject(cliente, ClienteDto.class);
  }

  @GetMapping("/{id}")
  public ClienteDto detalhe(@PathVariable Long id) {

    var entity = clientesRepository.findById(id).
            orElseThrow(() -> new EntityExistsException("No records found for this ID"));

    ClienteDto clienteDto = DozerConverter.parseObject(entity, ClienteDto.class);

    clienteDto.add(linkTo(methodOn(ClientesController.class).detalhe(id)).withSelfRel());

    return clienteDto;
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
