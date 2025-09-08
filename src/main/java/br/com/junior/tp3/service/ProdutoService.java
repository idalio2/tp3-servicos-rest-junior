package br.com.junior.tp3.service;

import br.com.junior.tp3.exception.ErroDeNegocioException;
import br.com.junior.tp3.exception.RecursoNaoEncontradoException;
import br.com.junior.tp3.model.Produto;
import br.com.junior.tp3.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repositorio;

    public List<Produto> listarTodos() { return repositorio.findAll(); }

    public Produto buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));
    }

    @Transactional
    public Produto criar(Produto produto) {
        repositorio.findBySku(produto.getSku())
                .ifPresent(p -> { throw new ErroDeNegocioException("SKU já cadastrado"); });
        return repositorio.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dados) {
        Produto atual = buscarPorId(id);
        if (!atual.getSku().equals(dados.getSku())) {
            repositorio.findBySku(dados.getSku())
                    .ifPresent(p -> { throw new ErroDeNegocioException("SKU já cadastrado"); });
        }
        atual.setNome(dados.getNome());
        atual.setDescricao(dados.getDescricao());
        atual.setPreco(dados.getPreco());
        atual.setSku(dados.getSku());
        atual.setAtivo(dados.isAtivo());
        return repositorio.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
