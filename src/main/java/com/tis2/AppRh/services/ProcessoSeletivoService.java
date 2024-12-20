package com.tis2.AppRh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.enums.StatusCandidato;
import com.tis2.AppRh.entities.enums.StatusProcesso;
import com.tis2.AppRh.repositories.CandidatoRepository;
import com.tis2.AppRh.repositories.ProcessoSeletivoRepository;

@Service
public class ProcessoSeletivoService {

    @Autowired
    private ProcessoSeletivoRepository repository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<ProcessoSeletivo> findAll() {
        return repository.findAll();
    }

   public Optional<ProcessoSeletivo> findById(Long id) {
        return repository.findById(id);
    }

    public ProcessoSeletivo criarProcesso(VagasCriadas vaga) {
        ProcessoSeletivo processoSeletivo = new ProcessoSeletivo(vaga);
        return repository.save(processoSeletivo);
    }

    public ProcessoSeletivo finalizarProcesso(Long id) {
        ProcessoSeletivo processo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Processo Seletivo não encontrado"));

        processo.setStatus(StatusProcesso.FINALIZADO.getCode());    
        
        return repository.save(processo);
    }

    public List<ProcessoSeletivo> findByStatus(StatusProcesso status) {
        return repository.findByStatus(status);
    }

    public List<Candidato> getCandidatosByProcessoSeletivoId(Long id) {
        ProcessoSeletivo processo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Processo Seletivo não encontrado"));

        return processo.getCandidatos();
    }


    public void aprovarCandidato(Long processoId, Long candidatoId) {
        
        Optional<ProcessoSeletivo> processoOpt = repository.findById(processoId);
        if (processoOpt.isPresent()) {
            ProcessoSeletivo processo = processoOpt.get();
    
            
            Optional<Candidato> candidatoOpt = candidatoRepository.findById(candidatoId);
            if (candidatoOpt.isPresent()) {
                Candidato candidato = candidatoOpt.get();
    
                
                candidato.setStatus(StatusCandidato.APROVADO);
    
                
                notificacaoService.criarNotificacao(
                    candidato,
                    ("Parabéns! Você foi aprovado. Agendaremos sua entrevista em breve. " +  processo.getVaga().getTitulo()) ,
                    processo 
                );
    
                
                candidatoRepository.save(candidato);
            } else {
                throw new RuntimeException("Candidato não encontrado no processo seletivo.");
            }
        } else {
            throw new RuntimeException("Processo seletivo não encontrado com o ID: " + processoId);
        }
    }
    

    
    public void reprovarCandidato(Long processoId, Long candidatoId) {
        Optional<ProcessoSeletivo> processoOpt = repository.findById(processoId);
        if (processoOpt.isPresent()) {
            ProcessoSeletivo processo = processoOpt.get();

            Optional<Candidato> candidatoOpt = candidatoRepository.findById(candidatoId);
            if (candidatoOpt.isPresent()) {
                Candidato candidato = candidatoOpt.get();
                candidato.setStatus(StatusCandidato.REPROVADO);
                candidatoRepository.save(candidato);
            }
            else {
                throw new RuntimeException("Candidato não encontrada com o ID: " + candidatoId);
            }
        } else {
            throw new RuntimeException("Processo seletivo não encontrado com o ID: " + processoId);
        }
    }
}


