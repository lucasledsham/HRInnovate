
const nome = document.querySelector("#nome");
const email = document.querySelector("#email");
const senha = document.querySelector("#senha");

document.getElementById('cadastroGerente').addEventListener('click', () => {
    enviarCadastro("http://localhost:8080/admin/cadastrarGerente");
});

document.getElementById('cadastroRh').addEventListener('click', () => {
    enviarCadastro("http://localhost:8080/admin/cadastrarRh");
});

async function enviarCadastro(url) {
    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    // Verifica se os campos estão preenchidos
    if (!nome.value || !email.value || !senha.value) {
        alert("Todos os campos são obrigatórios.");
        return;
    }

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}` // Adiciona o token JWT no cabeçalho
            },
            body: JSON.stringify({
                name: nome.value,
                email: email.value,
                password: senha.value
            })
        });

        if (response.ok) {
            try {
                const data = await response.json();
                alert(`Usuário cadastrado com sucesso!`);
            } catch (jsonError) {
                console.error('Erro ao interpretar a resposta JSON:', jsonError);
                alert('Erro ! Tente novamente');
            }
        } else {
            try {
                const errorData = await response.json();
                alert(`Erro: ${errorData.message || 'Erro ao cadastrar o usuário.'}`);
            } catch (jsonError) {
                console.error('Erro ao interpretar a resposta de erro JSON:', jsonError);
                alert('Erro ao processar a resposta de erro.');
            }
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao se conectar com o servidor.');
    }
}

const form = document.getElementById('indicadorForm');
const buttonUser = document.getElementById('gerarIndicadorUser');
const buttonVagas = document.getElementById('gerarIndicadorVagas');

form.addEventListener('submit', function (event) {
    event.preventDefault();

    const dataInicio = document.getElementById('dataInicio').value;
    const dataFim = document.getElementById('dataFim').value;

    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado

    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    // Fazer a requisição POST para gerar indicador de usuário
    fetch('http://127.0.0.1:8080/indicadores/gerar_porcentagem_user', {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ dataInicio: dataInicio, dataFim: dataFim })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Indicador gerado:', data);
        const resultadoDiv = document.getElementById('resultado');

        // Exibe a porcentagem retornada
        if (data.porcentagem) {
            resultadoDiv.innerHTML = `<p>Porcentagem gerada: ${data.porcentagem}%</p>`;
        } else {
            resultadoDiv.innerHTML = `<p>Erro ao gerar o indicador.</p>`;
        }
    })
    .catch(error => {
        console.error('Erro ao gerar indicador:', error);
    });
});

// Evento para o botão de "Gerar Indicador - Vagas"
buttonVagas.addEventListener('click', function (event) {
    event.preventDefault();

    const dataInicio = document.getElementById('dataInicio').value;
    const dataFim = document.getElementById('dataFim').value;


    const startDate = new Date(dataInicio).toISOString(); // Para garantir o formato correto
    const endDate = new Date(dataFim).toISOString();

    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado

    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    // Fazer a requisição GET para obter indicadores de vagas por categoria
    fetch(`http://127.0.0.1:8080/vagas_criadas/indice/vagas-por-categoria?startDate=${startDate}&endDate=${endDate}`, {
        method: 'GET',
        headers: {
            "Accept": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Indicador de Vagas gerado:', data);
        const resultadoVagasDiv = document.getElementById('resultadoVagas');

        // Exibe os dados retornados de vagas por categoria
        if (data) {
            resultadoVagasDiv.innerHTML = `
                <p>Total de Vagas: ${data.totalVagas}</p>
                <p>Desenvolvimento: ${data.percentualDesenvolvimento}%</p>
                <p>Administrativo: ${data.percentualAdministrativo}%</p>
                <p>Financeiro: ${data.percentualFinanceiro}%</p>
            `;
        } else {
            resultadoVagasDiv.innerHTML = `<p>Erro ao gerar indicador de vagas.</p>`;
        }
    })
    .catch(error => {
        console.error('Erro ao gerar indicador de vagas:', error);
    });
});
//------------------------------------------------------------------------------------------------------------
document.getElementById("gerarIndicador").addEventListener("click", async function () {
    const token = localStorage.getItem('authToken'); 
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }
    try {
        
        const response = await fetch("http://127.0.0.1:8080/candidato/indice-aprovacao", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`, 
                "Content-Type": "application/json"
            },
        });

        // Verifica se a resposta foi bem-sucedida
        if (!response.ok) {
            throw new Error("Erro ao obter o índice de aprovação.");
        }

        // Converte a resposta em JSON
        const indice = await response.json();

        // Atualiza o conteúdo da página com o índice
        const resultadoDiv = document.getElementById("result");
        resultadoDiv.innerHTML = `
            <p><strong>Índice de Aprovação:</strong> ${indice.toFixed(2)}%</p>
        `;
    } catch (error) {
        // Trata erros exibindo uma mensagem
        alert("Erro: " + error.message);
    }
});

document.getElementById('logout').addEventListener('click', function (event) {
    event.preventDefault(); 
    
    localStorage.removeItem('authToken');
    
    window.location.href = 'index.html';
});
