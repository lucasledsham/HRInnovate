// src/main/java/com/tis2/AppRh/services/EntrevistaService.java

package com.tis2.AppRh.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.dto.EntrevistaDTO;
import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.Entrevista;
import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.repositories.CandidatoRepository;
import com.tis2.AppRh.repositories.EntrevistaRepository;
import com.tis2.AppRh.repositories.UserRepository;

@Service
public class EntrevistaService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private UserRepository userRepository;

    public EntrevistaDTO agendarEntrevista(Long candidatoId, EntrevistaDTO entrevistaDTO) {
        
        Optional<Candidato> candidatoOpt = candidatoRepository.findById(candidatoId);
        if (candidatoOpt.isEmpty()) {
            throw new RuntimeException("Candidato com ID " + candidatoId + " não encontrado.");
        }

        Candidato candidato = candidatoOpt.get();


        Entrevista entrevista = new Entrevista();
        entrevista.setCandidato(candidato);
        entrevista.setDataHora(LocalDateTime.parse(entrevistaDTO.getDataHora())); 
        entrevista.setLocal(entrevistaDTO.getLocal());
        entrevista.setObservacoes(entrevistaDTO.getObservacoes());

        entrevista = entrevistaRepository.save(entrevista);

        String mensagem = "Sua entrevista foi agendada para " + entrevista.getDataHora().toString() + " no local: " 
        + entrevista.getLocal() +
        ". Observações: " + entrevista.getObservacoes() + "\n Clique para confirmar ou recusar.";
        notificacaoService.criarNotificacaoEntrevista(entrevista, mensagem);

        return new EntrevistaDTO(
                entrevista.getId(),
                entrevista.getCandidato().getId(),
                entrevista.getDataHora().toString(),
                entrevista.getLocal(),
                entrevista.getObservacoes(),
                entrevista.getConfirmada().name()
        );
    }

     public Optional<Entrevista> findById(Long id) {
        return entrevistaRepository.findById(id);
    }
    }

     
    
