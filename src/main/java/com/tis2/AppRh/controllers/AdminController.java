package com.tis2.AppRh.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.dto.RegisterRequestDTO;
import com.tis2.AppRh.dto.ResponseDTO;
import com.tis2.AppRh.entities.Gerente;
import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.enums.Role;
import com.tis2.AppRh.infra.security.TokenService;
import com.tis2.AppRh.repositories.GerenteRepository;
import com.tis2.AppRh.repositories.RhRepository;

import com.tis2.AppRh.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminController {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final TokenService tokenService;

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RhRepository rhRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cadastrarGerente")
    public ResponseEntity cadastrarGerente(@RequestBody RegisterRequestDTO body) {
        Optional<Gerente> gerente = this.gerenteRepository.findByEmail(body.email());

        if (gerente.isEmpty()) {
            Gerente newGerente = new Gerente();
            newGerente.setPassword(passwordEncoder.encode(body.password()));
            newGerente.setEmail(body.email());
            newGerente.setName(body.name());
            newGerente.setRole(Role.GERENTE);
            this.gerenteRepository.save(newGerente);

            String token = this.tokenService.generateToken(newGerente);
            return ResponseEntity.ok(new ResponseDTO(newGerente.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cadastrarRh")
    public ResponseEntity cadastrarRh(@RequestBody RegisterRequestDTO body) {
        Optional<Rh> rh = this.rhRepository.findByEmail(body.email());

        if (rh.isEmpty()) {
            Rh newRh = new Rh();
            newRh.setPassword(passwordEncoder.encode(body.password()));
            newRh.setEmail(body.email());
            newRh.setName(body.name());
            newRh.setRole(Role.PROFISSIONAL_RH);
            this.rhRepository.save(newRh);

            String token = this.tokenService.generateToken(newRh);
            return ResponseEntity.ok(new ResponseDTO(newRh.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    // ---------------------------------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/calcular_porcentagem")
    public Double getPorcentagemUsuariosCadastrados(
            @RequestParam(value = "dataInicio", required = true) String dataInicio,
            @RequestParam(value = "dataFim", required = true) String dataFim) {

        if (dataInicio == null || dataFim == null || dataInicio.isBlank() || dataFim.isBlank()) {
            throw new IllegalArgumentException("As datas de início e fim são obrigatórias!");
        }

        // Convertendo as strings para LocalDate
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        // Convertendo LocalDate para Instant em BRT
        Instant inicioInstant = inicio.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant();
        Instant fimInstant = fim.atTime(23, 59, 59).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();

        return userService.calcularPorcentagemUsuariosCadastrados(inicioInstant, fimInstant);
    }
}
