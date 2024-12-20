# HR Innovate


**Caio Victor Kodato Teixeira, caiovictorkodatot@gmail.com**

**Francielle Moura Limonge, fraanlimonge@gmail.com**

**Lucas Nunes Leal Ledsham, lucasnunesleal@gmail.com**

**Marina Ferreira Sansão Cabalzar, marinacabalzar@gmail.com**

**Pedro Queiroz Rolim, pedroq.rolim@gmail.com**

**Santhiago Takaesu Sampaio, santhiago.takaesu2021@gmail.com**

**Victor Guimarães de Alvarenga Cunha, victorgacunha@gmail.com**


---

Professores:

**Aline Norberta de Brito**

**Cristiano de Macêdo Neto**

**Cleia Marcia Gomes Amaral**

---

_Curso de Engenharia de Software_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---


Este trabalho propõe o desenvolvimento de um sistema de software para automatizar o processo de Recrutamento e Seleção no departamento de Recursos Humanos. O objetivo é aumentar a eficiência operacional, reduzir erros e melhorar a experiência tanto dos candidatos quanto dos profissionais de RH, integrando e otimizando todas as etapas do recrutamento, desde a publicação de vagas até a contratação. O sistema atende dois perfis principais de usuários: profissionais de RH e candidatos, cada um com necessidades específicas que serão tratadas de forma intuitiva e eficaz.
---


## 1. Introdução

Este trabalho apresenta o desenvolvimento de um sistema de software destinado a
automatizar o processo de Recrutamento e Seleção em um departamento de Recursos
Humanos, visando otimizar a eficiência e precisão dessa área crítica para as organizações.

### 1.1 Contextualização

No cenário corporativo atual, a eficácia no processo de Recrutamento e Seleção é
fundamental para garantir que as empresas possam atrair e reter talentos qualificados,
mantendo-se competitivas no mercado. O departamento de Recursos Humanos (RH)
desempenha um papel essencial na gestão do capital humano, com o processo de
Recrutamento e Seleção sendo um dos principais fatores que impactam diretamente a
qualidade das contratações e, consequentemente, o desempenho organizacional. De
acordo com um relatório da Deloitte (2022), 70% das empresas destacam a digitalização do
processo de recrutamento como uma prioridade estratégica para melhorar a eficácia das
contratações e reduzir o tempo de preenchimento de vagas.<sup>1.1</sup> No entanto, muitas
organizações ainda enfrentam desafios significativos na automação desse processo, o que
resulta em ineficiências e decisões de contratação subótimas. Este trabalho visa abordar
essa lacuna, propondo um sistema que automatize e integre as etapas de Recrutamento e
Seleção, desde a abertura de vagas até a contratação final.

### 1.2 Problema

Apesar da disponibilidade de tecnologias avançadas para a automação de processos, muitas empresas ainda dependem de métodos manuais e descentralizados para realizar o Recrutamento e Seleção, o que pode levar a atrasos, erros e perda de talentos potenciais. A falta de integração entre as diferentes etapas do processo, como triagem de currículos, agendamento de entrevistas e comunicação com candidatos, resulta em dificuldades na gestão eficiente dessas atividades, especialmente em empresas que lidam com um grande volume de contratações. Segundo Stone e Dulebohn (2013), a automação dos processos de gestão de recursos humanos, como o Recrutamento e Seleção, pode reduzir erros, otimizar a eficiência e melhorar a qualidade das contratações.<sup>1.2</sup> Este trabalho propõe o desenvolvimento de um sistema de software que automatize o processo de Recrutamento e Seleção, reduzindo ineficiências e melhorando a qualidade das contratações.

### 1.3 Objetivo geral

O objetivo geral deste trabalho é desenvolver um sistema de software para automação do
processo de Recrutamento e Seleção no departamento de Recursos Humanos, visando
otimizar a gestão de vagas, a triagem de candidatos e o agendamento de entrevistas.

#### 1.3.1 Objetivos específicos

Desenvolver um módulo de Recrutamento e Seleção que permita a gestão
automatizada de vagas, facilitando a publicação e divulgação de oportunidades de
emprego;

Criar uma ferramenta de triagem automatizada que utilize critérios predefinidos para
selecionar os candidatos mais qualificados, agilizando o processo de avaliação;

Implementar um sistema de agendamento de entrevistas que automatize a
comunicação com os candidatos e o planejamento das entrevistas, garantindo maior
eficiência na gestão do tempo;

Automatizar o processo de seleção de candidatos, permitindo a integração das fases
de triagem, entrevistas e comunicação com os candidatos em um único fluxo de
trabalho.

### 1.4 Justificativas

A automação do processo de Recrutamento e Seleção se justifica pela necessidade de as
empresas atraírem e contratarem talentos de maneira mais rápida e eficaz, reduzindo o
tempo de resposta e os custos associados a processos manuais e ineficientes. Ao
automatizar essas atividades, o departamento de Recursos Humanos pode se concentrar
em aspectos mais estratégicos do recrutamento, como a criação de uma marca
empregadora atraente e a melhoria da experiência dos candidatos. Além disso, o sistema
proposto contribuirá para a padronização dos processos de seleção, assegurando maior
conformidade com políticas internas e externas, e permitindo decisões de contratação mais
assertivas e baseadas em dados.

## 2. Participantes do processo

Os usuários do sistema incluem:

1. Gestor da Empresa:
O gestor é o responsável por solicitar a abertura de novas vagas ao RH, fornecendo os requisitos necessários para cada posição. Após a criação da vaga pelo RH, o gestor tem a responsabilidade exclusiva de aprovar a publicação da vaga no sistema.

2. RH da Empresa:
O setor de Recursos Humanos é responsável por gerenciar todo o processo de recrutamento e seleção. Suas principais atividades incluem a publicação das vagas, triagem dos currículos recebidos, agendamento de entrevistas e acompanhamento do processo seletivo até a contratação dos candidatos mais adequados. O RH também atua como intermediário entre os candidatos e os gestores da empresa.

3. Usuários (Candidatos):
Os candidatos são os profissionais interessados nas vagas publicadas pela empresa. Eles acessam o sistema, se cadastram, enviam seus currículos e acompanham o andamento da seleção. Após a candidatura, passam pela triagem realizada pelo RH, e, caso selecionados, participam das entrevistas e demais etapas do processo seletivo.

## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

● Os sistemas atuais de Recrutamento e Seleção enfrentam desafios significativos, mesmo com a disponibilidade de tecnologias avançadas.<sup>1.3</sup>

● Muitas empresas ainda dependem de métodos manuais e descentralizados, o que pode causar atrasos, erros e a perda de talentos potenciais.<sup>1.4</sup>

● A falta de integração entre as diferentes etapas do processo, como triagem de currículos, agendamento de entrevistas e comunicação com os candidatos, resulta em ineficiências.<sup>1.5</sup>

● Esses problemas são particularmente agravados em empresas que precisam lidar com um grande volume de contratações. Dessa forma, a automação é vista como uma solução promissora para otimizar essas atividades.<sup>1.6</sup> 

● O recrutamento é o setor mais significativo em Recursos Humanos, as empresas investem bilhões anualmente em recrutamento. Esse investimento reflete a crescente importância atribuída às novas tecnologias direcionadas a essa área.<sup>1.7</sup>

### 3.2. Descrição geral da proposta de solução

● Embora a automação ofereça várias vantagens, a proposta também enfrenta alguns limites:
 
1) **Complexidade na Implementação:** Integrar o sistema proposto com as plataformas e processos já existentes na empresa pode exigir tempo e recursos significativos, o que pode dificultar a adoção inicial. 

2) **Resistência à Mudança:** A transição de processos manuais para automatizados pode encontrar resistência entre os funcionários, especialmente aqueles acostumados aos métodos tradicionais. 

3) **Limitações Tecnológicas:** A eficácia do sistema depende da qualidade dos algoritmos de triagem e da capacidade do sistema de se adaptar às necessidades específicas de cada empresa. Isso pode limitar sua aplicabilidade em contextos em que a personalização é crucial.
 
● A proposta alinha-se diretamente com as estratégias e objetivos de negócios das empresas que buscam: 

1) **Eficiência Operacional:** Ao automatizar o processo de Recrutamento e Seleção, a empresa pode reduzir o tempo de preenchimento de vagas e minimizar erros, resultando em uma operação mais ágil e eficiente. 

2) **Melhoria na Qualidade das Contratações:** Com uma triagem mais precisa e automatizada, o sistema pode ajudar a identificar os candidatos mais qualificados, o que melhora a qualidade das contratações e, consequentemente, o desempenho organizacional. 

3) **Conformidade e Padronização:** O sistema proposto também ajuda a garantir que o processo seletivo siga políticas internas e externas, promovendo uma padronização que pode ser crucial para a conformidade regulatória. 

● Embora a proposta ofereça uma solução robusta para muitos problemas atuais, existem oportunidades para melhorias: 

1) **Aprimoramento de Algoritmos:** Melhorar os algoritmos de triagem para considerar não apenas as qualificações técnicas, mas também fatores culturais e comportamentais, pode aumentar a precisão na seleção de candidatos. 

2) **Integração com Ferramentas Externas:** Facilitar a integração do sistema com outras ferramentas de RH, como sistemas de gestão de desempenho e plataformas de aprendizagem, pode ampliar seu impacto. 

3) **Feedback e Adaptação Contínua:** Implementar mecanismos para coletar feedback dos usuários e adaptar o sistema às necessidades em constante mudança da empresa pode garantir que o sistema continue a oferecer valor a longo prazo. 

4) **Melhoria na Experiência do Usuário:** Investir na interface do usuário para garantir que o sistema seja intuitivo e fácil de usar, minimizando a curva de aprendizado e aumentando a adesão dos funcionários ao novo sistema. 
### 3.3. Modelagem dos processos

[Processo 1 - Publicação de Vagas Disponíveis](Processo%201%20-%20Publicação%20de%20Vagas%20Disponíveis.md "Detalhamento do Processo 1.")

[Processo 2 - Candidatura e Envio de Currículo](Processo%202%20-%20Candidatura%20e%20Envio%20de%20Curr%C3%ADculo.md "Detalhamento do Processo 2.")

[Processo 3 - Triagem e Seleção de Candidatos](Processo%203%20-%20Triagem%20e%20Sele%C3%A7%C3%A3o%20de%20Candidatos.md "Detalhamento do Processo 3.")

[Processo 4 – Agendamento de Entrevista e Processo Seletivo](Processo%204%20%E2%80%93%20Agendamento%20de%20Entrevista%20e%20Processo%20Seletivo.md "Detalhamento do Processo 4.")

[Processo 5 – Cadastro de Usuario](Processo%205%20-%20Cadastro%20de%20Usuario.md "Detalhamento do Processo 5.")

## 4. Projeto da solução

_O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias._

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

_O documento a seguir apresenta os indicadores de desempenho dos processos._

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

_A sessão a seguir apresenta a descrição do produto de software desenvolvido._ 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão

- **Caio Victor Kodato Teixeira**

Foi um trabalho que demandou bastante empenho e constância, porém trouxe muitos benefícios para mim. Senti um aprendizado e um prazer enorme por estar desenvolvendo e fazendo com que funcionasse e ficasse de acordo. Quanto ao projeto, chegamos ao resultado esperado, com os nossos 5 processos sendo implementados corretamente e com muita qualidade. 

- **Francielle Moura Limonge**

Acredito que o sistema tenha cumprido com o que foi prometido, e proporcionou uma solução eficaz para os desafios enfrentados no processo de recrutamento e seleção. O software foi projetado para otimizar etapas cruciais, desde a publicação de vagas até a contratação de candidatos, garantindo praticidade e eficiência tanto para as empresas quanto para os profissionais em busca de oportunidades. 

Além disso, o sistema se destacou por sua interface intuitiva e de fácil utilização, permitindo que tanto recrutadores quanto candidatos pudessem navegar e realizar suas atividades sem dificuldades técnicas. Apesar de sua simplicidade, a solução foi projetada de maneira robusta, oferecendo funcionalidades completas e bem integradas, capazes de atender às principais demandas do setor de RH.

- **Lucas Nunes Leal Ledsham**

O sistema desenvolvido atendeu plenamente às necessidades e desafios propostos, oferecendo uma solução prática e eficaz para o processo de recrutamento e seleção. Desde a criação dos logins até a efetivação da contratação, o software mostrou-se intuitivo e de fácil utilização, o que proporcionou uma experiência positiva para todos os usuários, tanto recrutadores quanto candidatos.

A interface, simples mas eficiente, garantiu uma navegação ágil e sem complicações, permitindo que as etapas do processo fossem cumpridas de maneira eficiente. Além disso, a integração entre as funcionalidades foi um destaque, contribuindo para a fluidez e a agilidade das operações realizadas.

- **Marina Ferreira Sansão Cabalzar**

Posso afirmar que o sistema desenvolvido atendeu plenamente aos requisitos e objetivos propostos, desde a criação de logins até o processo de contratação de candidatos, demonstrando ser uma ferramenta prática e de fácil utilização.

- **Pedro Queiroz Rolim**

O sistema HR Innovate atingiu com sucesso os objetivos estabelecidos, oferecendo uma solução prática e eficiente para os desafios do processo de recrutamento e seleção. Desde a publicação de vagas até a contratação, o sistema demonstrou ser uma ferramenta robusta, com funcionalidades bem integradas e uma interface intuitiva, que facilitaram a navegação tanto para os candidatos quanto para os profissionais de RH.

Além disso, o projeto destacou-se por otimizar etapas críticas do processo seletivo, como a triagem de currículos e o agendamento de entrevistas, reduzindo erros e aumentando a eficiência. Com isso, o HR Innovate não apenas cumpriu o que foi prometido, mas também abriu portas para futuras melhorias e inovações no setor de Recursos Humanos.

- **Santhiago Takaesu Sampaio**

O desenvolvimento do sistema HR Innovate alcançou resultados significativos ao abordar os desafios atuais enfrentados por empresas. A solução desenvolvida promoveu melhorias na eficiência operacional, reduzindo tempo de preenchimento de vagas e minimizano erros. Além disso, aumentou a qualidade das contratações por meio de triagens mais precisas, contribuiu para a padronização dos processos e melhorou a experiência tanto de candidatos quanto dos profissionais de RH.
  
- **Victor Guimarães de Alvarenga Cunha**

Concluo que o nosso sistema cumpriu todos os requisitos e a proposta que fizemos para ele, desde a geração de um login até a contratação de um candidato, sendo muito prático e fácil de utilizar.

---
Novas linhas de estudo:
    
  - Aprimoramento de Algoritmos: desenvolvimento de algoritmos de triagem mais avançados que avaliem não apenas qualificações técnicas, mas também fatores comportamentais e culturais.
    
  - Integração com Outras Ferramentas de RH: explorar integrações com sistemas de gestão de desempenho, plataformas de treinamento e desenvolvimento, e ferramentas de análise preditiva para criar um ecossistema completo de RH.
    
  - Análise de Dados e Inteligência Artificial: utilizar análise preditiva e aprendizado de máquina para identificar tendências em contratações e prever o sucesso de candidatos em longo prazo
    
  - Feedback Contínuo: implementar sistemas para coletar feedback regular de usuários, permitindo ajustes contínuos para atender às necessidades em evolução das empresas.

  - Passar a disponibilizar entrevistas onlines

# APÊNDICES



## Apêndice A - Código fonte

[Código do front-end](../src/front) -- repositório do código do front-end

[Código do back-end](../src/main)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/HR%20INNOVATE.pdf)


[Vídeo da apresentação final](video)

https://github.com/user-attachments/assets/10e60c9f-e2bb-4acc-9875-c09e72ee6d39










