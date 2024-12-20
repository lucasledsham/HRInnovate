### 3.3.4 Processo 4 – Agendamento de Entrevista e Processo Seletivo
O candidato é informado sobre o andamento do processo seletivo, que inclui o próximo passo relacionado ao agendamento da entrevista.O profissional de RH faz o agendamento.O candidato confirma ou recusa esse agendamento. Caso o candidato recuse, é necessário contatar a nossa empresa para reagenda caso for do interesse. Caso aceite, o processo segue para confirmação do agendamento.


![Lista 3_3-Page-1 drawio-2](https://github.com/user-attachments/assets/34310c5a-ec9d-4c8c-ad53-5f979bdfe3b2)


### Detalhamento das Atividades

Marca entrevista: Rh seleciona uma data e um horário para o candidato dar continuidade no seu processo seletivo. A entrevista é marcada, nela também é informada o local e as observações.


Confirmar agendamento: O candidato tem opção de confirmar ou rejeitar o agendamento da entrevista. Se confirmar, é informado que o candidato é aguardado no dia e no local. Se recusar, o candidato pode ligar para empresa e remarca-la


### Atividade 1: Marcar entrevista

| Campo                | Tipo             | Restrições                      | Valor default    |
|----------------------|------------------|---------------------------------|------------------|
| ID do Candidato       | Caixa de Texto   | Obrigatório                     | -                |
| Data da Entrevista             | Data            |  Formato dd-mm-aaaa                    | -                |
| Horário da Entrevista   | Horário             | Formato hh:mm              | Data atual       |
| Local da Entrevista   | Caixa de texto             | Máximo de 100 caracteres, obrigatório              | -       |
| Observações   | Área de texto             | Opcional, máximo de 200 caracteres              | -       |

| Comandos             | Destino           | Tipo      |
|----------------------|-------------------|-----------|
| Agendar entrevista   | Submeter ao aprovador | default   |
| Fechar    | Retornar ao formulário anterior | cancel   |

---

### Atividade 1: Confirmar entrevista

| Campo                | Tipo             | Restrições                      | Valor default    |
|----------------------|------------------|---------------------------------|------------------|
| ID do Candidato       | Caixa de Texto   | Obrigatório                     | -                |
| Data da Entrevista             | Data            |  Formato dd-mm-aaaa                    | -                |
| Horário da Entrevista   | Horário             | Formato hh:mm              | Data atual       |
| Local da Entrevista   | Caixa de texto             | Máximo de 100 caracteres, obrigatório              | -       |
| Observações   | Área de texto             | Opcional, máximo de 200 caracteres              | -       |

| Comandos             | Destino           | Tipo      |
|----------------------|-------------------|-----------|
| Agendar entrevista   | Tela de triagem | default   |
| Fechar    | Retornar ao formulário anterior | cancel   |

---



### Atividade 2: Confirmar Agendamento

| Campo                | Tipo             | Restrições                      | Valor default    |
|----------------------|------------------|---------------------------------|------------------|
| Mensagem      | Caixa de Texto   | Obrigatório                     | -                |

| Comandos             | Destino           | Tipo      |
|----------------------|-------------------|-----------|
| Confirmar  | Fim do Processo   | default   |
| Recusar  | Fim do Processo   | default   |


