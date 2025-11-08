package br.com.gestor.projetosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestor.projetosapi.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByProjetoId(Long projetoId);
    
    List<Tarefa> findByProjetoIdAndDescricaoContainingIgnoreCase(Long projetoId, String descricao);
}
