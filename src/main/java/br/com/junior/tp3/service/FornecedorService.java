package br.com.junior.tp3.service;

import br.com.junior.tp3.exception.ErroDeNegocioException;
import br.com.junior.tp3.exception.RecursoNaoEncontradoException;
import br.com.junior.tp3.model.Fornecedor;
import br.com.junior.tp3.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repositorio;

    public List<Fornecedor> listarTodos() { return repositorio.findAll(); }

    public Fornecedor buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: " + id));
    }

    @Transactional
    public Fornecedor criar(Fornecedor fornecedor) {
        repositorio.findByDocumentoFiscal(fornecedor.getDocumentoFiscal())
                .ifPresent(f -> { throw new ErroDeNegocioException("Documento fiscal já cadastrado"); });
        return repositorio.save(fornecedor);
    }

    @Transactional
    public Fornecedor atualizar(Long id, Fornecedor dados) {
        Fornecedor atual = buscarPorId(id);
        if (!atual.getDocumentoFiscal().equals(dados.getDocumentoFiscal())) {
            repositorio.findByDocumentoFiscal(dados.getDocumentoFiscal())
                    .ifPresent(f -> { throw new ErroDeNegocioException("Documento fiscal já cadastrado"); });
        }
        atual.setRazaoSocial(dados.getRazaoSocial());
        atual.setDocumentoFiscal(dados.getDocumentoFiscal());
        atual.setEmail(dados.getEmail());
        atual.setTelefone(dados.getTelefone());
        return repositorio.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
