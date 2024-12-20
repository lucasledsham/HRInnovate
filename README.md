# HR Innovate

Este trabalho apresenta o desenvolvimento de um sistema de software destinado a
automatizar processos críticos em um departamento de Recursos Humanos, focando em
uma área em essencial: Recrutamento e Seleção

## Integrantes

* Caio Victor Kodato Teixeira
* Francielle Moura Limonge
* Lucas Nunes Leal Ledsham
* Marina Ferreira Sansão Cabalzar
* Pedro Queiroz Rolim
* Santhiago Takaesu Sampaio
* Victor Guimarães de Alvarenga Cunha

## Professor

* Aline Norberta de Brito
* Cleia Marcia Gomes Amaral
* Cristiano de Macêdo Neto

## Instruções de utilização

### Pré-requisitos

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado em sua máquina.
- [Spring Boot](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack) instalado em sua IDE.
- [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) instalado em sua IDE.

### Passo a passo para configuração e execução do projeto

### 1.1 Baixar e instalar o Java 17

Se você ainda não tem o Java 17 instalado, siga as instruções abaixo:

1. Acesse o site oficial do Java

- Abra seu navegador e vá para [https://www.oracle.com/java/technologies/javase-jdk17-downloads.html](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html). Este é um site oficial da Oracle para baixar versões do OpenJDK, incluindo o Java 17.

2. Escolha a versão do Java

- Na página inicial, selecione a versão **JDK 17**.

3. Baixe o instalador

- Realize o download do instalador adequado para seu sistema operacional (Windows, macOS, ou Linux) começará automaticamente, ou você poderá selecionar manualmente o instalador apropriado.

4. Execute o instalador

- Após o download, localize o arquivo de instalação em sua pasta de downloads e clique duas vezes para executá-lo.

5. Siga as instruções do instalador

- **Windows**:
  - Execute o arquivo MSI baixado.
  - Clique em "Next" na tela de boas-vindas.
  - Aceite os termos de licença e clique em "Next".
  - Escolha o diretório de instalação (ou deixe o padrão) e clique em "Next".
  - Clique em "Install" para iniciar a instalação.
  - Após a conclusão, clique em "Finish".
  
- **macOS**:
  - Abra o arquivo `.dmg` baixado e siga as instruções para arrastar o JDK para a pasta "Applications".

- **Linux**:
  - Para distribuições baseadas em Debian (como Ubuntu), você pode usar os seguintes comandos no terminal:
    ```sh
    sudo apt update
    sudo apt install openjdk-17-jdk
    ```
  - Para outras distribuições, consulte a documentação do OpenJDK ou da Oracle para instruções específicas.

6. Verifique a instalação

- Após a instalação, abra o terminal (ou Prompt de Comando no Windows) e digite o seguinte comando para verificar se o Java foi instalado corretamente:
  ```sh
  java -version
  ```

### 1.2 Baixar a extensão Spring Boot Extension Pack

1. Abra a sua IDE

2. Navegue até a seção de Extensões

3. Procure por Spring Boot Extension Pack

4. Clique em baixar

### 1.3 Baixar a extensão Live Server

1. Abra a sua IDE

2. Navegue até a seção de Extensões

3. Procure por Live Server

4. Clique em baixar

### 2. Clonar o repositório

Abra o terminal e execute o seguinte comando para clonar o repositório:

```sh
git clone https://github.com/lucasledsham/HRInnovate.git

```

### 3. Abrir o projeto

Abra o projeto na IDE de sua preferência.

### 4. Navegar até o arquivo AppRhApplication.java

Navegue utilizando os seguintes comandos: 

```sh
cd src
```

```sh
cd main
```

```sh
cd java
```

```sh
cd com
```

```sh
cd tis2
```

```sh
cd AppRh
```

### 5. Execute o código de AppRhApplication.java

Dentro de AppRhApplication.java clique no botão 'Run' da sua IDE.

### 6. Navegar até o o arquivo index.html

```sh
cd src
```

```sh
cd front
```

### 7. Abrir o index no navegador

Com a extensão Live Server, ou outra de sua preferência com a mesma finalidade, abra o index.html sem seu navegador, clicando no botão 'Go Live'.

### 8. Realize o login como admin

Após aberto o index.html, clique no botão de Login da página, canto superior direito da tela. Insira os seguintes dados:

```sh
Email: admin@example.com
Senha: admin123
```

### 9. Desfrute do Sistema

Seguindo todas as instruções acima, você estará apto a utilizar o sistema. Crie contas como Candidato, Gestor e Profissional de RH e aproveite a aplicação!

## Histórico de versões

* **0.1.1**
    * CHANGE: Entrega das seções "Análise da situação atual" e "Descrição geral da proposta de solução" no relatório.

* **0.1.0**
    * CHANGE: Atualização das seções "Análise da situação atual" e "Descrição geral da proposta de solução" no relatório.

* **0.2.1**
    * CHANGE: Correção nas seções "Análise da situação atual" e "Descrição geral da proposta de solução" no relatório.
    * NEW: Identificação e modelagem dos processos no relatório.

* **0.2.0**
    * CHANGE: Entrega das seções "Análise da situação atual" e "Descrição geral da proposta de solução" no relatório, versão corrigida.
    * NEW: Identificação e modelagem dos processos.

* **0.3.0**
    * NEW: Entrega da seção "Modelagem do Processo de Negócio" no relatório.

* **0.4.0**
    * NEW: Entrega das seções "Detalhamentos das Atividades" e "Modelo de Dados" (versão preliminar) no relatório.

* **0.5.1**
    * CHANGE: Correção nas seções "Detalhamentos das Atividades" e "Modelo de Dados" no relatório.
    * NEW: Entrega da seção "Tecnologias" no relatório.

* **0.5.0**
    * NEW: Entrega das seções "Detalhamentos das Atividades" e "Modelo de Dados" (versão preliminar) no relatório.
    * NEW: Entrega da seção "Tecnologias" no relatório.

* **0.6.0**
    * NEW: Implementação da solução - 1ª parte.
    * CHANGE: Atualização das seções "Modelagem dos processos - processo 1" e "Modelo de Dados" no relatório.
    * NEW: Implementação do processo 1 (front-end).

* **0.7.0**
    * NEW: Implementação do processo 1.
    * CHANGE: Atualização das seções "Modelagem dos processos - processo 1" e "Modelo de Dados" no relatório.

* **0.8.0**
    * NEW: Implementação do processo 2 (front-end).
    * CHANGE: Atualização das seções "Modelagem dos processos - Processo 2" e "Modelo de Dados" no relatório.

* **0.9.0**
    * NEW: Implementação dos processos 1 e 2.
    * CHANGE: Atualização das seções "Modelagem dos processos - Processos 1 e 2" e "Modelo de Dados" no relatório.

* **1.0.0**
    * NEW: Implementação do processo 3.
    * CHANGE: Atualização das seções "Modelagem dos processos - Processo 3", "Modelo de Dados" e "Indicadores de desempenho" no relatório.

* **1.1.0**
    * NEW: Implementação dos processos 3 e 4.
    * CHANGE: Atualização das seções "Modelagem dos processos - Processos 3 e 4", "Modelo de Dados" e "Indicadores de desempenho" no relatório.

* **1.2.0**
    * NEW: Implementação dos indicadores de desempenho e correção dos processos pendentes.
    * CHANGE: Atualização das seções "Modelagem dos processos" e "Modelo de Dados" no relatório.
