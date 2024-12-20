package com.tis2.AppRh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.enums.StatusCandidato;
import com.tis2.AppRh.entities.indicadores.IndiceAprovacoesCandidato;
import com.tis2.AppRh.repositories.CandidatoRepository;
import com.tis2.AppRh.repositories.IndiceAprovacoes_CandidatoRepository;
import com.tis2.AppRh.repositories.ProcessoSeletivoRepository;
import com.tis2.AppRh.repositories.UserRepository;

@Service
public class CandidatoService {

    @Autowired
    private ProcessoSeletivoRepository processoSeletivoRepository;
    
    @Autowired
    private IndiceAprovacoes_CandidatoRepository indiceAprovacoesRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato candidatar(Candidato candidato) {
         ProcessoSeletivo processoSeletivoOpt = processoSeletivoRepository.findById(candidato
         .getProcessoSeletivo().getId())
        .orElseThrow(() -> new RuntimeException("Processo Seletivo não encontrado"));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail(); 

        User user2 = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User não encontrado!"));

        candidato.setProcessoSeletivo(processoSeletivoOpt);
        candidato.setUser(user2);

        return candidatoRepository.save(candidato);
    }

    public byte[] getCurriculoById(Long candidatoId) {
        Candidato candidato = candidatoRepository.findById(candidatoId)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        return candidato.getCurriculo();
    }


    public double calcularIndiceAprovacao() {
        // Buscar total de candidaturas e candidaturas aprovadas
        int totalCandidaturas = candidatoRepository.countTotalCandidaturas();
        int totalAprovadas = candidatoRepository.countCandidaturasByStatus(StatusCandidato.APROVADO.getCode());

        // Evitar divisão por zero
        if (totalCandidaturas == 0) {
            return 0;
        }

        // Calcular o índice de aprovação
        double indice = ((double) totalAprovadas / totalCandidaturas) * 100;

        // Registrar o índice no banco
        IndiceAprovacoesCandidato registro = new IndiceAprovacoesCandidato(indice);
        indiceAprovacoesRepository.save(registro);

        return indice;
    }
}
