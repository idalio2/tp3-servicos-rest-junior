package br.com.junior.tp3.service;

import br.com.junior.tp3.exception.RecursoNaoEncontradoException;
import br.com.junior.tp3.model.Projeto;
import br.com.junior.tp3.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository repositorio;

    public List<Projeto> listarTodos() { return repositorio.findAll(); }

    public Projeto buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado: " + id));
    }

    @Transactional
    public Projeto criar(Projeto projeto) { return repositorio.save(projeto); }

    @Transactional
    public Projeto atualizar(Long id, Projeto dados) {
        Projeto atual = buscarPorId(id);
        atual.setTitulo(dados.getTitulo());
        atual.setDescricao(dados.getDescricao());
        atual.setStatus(dados.getStatus());
        atual.setDataInicio(dados.getDataInicio());
        atual.setDataFimPrevista(dados.getDataFimPrevista());
        return repositorio.save(atual);
    }

    @Transactional
    public void deletar(Long id) { repositorio.delete(buscarPorId(id)); }
}
