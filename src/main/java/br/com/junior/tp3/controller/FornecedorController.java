package br.com.junior.tp3.controller;

import br.com.junior.tp3.model.Fornecedor;
import br.com.junior.tp3.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService service;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscar(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody @Valid Fornecedor fornecedor, UriComponentsBuilder uriBuilder) {
        Fornecedor criado = service.criar(fornecedor);
        URI location = uriBuilder.path("/api/fornecedores/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @RequestBody @Valid Fornecedor dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
