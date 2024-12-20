package com.tis2.AppRh.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.dto.IndicadorRequest;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.indicadores.IndicadorVagasPCategoria;
import com.tis2.AppRh.services.VagasCriadasService;

@RestController
@RequestMapping(value = "/vagas_criadas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VagasCriadasController {

    @Autowired
    private VagasCriadasService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFISSIONAL_RH', 'GERENTE')")
    public ResponseEntity<List<VagasCriadas>> findAll() {
        List<VagasCriadas> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('PROFISSIONAL_RH', 'GERENTE')")
    public ResponseEntity<VagasCriadas> findById(@PathVariable Long id) {
        VagasCriadas vaga = service.findById(id);
        return ResponseEntity.ok().body(vaga);
    }

    @GetMapping("/aprovadas")
    public ResponseEntity<List<VagasCriadas>> findAllApproved() {
        List<VagasCriadas> vagasAprovadas = service.findAllApprovedVagas();
        return ResponseEntity.ok(vagasAprovadas);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarVaga(@PathVariable Long id) {
        service.aprovarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarVaga(@PathVariable Long id) {
        service.reprovarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        try {
            service.deleteVaga(id); // Chama o método para deletar a vaga
            return ResponseEntity.noContent().build(); // Retorna 204 No Content em caso de sucesso
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se a vaga não for encontrada
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/indice/vagas-por-categoria")
    public ResponseEntity<IndicadorVagasPCategoria> obterPorcentagensVagasPorCategoria(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant endDate){

        IndicadorVagasPCategoria indicador = service.calcularPorcentagensVagasPorCategoria(startDate, endDate);

        return ResponseEntity.ok(indicador);
    }

}
