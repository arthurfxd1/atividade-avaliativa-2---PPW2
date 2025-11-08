package br.com.gestor.projetosapi.controller;

import br.com.gestor.projetosapi.model.Tarefa;
import br.com.gestor.projetosapi.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Map<String, Object> body) {
        // espera: descricao, status (opcional), dataLimite (opcional), projetoId
        Long projetoId = ((Number) body.get("projetoId")).longValue();
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao((String) body.get("descricao"));
        if (body.get("status") != null) {
            tarefa.setStatus(br.com.gestor.projetosapi.model.Status.valueOf(((String) body.get("status")).toUpperCase()));
        }
        if (body.get("dataLimite") != null) {
            tarefa.setDataLimite(java.time.LocalDate.parse((String) body.get("dataLimite")));
        }
        Tarefa criado = tarefaService.criarTarefa(tarefa, projetoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/{id}")
    public Tarefa buscar(@PathVariable Long id) {
        return tarefaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.atualizarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
