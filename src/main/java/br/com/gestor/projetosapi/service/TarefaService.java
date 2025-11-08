package br.com.gestor.projetosapi.service;

import br.com.gestor.projetosapi.exception.ResourceNotFoundException;
import br.com.gestor.projetosapi.model.Projeto;
import br.com.gestor.projetosapi.model.Tarefa;
import br.com.gestor.projetosapi.repository.ProjetoRepository;
import br.com.gestor.projetosapi.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.projetoRepository = projetoRepository;
    }

    public Tarefa criarTarefa(Tarefa tarefa, Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id " + projetoId));
        tarefa.setProjeto(projeto);
        if (tarefa.getStatus() == null) {
            tarefa.setStatus(br.com.gestor.projetosapi.model.Status.PENDENTE);
        }
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id " + id));
    }

    public Tarefa atualizarTarefa(Long id, Tarefa dados) {
        Tarefa t = buscarPorId(id);
        t.setDescricao(dados.getDescricao());
        t.setStatus(dados.getStatus());
        t.setDataLimite(dados.getDataLimite());
        return tarefaRepository.save(t);
    }

    public void deletarTarefa(Long id) {
        Tarefa t = buscarPorId(id);
        tarefaRepository.delete(t);
    }

    public List<Tarefa> listarPorProjeto(Long projetoId) {
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com id " + projetoId);
        }
        return tarefaRepository.findByProjetoId(projetoId);
    }

    public List<Tarefa> listarPorProjetoENome(Long projetoId, String nomeDaTarefa) {
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com id " + projetoId);
        }
        return tarefaRepository.findByProjetoIdAndDescricaoContainingIgnoreCase(projetoId, nomeDaTarefa);
    }
}
