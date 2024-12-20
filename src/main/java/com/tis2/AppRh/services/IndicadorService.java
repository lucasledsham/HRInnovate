package com.tis2.AppRh.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.indicadores.IndicadorPorcentagemUser;
import com.tis2.AppRh.repositories.IndicadorRepository;
import com.tis2.AppRh.repositories.UserRepository;

@Service
public class IndicadorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IndicadorRepository indicadorRepository;

    public IndicadorPorcentagemUser gerarIndicador(LocalDate dataInicio, LocalDate dataFim) {
        Instant inicioInstant = dataInicio.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant();
        Instant fimInstant = dataFim.atTime(23, 59, 59).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();

        // Consulta os dados
        Integer usuariosNoPeriodo = userRepository.getUsuariosCadastradosNoPeriodo(inicioInstant, fimInstant);
        Integer totalUsuarios = userRepository.getTotalUsuarios();

        Double porcentagem = (totalUsuarios == 0) ? 0.0 : (usuariosNoPeriodo.doubleValue() / totalUsuarios.doubleValue()) * 100;

        // Cria e salva no banco
        IndicadorPorcentagemUser indicador = new IndicadorPorcentagemUser();
        indicador.setDataInicio(dataInicio); // Utiliza LocalDate diretamente
        indicador.setDataFim(dataFim); // Utiliza LocalDate diretamente
        indicador.setUsuariosCadastrados(usuariosNoPeriodo);
        indicador.setPorcentagem(porcentagem);

        return indicadorRepository.save(indicador);
    }
}

