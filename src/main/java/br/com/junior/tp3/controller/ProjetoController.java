package br.com.junior.tp3.controller;

import br.com.junior.tp3.model.Projeto;
import br.com.junior.tp3.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService service;

    @GetMapping
    public ResponseEntity<List<Projeto>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }

    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody @Valid Projeto projeto, UriComponentsBuilder uriBuilder) {
        Projeto criado = service.criar(projeto);
        URI location = uriBuilder.path("/api/projetos/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @RequestBody @Valid Projeto dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
