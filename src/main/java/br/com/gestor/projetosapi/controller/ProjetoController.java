package br.com.gestor.projetosapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestor.projetosapi.model.Projeto;
import br.com.gestor.projetosapi.model.Tarefa;
import br.com.gestor.projetosapi.service.ProjetoService;
import br.com.gestor.projetosapi.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final TarefaService tarefaService;
    public ProjetoController(ProjetoService projetoService, TarefaService tarefaService) {
        this.projetoService = projetoService;
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Projeto> criar(@Valid @RequestBody Projeto projeto) {
        Projeto criado = projetoService.criarProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public List<Projeto> listar() {
        return projetoService.listarProjetos();
    }

    @GetMapping("/nome/{nomeDoProjeto}")
    public List<Projeto> buscarPorNome(@PathVariable("nomeDoProjeto") String nomeDoProjeto) {
        return projetoService.listarPorNome(nomeDoProjeto);
    }

    @GetMapping("/{id}")
    public Projeto buscar(@PathVariable Long id) {
        return projetoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Projeto atualizar(@PathVariable Long id, @Valid @RequestBody Projeto projeto) {
        return projetoService.atualizarProjeto(id, projeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tarefas/{nomeDaTarefa}")
    public List<Tarefa> buscarTarefasPorProjetoENome(@PathVariable("id") Long projetoId,
                                                    @PathVariable("nomeDaTarefa") String nomeDaTarefa) {
        return tarefaService.listarPorProjetoENome(projetoId, nomeDaTarefa);
    }
}
