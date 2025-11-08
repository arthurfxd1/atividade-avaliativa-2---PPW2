package br.com.gestor.projetosapi.repository;

import br.com.gestor.projetosapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByProjetoId(Long projetoId);
    
    List<Tarefa> findByProjetoIdAndDescricaoContainingIgnoreCase(Long projetoId, String descricao);
}
