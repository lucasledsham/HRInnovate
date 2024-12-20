package com.tis2.AppRh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.enums.StatusVaga;
import com.tis2.AppRh.repositories.GerenteRepository;
import com.tis2.AppRh.repositories.VagasCriadasRepository;


@Service
public class GerenteService {
    @Autowired
    private GerenteRepository repository;

    @Autowired
    private VagasCriadasRepository vagasCriadasRepository; 

     public List<Gerente> findAll() {
        return repository.findAll();
    }

    public Optional<Gerente> findById(String id) {
        return repository.findById(id);
    }

    public Gerente save(Gerente gerente) {
        return repository.save(gerente);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

  
    // Método para aprovar uma vaga
    public void aprovarVaga(Long vagaId) {
        Optional<VagasCriadas> vagaOptional = vagasCriadasRepository.findById(vagaId);
        if (vagaOptional.isPresent()) {
            VagasCriadas vaga = vagaOptional.get();
            vaga.setStatusVaga(StatusVaga.APROVADO);
            vagasCriadasRepository.save(vaga);
        }
        else {
            throw new RuntimeException("Vaga não encontrada com o ID: " + vagaId);
        }
    }

    // Método para reprovar uma vaga
    public void reprovarVaga(Long vagaId) {
        Optional<VagasCriadas> vagaOptional = vagasCriadasRepository.findById(vagaId);
        if (vagaOptional.isPresent()) {
            VagasCriadas vaga = vagaOptional.get();
            vaga.setStatusVaga(StatusVaga.REPROVADO);
            vagasCriadasRepository.save(vaga);
        }
        else {
            throw new RuntimeException("Vaga não encontrada com o ID: " + vagaId);
        }
    }

}
