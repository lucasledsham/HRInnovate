### 3.3.1 Processo 1 – PUBLICAÇÃO DE VAGA

Esse processo visa simplificar a publicação de vagas de emprego. O sistema inicia com a solicitação de outro setor sobre a necessidade da vaga de emprego, e o rh irá realizar todas as etapas necessárias para publicar e atrair os melhores profissionais para vaga, tudo isso com um sistema bem automatizado e organizado.

![Processo1](https://github.com/user-attachments/assets/28d38139-78a5-49f5-ba0c-9c4b333a1fee)



# Detalhamento das Atividades

Solicitar vaga: Um gerente percebe a necessidade de preencher uma nova posição e inicia o processo ao solicitar a criação da vaga junto ao setor de RH. Este processo envolve a descrição básica da vaga, requisitos e justificativa.

Criar vaga: O profissional de RH recebe a solicitação de vaga e é responsável por criar formalmente a vaga, inserindo as informações detalhadas no sistema de recrutamento ou na plataforma interna da empresa. Esta tarefa inclui definir os requisitos técnicos e comportamentais, cargo, faixa salarial, e as responsabilidades da posição.

Aprovar vaga: Gerente analisa se a os dados da vaga criada estão válidos e adequados e escolhe se vai publicar ou não a vaga.


# Atividade 1: Solicitar Vaga

| **Campo**             | **Tipo**           | **Restrições**                | **Valor default**    |
|-----------------------|--------------------|-------------------------------|----------------------|
| Título da vaga         | Caixa de Texto     | Máximo de 100 caracteres       |                      |
| Justificativa          | Área de Texto      | Mínimo de 50 caracteres        |                      |
| Quantidade de vagas    | Número             | Valor numérico, mínimo 1       | 1                    |
| Categoria              | Seleção única      | Opções: Administrativo, RH, etc|                      |

### Comandos:
| **Comandos**         | **Destino**                   | **Tipo**          |
|---------------------|-------------------------------|-------------------|
| Enviar               | Enviar ao RH                   | default           |
| Cancelar             | Retornar ao início             | cancel            |

---

# Atividade 2: Criar Vaga

| **Campo**           | **Tipo**           | **Restrições**                | **Valor default**    |
|---------------------|--------------------|-------------------------------|----------------------|
| Id da vaga         | Número             | Valor numérico, Id existente  | ---         |
| Descrição da vaga    | Área de Texto      | Mínimo de 50 caracteres        |                      |
| Requisitos   | Área de Texto      | -----      |                      |
| **Salário**          | Número             | Valor numérico, mínimo **R$ 1.400**  | **R$ 1.400**          |


### Comandos:
| **Comandos**         | **Destino**                   | **Tipo**          |
|---------------------|-------------------------------|-------------------|
| Enviar para aprovação               | Enviar para gerente aprovar      | default           |
| Cancelar             | Retornar ao início             | cancel            |

---

---
# Atividade 4: Aprovar vaga


### Comandos:
| **Comandos**         | **Destino**                   | **Tipo**          |
|---------------------|-------------------------------|-------------------|
| Aprovar              | Publicar no sistema            | default           |
| Rejeitar             | Mudança de status        | cancel            |
| Deletar             | Delete vaga         | cancel            |

---


