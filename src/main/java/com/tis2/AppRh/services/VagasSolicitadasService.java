package com.tis2.AppRh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.VagasSolicitadas;
import com.tis2.AppRh.repositories.GerenteRepository;
import com.tis2.AppRh.repositories.VagasSolicitadasRepository;

@Service
public class VagasSolicitadasService {
    
    @Autowired
    private VagasSolicitadasRepository repository;

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<VagasSolicitadas> findAll() {
        return repository.findAll();
    }

    public Optional<VagasSolicitadas> findById(Long id) {
        return repository.findById(id);
    }

   public VagasSolicitadas save(VagasSolicitadas vagaSolicitada) {

        return repository.save(vagaSolicitada);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
