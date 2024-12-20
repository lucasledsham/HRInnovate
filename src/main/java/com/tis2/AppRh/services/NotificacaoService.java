package com.tis2.AppRh.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis2.AppRh.entities.Candidato;
import com.tis2.AppRh.entities.Entrevista;
import com.tis2.AppRh.entities.Notificacao;
import com.tis2.AppRh.entities.ProcessoSeletivo;
import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.enums.TipoNotificacao;
import com.tis2.AppRh.repositories.CandidatoRepository;
import com.tis2.AppRh.repositories.NotificacaoRepository;
import com.tis2.AppRh.repositories.UserRepository;


@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private UserRepository userRepository;

    
    public List<Notificacao> findByUserId(String userId) {
        return notificacaoRepository.findByUserId(userId);
    }

    public void criarNotificacao(Candidato candidato, String mensagem, ProcessoSeletivo processoSeletivo) {
    Optional<User> userOptional = userRepository.findById(candidato.getUser().getId());
    if (userOptional.isPresent()) {
        User user = userOptional.get();

        Notificacao notificacao = new Notificacao();
        notificacao.setUser(user);
        notificacao.setMensagem(mensagem);
        notificacao.setLida(false);
        notificacao.setProcessoSeletivo(processoSeletivo); 
        notificacao.setTipoNotificacao(TipoNotificacao.TIPO_APROVACAO);

       
        notificacaoRepository.save(notificacao);
    } else {
        throw new RuntimeException("Usuário do candidato não encontrado");
    }

}
    public void criarNotificacaoEntrevista(Entrevista entrevista, String mensagem) {
        Optional<User> userOptional = userRepository.findById(entrevista.getCandidato().getUser().getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
    
            // Cria uma nova notificação
            Notificacao notificacao = new Notificacao();
            notificacao.setUser(user);
            notificacao.setMensagem(mensagem);
            notificacao.setLida(false); // Marca como não lida
            notificacao.setEntrevista(entrevista); // Associa a notificação à entrevista específica
            notificacao.setTipoNotificacao(TipoNotificacao.TIPO_ENTREVISTA);
    
            // Salva a notificação no banco de dados
            notificacaoRepository.save(notificacao);
        } else {
            throw new RuntimeException("Usuário do candidato não encontrado");
        }
    }
    
    public void marcarComoLida(Long notificacaoId) {
    Optional<Notificacao> notificacao = notificacaoRepository.findById(notificacaoId);
    if (notificacao.isPresent()) {
        Notificacao notif = notificacao.get();
        notif.setLida(true);
        notificacaoRepository.save(notif);
    }
}

public Integer contarNaoLidasPorUsuario(String userId) {
    return notificacaoRepository.countByUserIdAndLidaFalse(userId);
}

public Optional<Notificacao> findById(Long id) {
    return notificacaoRepository.findById(id);
}

}
