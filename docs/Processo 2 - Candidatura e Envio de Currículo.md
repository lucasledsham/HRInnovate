### 3.3.2 Processo 2 – Candidatura


 Esse processo visa simplificar a candidatura das pessoas para vagas de emprego. O sistema automatiza a busca por vagas, com possibilidades de filtrar o tipo de vaga desejada, salários. O envio de currículo deve ser no formato PDF e após, o candidato recebe uma confirmação de candidatura em seu email, informado no seu cadastro.

![Processo2 drawio](https://github.com/user-attachments/assets/5784d6aa-d081-4310-8f54-afb1c0c72d2d)



**Detalhamento das Atividades**

**Visualizar vagas:**
O candidato busca uma vaga de emprego na plataforma. O sistema possibilita filtrar as vagas para uma procura mais especifica com o desejo do usuário.

**Analisar requisitos da vaga:**
Após encontrar uma vaga de interesse, o candidato acessa os detalhes da vaga. Ele pode visualizar a descrição da vaga, os requisitos exigidos e a faixa salarial. Com essas informações, o candidato decide se vai se candidatar.

**Enviar Currículo:**
O candidato preenche suas informações pessoais, como nome completo, e-mail, telefone, data de nascimento e anexa o currículo no formato exigido (PDF). Caso queira, também pode incluir uma carta de apresentação opcional. Ao final, ele confirma o envio do currículo.


## Atividade 1: Buscar Vaga

| Campo           | Tipo           | Restrições                   | Valor Default      |
|-----------------|----------------|------------------------------|--------------------|
| Pesquisar vagas   | Caixa de Texto  |        | ---                |

| Comando        | Destino              | Tipo     |
|----------------|----------------------|----------|
| Limpar filtros | Exibe todas as vagas     | default  |


---

## Atividade 2: Analisar Requisitos da vaga

| Campo           | Tipo           | Restrições                        | Valor Default |
|-----------------|----------------|-----------------------------------|---------------|
| Tiulo | Área de Texto  | Texto descritivo sem limite de linhas | Vazio     |
| Descricao         | Texto          | --------  | Vazio     |


| Comando        | Destino              | Tipo     |
|----------------|----------------------|----------|
| Ver detalhes | Detalhes da vaga   | default  |


---

## Atividade 3: Enviar Currículo

| Campo               | Tipo           | Restrições                               | Valor Default |
|---------------------|----------------|------------------------------------------|---------------|
| Nome Completo       | Caixa de Texto  | Mínimo de 2 caracteres                   | ---           |
| E-mail              | Caixa de Texto  | Formato de e-mail                        | ---           |
| Telefone            | Caixa de Texto  | Somente números (formato DDD+Número)     | ---           |
| Currículo           | Arquivo         | Aceitar apenas PDF                       | ---           |
| Carta de Apresentação | Área de Texto  | Opcional                                 | ---           |



| Comando   | Destino              | Tipo     |
|-----------|----------------------|----------|
| Enviar  | Tela de vagas   | submit   |
| X  | Tela de vagas   | cancel   |


---



