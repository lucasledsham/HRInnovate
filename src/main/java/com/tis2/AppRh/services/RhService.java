package com.tis2.AppRh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.VagasSolicitadas;
import com.tis2.AppRh.entities.enums.StatusCandidato;
import com.tis2.AppRh.repositories.CandidatoRepository;
import com.tis2.AppRh.repositories.RhRepository;
import com.tis2.AppRh.repositories.VagasCriadasRepository;
import com.tis2.AppRh.repositories.VagasSolicitadasRepository;

@Service
public class RhService {
    @Autowired
    private RhRepository repository;

    @Autowired
    private VagasSolicitadasRepository vagasSolicitadasRepository;

    @Autowired
    private VagasCriadasRepository vagasCriadasRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    public List<Rh> findAll() {
        return repository.findAll();
    }

    public Optional<Rh> findById(String id) {
        return repository.findById(id);
    }

    public Rh save(Rh rh) {
        return repository.save(rh);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

 public VagasCriadas create(VagasCriadas vaga) {
        VagasSolicitadas vagaSolicitadaOpt = vagasSolicitadasRepository.findById(vaga.getVagaSolicitada().getId())
                .orElseThrow(() -> new RuntimeException("Vaga Solicitada não encontrada"));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail();  

        
        Rh rh = carregarRh(email);

        vaga.setTitulo(vagaSolicitadaOpt.getTitulo());
        vaga.setCategoriaVaga(vagaSolicitadaOpt.getCategoriaVaga());

        vaga.setResponsavelRh(rh);

        return vagasCriadasRepository.save(vaga);
    }

    public void aprovarCandidato(Long candidatoId) {
        Optional<Candidato> candidatoOpt = candidatoRepository.findById(candidatoId);
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            candidato.setStatus(StatusCandidato.APROVADO);
            candidatoRepository.save(candidato);
        }
        else {
            throw new RuntimeException("Candidato não encontrada com o ID: " + candidatoId);
        }
    }

    // Método para reprovar candidato
    public void reprovarCandidato(Long candidatoId) {
        Optional<Candidato> candidatoOpt = candidatoRepository.findById(candidatoId);
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            candidato.setStatus(StatusCandidato.REPROVADO);
            candidatoRepository.save(candidato);
        }
        else {
            throw new RuntimeException("Candidato não encontrada com o ID: " + candidatoId);
        }
    }

    private Rh carregarRh(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profissional de rh não encontrado!"));
    }
    

}
