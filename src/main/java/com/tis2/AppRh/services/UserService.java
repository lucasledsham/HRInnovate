package com.tis2.AppRh.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.Entrevista;
import com.tis2.AppRh.entities.Entrevista.Confirmacao;
import com.tis2.AppRh.entities.enums.StatusCandidato;
import com.tis2.AppRh.repositories.EntrevistaRepository;
import com.tis2.AppRh.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Autowired
    private UserRepository userRepository;
    public void confirmarEntrevista(Long entrevistaId) {
        Optional<Entrevista> entrevistaOpt = entrevistaRepository.findById(entrevistaId);
        if (entrevistaOpt.isPresent()) {
            Entrevista entrevista = entrevistaOpt.get();
            entrevista.setConfirmada(Confirmacao.CONFIRMADA);;
            entrevistaRepository.save(entrevista);
        }
        else {
            throw new RuntimeException("Entrevista não encontrada com o ID: " +  entrevistaId);
        }
    }

    // Método para reprovar candidato
    public void recusarEntrevista(Long entrevistaId) {
        Optional<Entrevista> entrevistaOpt = entrevistaRepository.findById(entrevistaId);
        if (entrevistaOpt.isPresent()) {
            Entrevista entrevista = entrevistaOpt.get();
            entrevista.setConfirmada(Confirmacao.RECUSADA);;
            entrevistaRepository.save(entrevista);
        }
        else {
            throw new RuntimeException("Entrevista não encontrada com o ID: " +  entrevistaId);
        }
    }

    public Double calcularPorcentagemUsuariosCadastrados(Instant dataInicio, Instant dataFim) {
        Integer usuariosNoPeriodo = userRepository.getUsuariosCadastradosNoPeriodo(dataInicio, dataFim);
        Integer totalUsuarios = userRepository.getTotalUsuarios();

        if (totalUsuarios == 0) {
            return 0.0; 
        }

        return (usuariosNoPeriodo.doubleValue() / totalUsuarios.doubleValue()) * 100;
    }
}
