package com.tis2.AppRh.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.VagasSolicitadas;
import com.tis2.AppRh.services.VagasSolicitadasService;

@RestController
@RequestMapping(value = "/vagas_solicitadas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VagasSolicitadasController {

    @Autowired
    private VagasSolicitadasService service;

    @GetMapping
    public ResponseEntity<List<VagasSolicitadas>> findAll() {
        List<VagasSolicitadas> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<VagasSolicitadas>> findById(@PathVariable Long id) {
        Optional<VagasSolicitadas> obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}

