package com.tis2.AppRh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.enums.StatusProcesso;
import com.tis2.AppRh.services.ProcessoSeletivoService;


@RestController
@RequestMapping(value = "/processo_seletivo")
public class ProcessoSeletivoController {
    
    @Autowired
    private ProcessoSeletivoService service;

     @PostMapping("/criar")
    public ProcessoSeletivo criar(@RequestBody VagasCriadas vaga) {
        return service.criarProcesso(vaga);
    }

    @PutMapping("/finalizar/{id}")
    public void finalizarProcesso(@PathVariable Long id) {
        service.finalizarProcesso(id);
    }

    @GetMapping
    public ResponseEntity<List<ProcessoSeletivo>> findAll() {
        List<ProcessoSeletivo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/emAberto")
    public ResponseEntity<List<ProcessoSeletivo>> findAllEmAberto() {
        List<ProcessoSeletivo> processosEmAberto = service.findByStatus(StatusProcesso.EM_ABERTO);
        return ResponseEntity.ok(processosEmAberto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoSeletivo> findById(@PathVariable Long id) {
        ProcessoSeletivo processo = service.findById(id)
            .orElseThrow(() -> new RuntimeException("Processo Seletivo não encontrado"));
        return ResponseEntity.ok(processo);
    }

    @GetMapping("/{id}/candidatos")
    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')") 
    public ResponseEntity<List<Candidato>> getCandidatosByProcessoId(@PathVariable Long id) {
        List<Candidato> candidatos = service.getCandidatosByProcessoSeletivoId(id);
        return ResponseEntity.ok(candidatos);
    }

    @PutMapping("/{processoId}/candidato/{candidatoId}/aprovar")
    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')")
    public ResponseEntity<Void> aprovarCandidato(@PathVariable Long processoId, @PathVariable Long candidatoId) {
        try {
            service.aprovarCandidato(processoId, candidatoId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // ou outro código de erro adequado
        }
    }

    @PutMapping("/{processoId}/candidato/{candidatoId}/reprovar")
    @PreAuthorize("hasRole('ROLE_PROFISSIONAL_RH')")
    public ResponseEntity<Void> reprovarCandidato(@PathVariable Long processoId, @PathVariable Long candidatoId) {
        try {
            service.reprovarCandidato(processoId, candidatoId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // ou outro código de erro adequado
        }
    }

}
