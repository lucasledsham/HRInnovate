package com.tis2.AppRh.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.dto.IndicadorRequest;
import com.tis2.AppRh.entities.indicadores.IndicadorPorcentagemUser;
import com.tis2.AppRh.services.IndicadorService;

@RestController
@RequestMapping("/indicadores")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class IndicadoresController {
    @Autowired
    private IndicadorService indicadorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/gerar_porcentagem_user")
    public IndicadorPorcentagemUser gerarIndicador(@RequestBody IndicadorRequest indicadorRequest) {
        LocalDate inicio = LocalDate.parse(indicadorRequest.getDataInicio());
        LocalDate fim = LocalDate.parse(indicadorRequest.getDataFim());

        return indicadorService.gerarIndicador(inicio, fim);
    }
}
