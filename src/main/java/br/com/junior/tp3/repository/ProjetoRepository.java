package br.com.junior.tp3.repository;

import br.com.junior.tp3.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> { }
