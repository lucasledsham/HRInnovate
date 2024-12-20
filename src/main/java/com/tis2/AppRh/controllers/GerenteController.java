package com.tis2.AppRh.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.VagasSolicitadas;
import com.tis2.AppRh.repositories.GerenteRepository;
import com.tis2.AppRh.services.GerenteService;
import com.tis2.AppRh.services.VagasCriadasService;
import com.tis2.AppRh.services.VagasSolicitadasService;

@RestController
@RequestMapping(value = "/gerente")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class GerenteController {

    @Autowired
    private GerenteService service;

    @Autowired
    private VagasCriadasService vCservice;

    @Autowired
    private VagasSolicitadasService vagasSolicitadasService;
    
    @Autowired
    private GerenteRepository gerenteRepository;

    @GetMapping
    public ResponseEntity<List<Gerente>> findAll() {
        List<Gerente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<Gerente>> findById(@PathVariable String id) {
        Optional<Gerente> obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('GERENTE')")  
    @PostMapping("/solicitar-vaga")
    public VagasSolicitadas solicitarVaga(@RequestBody VagasSolicitadas vagaSolicitada) {

        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail();  

        
        Gerente gerente = carregarGerente(email);

        if (gerente == null) {
            throw new RuntimeException("Gerente não encontrado!");  
        }

        
        vagaSolicitada.setGerente(gerente);

        
        return vagasSolicitadasService.save(vagaSolicitada);
    }

    
    private Gerente carregarGerente(String email) {
        return gerenteRepository.findByEmail(email).orElse(null);
    }

    @PreAuthorize("hasRole('GERENTE')")  
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarVaga(@PathVariable Long id) {
        vCservice.aprovarVaga(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('GERENTE')")  
    @PutMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarVaga(@PathVariable Long id) {
        vCservice.reprovarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('GERENTE')")  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        try {
            vCservice.deleteVaga(id); // Chama o método para deletar a vaga
            return ResponseEntity.noContent().build(); // Retorna 204 No Content em caso de sucesso
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se a vaga não for encontrada
        }
    }

}