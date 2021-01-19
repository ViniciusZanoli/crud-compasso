package br.com.compasso.crud.repository;

import br.com.compasso.crud.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadesRepository extends JpaRepository<Cidade, Long> {
  Cidade findByNome(String nome);
  Cidade findByEstado(String estado);
}
