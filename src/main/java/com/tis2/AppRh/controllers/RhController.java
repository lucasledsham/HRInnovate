package com.tis2.AppRh.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.VagasCriadas;

import com.tis2.AppRh.services.RhService;

@RestController
@RequestMapping(value = "/rh")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RhController {

    @Autowired
    private RhService service;

    @GetMapping
    public ResponseEntity<List<Rh>> findAll() {
        List<Rh> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<Rh>> findById(@PathVariable String id) {
        Optional<Rh> obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')") 
    @PostMapping(value = "/criar-vaga")
    public ResponseEntity<VagasCriadas> createVaga(@RequestBody VagasCriadas vaga) {
        VagasCriadas novaVaga = service.create(vaga);
        return ResponseEntity.ok(novaVaga);
    }

    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')") 
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarCandidato(@PathVariable Long id) {
        service.aprovarCandidato(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')")  
    @PutMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarCandidato (@PathVariable Long id) {
        service.reprovarCandidato(id);
        return ResponseEntity.noContent().build();
    }


}
