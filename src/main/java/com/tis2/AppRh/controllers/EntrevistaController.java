

package com.tis2.AppRh.controllers;

import com.tis2.AppRh.dto.EntrevistaDTO;
import com.tis2.AppRh.entities.Entrevista;
import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.services.EntrevistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @PostMapping("/agendar")
    public ResponseEntity<EntrevistaDTO> agendarEntrevista(@RequestBody EntrevistaDTO request) {
    EntrevistaDTO agendada = entrevistaService.agendarEntrevista(request.getCandidatoId(), request);
    return ResponseEntity.status(HttpStatus.CREATED).body(agendada);
}

@GetMapping("/{id}")
    public ResponseEntity<Entrevista> findById(@PathVariable Long id) {
        Entrevista entrevista = entrevistaService.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrevista não encontrada"));
        return ResponseEntity.ok(entrevista);
    }


   
}
