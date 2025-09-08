package br.com.junior.tp3.service;

import br.com.junior.tp3.exception.ErroDeNegocioException;
import br.com.junior.tp3.exception.RecursoNaoEncontradoException;
import br.com.junior.tp3.model.Cliente;
import br.com.junior.tp3.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repositorio;

    public List<Cliente> listarTodos() { return repositorio.findAll(); }

    public Cliente buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));
    }

    @Transactional
    public Cliente criar(Cliente cliente) {
        repositorio.findByEmail(cliente.getEmail())
                .ifPresent(c -> { throw new ErroDeNegocioException("E-mail já cadastrado"); });
        return repositorio.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente dados) {
        Cliente atual = buscarPorId(id);
        if (!atual.getEmail().equals(dados.getEmail())) {
            repositorio.findByEmail(dados.getEmail())
                    .ifPresent(c -> { throw new ErroDeNegocioException("E-mail já cadastrado"); });
        }
        atual.setNome(dados.getNome());
        atual.setEmail(dados.getEmail());
        atual.setTelefone(dados.getTelefone());
        atual.setTipo(dados.getTipo());
        atual.setDataCadastro(dados.getDataCadastro());
        return repositorio.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
