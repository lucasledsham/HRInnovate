package com.tis2.AppRh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tis2.AppRh.entities.Entrevista;
import com.tis2.AppRh.entities.Notificacao;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.services.NotificacaoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping (value = "/notificacoes")
@CrossOrigin ("http://127.0.0.1:5500")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> findById(@PathVariable Long id) {
        Notificacao notificacao = notificacaoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        return ResponseEntity.ok(notificacao);
    }

    @PutMapping("/marcarLida/{id}")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id) {
        notificacaoService.marcarComoLida(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contarNaoLidas")
    public ResponseEntity<Integer> contarNaoLidas() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int count = notificacaoService.contarNaoLidasPorUsuario(user.getId());
    return ResponseEntity.ok(count);
}
}
