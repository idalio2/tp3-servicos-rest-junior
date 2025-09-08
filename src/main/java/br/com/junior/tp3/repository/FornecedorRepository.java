package br.com.junior.tp3.repository;

import br.com.junior.tp3.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByDocumentoFiscal(String documentoFiscal);
}
