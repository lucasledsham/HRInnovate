### 3.3.5 Processo 5 - Cadastro de Usuario.md
O usuário inicia o processo de cadastro acessando o sistema e preenchendo os dados necessários. O sistema valida as informações inseridas. Se as informações estiverem corretas, o cadastro é confirmado. Caso haja algum erro, o sistema exibe uma mensagem de erro ao usuário, que deve corrigir as informações antes de tentar novamente.

![bpmn5 drawio](https://github.com/user-attachments/assets/ac9db0a0-1d52-4b72-9812-f474ef6a6d96)

### Detalhamento das Atividades


Acessar o Sistema:
O processo começa com o usuário acessando o sistema para iniciar o cadastro.

Preencher Dados:
Após acessar o sistema, o usuário preenche o formulário de cadastro com as informações necessárias.

---

### Acessar o Sistema


| Comandos             | Destino           | Tipo      |
|----------------------|-------------------|-----------|
| Cadastre-se    | Pagina cadastro| default   |

---

### Preencher Dados
| Campo                | Tipo             | Restrições                      | Valor default    |
|----------------------|------------------|---------------------------------|------------------|
| Nome                 | Caixa de Texto   | Obrigatório                     | -                |
| E-mail               | Caixa de Texto   | Formato de e-mail válido         | -                |
| Senha                | Senha            | Mínimo 8 caracteres             | -                |
| Confirmação de Senha | Senha            | Deve coincidir com a Senha       | -                |

| Comandos             | Destino           | Tipo      |
|----------------------|-------------------|-----------|
| Cadastrar        | Usuario cadastrado | default   |
| Ja possui cadastro? Faça login        | Tela de login | default   |


