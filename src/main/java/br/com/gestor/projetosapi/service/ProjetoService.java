package br.com.gestor.projetosapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestor.projetosapi.exception.ResourceNotFoundException;
import br.com.gestor.projetosapi.model.Projeto;
import br.com.gestor.projetosapi.repository.ProjetoRepository;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criarProjeto(Projeto projeto) {
        projeto.setDataCriacao(LocalDate.now());
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public List<Projeto> listarPorNome(String nome) {
        return projetoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id " + id));
    }

    public Projeto atualizarProjeto(Long id, Projeto dados) {
        Projeto p = buscarPorId(id);
        p.setNome(dados.getNome());
        p.setDescricao(dados.getDescricao());
        return projetoRepository.save(p);
    }

    public void deletarProjeto(Long id) {
        Projeto p = buscarPorId(id);
        projetoRepository.delete(p);
    }
}
