package com.tis2.AppRh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.services.CandidatoService;



@RestController
@RequestMapping("/candidato")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CandidatoController {  

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/candidatar")
    public ResponseEntity<?> candidatar(@RequestPart("candidato") Candidato candidato,
                                         @RequestPart(value = "curriculo", required = false) MultipartFile curriculo) {
        try {
            if (curriculo != null && !curriculo.isEmpty()) {
                candidato.setCurriculo(curriculo.getBytes());
            }
            Candidato savedCandidato = candidatoService.candidatar(candidato);
            return ResponseEntity.ok(savedCandidato);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/curriculo")
    public ResponseEntity<Resource> downloadCurriculo(@PathVariable Long id) {
        byte[] pdfData = candidatoService.getCurriculoById(id);

        if (pdfData == null || pdfData.length == 0) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfData);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"curriculo.pdf\"")
                .body(resource);
    }


    @GetMapping("/indice-aprovacao")
    @PreAuthorize("hasRole('ADMIN')") 
    public double getIndiceAprovacao() {
        return candidatoService.calcularIndiceAprovacao();
    }
}

