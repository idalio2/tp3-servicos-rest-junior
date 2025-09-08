package br.com.junior.tp3.service;

import br.com.junior.tp3.exception.ErroDeNegocioException;
import br.com.junior.tp3.exception.RecursoNaoEncontradoException;
import br.com.junior.tp3.model.Funcionario;
import br.com.junior.tp3.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repositorio;

    public List<Funcionario> listarTodos() {
        return repositorio.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado: " + id));
    }

    @Transactional
    public Funcionario criar(Funcionario funcionario) {
        repositorio.findByEmail(funcionario.getEmail())
                .ifPresent(f -> { throw new ErroDeNegocioException("E-mail já cadastrado"); });
        return repositorio.save(funcionario);
    }

    @Transactional
    public Funcionario atualizar(Long id, Funcionario dados) {
        Funcionario atual = buscarPorId(id);
        if (!atual.getEmail().equals(dados.getEmail())) {
            repositorio.findByEmail(dados.getEmail())
                    .ifPresent(f -> { throw new ErroDeNegocioException("E-mail já cadastrado"); });
        }
        atual.setNome(dados.getNome());
        atual.setCargo(dados.getCargo());
        atual.setSalario(dados.getSalario());
        atual.setDataAdmissao(dados.getDataAdmissao());
        atual.setEmail(dados.getEmail());
        return repositorio.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        Funcionario atual = buscarPorId(id);
        repositorio.delete(atual);
    }
}
