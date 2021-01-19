package br.com.compasso.crud.repository;

import br.com.compasso.crud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
  Cliente findByNomeCompleto(String nome);
}
