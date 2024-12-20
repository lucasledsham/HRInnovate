package com.tis2.AppRh.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tis2.AppRh.entities.Rh;
import com.tis2.AppRh.entities.VagasCriadas;
import com.tis2.AppRh.entities.VagasSolicitadas;
import com.tis2.AppRh.entities.enums.CategoriaVaga;
import com.tis2.AppRh.entities.enums.StatusVaga;
import com.tis2.AppRh.entities.indicadores.IndicadorVagasPCategoria;
import com.tis2.AppRh.repositories.IndicadorVagasPCategoriaRepository;
import com.tis2.AppRh.repositories.RhRepository;
import com.tis2.AppRh.repositories.VagasCriadasRepository;
import com.tis2.AppRh.repositories.VagasSolicitadasRepository;

@Service
public class VagasCriadasService {

    @Autowired
    private VagasCriadasRepository repository;

    @Autowired
    private IndicadorVagasPCategoriaRepository indicadorVagasPCategoriaRepository;

    @Autowired
    private VagasSolicitadasRepository vagasSolicitadasRepository;

    @Autowired
    private RhRepository repositoryRh;

    @Autowired
    private ProcessoSeletivoService processoSeletivoService;

    public List<VagasCriadas> findAll() {
        return repository.findAll();
    }

    public VagasCriadas findById(Long id) {
        Optional<VagasCriadas> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    public List<VagasCriadas> findAllApprovedVagas() {
        return repository.findByStatusVaga(StatusVaga.APROVADO);
    }

    public VagasCriadas create(VagasCriadas vaga) {
        VagasSolicitadas vagaSolicitadaOpt = vagasSolicitadasRepository.findById(vaga.getVagaSolicitada().getId())
                .orElseThrow(() -> new RuntimeException("Vaga Solicitada não encontrado"));

        Rh responsavelRhOpt = repositoryRh.findById(vaga.getResponsavelRh().getId())
                .orElseThrow(() -> new RuntimeException("Profissional do rh não encontrado"));
        ;

        vaga.setTitulo(vagaSolicitadaOpt.getTitulo());
        vaga.setCategoriaVaga(vagaSolicitadaOpt.getCategoriaVaga());

        vaga.setResponsavelRh(responsavelRhOpt);

        return repository.save(vaga);
    }

    public void aprovarVaga(Long id) {
        VagasCriadas vaga = findById(id);
        vaga.setStatusVaga(StatusVaga.APROVADO);
        repository.save(vaga);

        processoSeletivoService.criarProcesso(vaga);
    }

    public void reprovarVaga(Long id) {
        VagasCriadas vaga = findById(id);
        vaga.setStatusVaga(StatusVaga.REPROVADO);
        repository.save(vaga);
    }

    @Transactional
    public void deleteVaga(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id); // Deleta a vaga do banco
        } else {
            throw new IllegalArgumentException("Vaga com ID " + id + " não encontrada.");
        }
    }


    public IndicadorVagasPCategoria calcularPorcentagensVagasPorCategoria(Instant startDate, Instant endDate) {
        // Busca as vagas criadas dentro do intervalo de datas
        List<VagasCriadas> vagas = repository.findByDataCriacaoBetween(startDate, endDate);

        // Calcula o total de vagas
        long totalVagas = vagas.size();

        if (totalVagas == 0) {
            // Evitar divisão por zero
            return new IndicadorVagasPCategoria(0, Instant.now(), 0.0, 0.0, 0.0);
        }

        // Conta o número de vagas em cada categoria
        long countDesenvolvimento = vagas.stream()
                .filter(vaga -> vaga.getCategoriaVaga() == CategoriaVaga.DESENVOLVIMENTO)
                .count();
        long countAdministrativo = vagas.stream()
                .filter(vaga -> vaga.getCategoriaVaga() == CategoriaVaga.ADMINISTRATIVO)
                .count();
        long countFinanceiro = vagas.stream()
                .filter(vaga -> vaga.getCategoriaVaga() == CategoriaVaga.FINANCEIRO)
                .count();

        // Calcula os percentuais para cada categoria
        double percentualDesenvolvimento = (countDesenvolvimento * 100.0) / totalVagas;
        double percentualAdministrativo = (countAdministrativo * 100.0) / totalVagas;
        double percentualFinanceiro = (countFinanceiro * 100.0) / totalVagas;

        // Cria o objeto IndicadorVagasPCategoria com os dados calculados
        IndicadorVagasPCategoria indicador = new IndicadorVagasPCategoria(
                totalVagas,
                Instant.now(), // A data da consulta é a data atual
                percentualDesenvolvimento,
                percentualAdministrativo,
                percentualFinanceiro
        );

        indicadorVagasPCategoriaRepository.save(indicador);

        return indicador;
    }
}

