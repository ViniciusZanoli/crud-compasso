package br.com.compasso.crud.controller;

import br.com.compasso.crud.model.Cidade;
import br.com.compasso.crud.model.dto.CidadeDto;
import br.com.compasso.crud.repository.CidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

  @Autowired
  private CidadesRepository cidadesRepository;

  @GetMapping
  public List<CidadeDto> listar(){
    List<Cidade> cidades = cidadesRepository.findAll();
    return CidadeDto.converteList(cidades);
  }

  @GetMapping("/nome")
  public CidadeDto detalhePorNome(@RequestParam String nome){
    Cidade cidade = cidadesRepository.findByNome(nome);
    return CidadeDto.converter(cidade);
  }

  @GetMapping("/estado/{estado}")
  public CidadeDto detalhePorEstado(@PathVariable String estado){
    Cidade cidade = cidadesRepository.findByEstado(estado);
    return CidadeDto.converter(cidade);
  }

  @Transactional
  @PostMapping
  public CidadeDto cadastrar(@RequestBody @Valid Cidade cidade){
    ExampleMatcher modelMatcher = ExampleMatcher.matching().withMatcher("nome", ignoreCase());
    Example<Cidade> example = Example.of(cidade, modelMatcher);

    if (cidadesRepository.exists(example)){
      throw new EntityExistsException(cidade.getNome() + " Já existe");
    }

    return CidadeDto.converter(cidadesRepository.save(cidade));
  }

}
