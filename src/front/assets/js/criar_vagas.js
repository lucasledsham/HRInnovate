// Seleciona os elementos do formulário
const vagaSolicitadaId = document.querySelector("#vaga-solicitada-id");
const descricaoVaga = document.querySelector("#descricao-vaga");
const requisitosVaga = document.querySelector("#requisitos-vaga");
const salarioVaga = document.querySelector("#salario-vaga");


document.getElementById("botao-enviar").addEventListener("click", () => {
    enviarCriacaoDeVaga("http://localhost:8080/rh/criar-vaga");
});

async function enviarCriacaoDeVaga(url) {
    
    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }
    
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
                vagaSolicitada: { id: parseInt(vagaSolicitadaId.value) },
                descricao: descricaoVaga.value,
                requisitos: requisitosVaga.value,
                salario: parseFloat(salarioVaga.value)
            })
        });

        if (response.ok) {
            const data = await response.json();
            alert("Vaga criada com sucesso! ID: " + data.id);
            // Opcional: Limpa o formulário após o envio
            document.getElementById("formulario-criar-vaga").reset();
            loadVagasCriadas(); // Atualiza a lista de vagas criadas, se necessário
        } else {
            const errorData = await response.json();
            alert(`Erro: ${errorData.message || 'Erro ao criar a vaga.'}`);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao se conectar com o servidor.');
    }
}



async function loadVagasCriadas() {
    try {
        const token = localStorage.getItem('authToken');  

        if (!token) {
            throw new Error('Você precisa estar autenticado para acessar as vagas.');
        }

        const response = await fetch("http://localhost:8080/vagas_criadas", {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,  
                'Content-Type': 'application/json'
            }
        });

        if (response.status === 403) {
            throw new Error("Acesso proibido. Verifique suas permissões.");
        }

        const data = await response.json();
        const container = document.getElementById("container-lista-vagas");
        container.innerHTML = "<h1 class='container-titulo'>Vagas Criadas</h1>";

        for (const vaga of data) {
            const div = document.createElement("div");
            div.classList.add("vaga-criada");

            // Verifica se o título da vaga está disponível
            let titulo = vaga.vagaSolicitada?.titulo;
            if (!titulo) {
                // Obtém o título dinamicamente pelo ID da vaga solicitada
                titulo = await obterTituloVagaSolicitada(vaga.vagaSolicitada.id) || 'Título não disponível';
            }

            div.innerHTML = `
                <h3 class="vaga-titulo">${titulo}</h3>
                <p class="vaga-descricao"><strong>Descrição:</strong> ${vaga.descricao}</p>
                <p class="vaga-requisitos"><strong>Requisitos:</strong> ${vaga.requisitos}</p>
                <p class="vaga-salario"><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
                <p class="vaga-status"><strong>Status:</strong> ${vaga.statusVaga}</p>
            `;

            // Exibe os botões de acordo com o status
            if (vaga.statusVaga === "Aguardando Aprovação") {
                div.innerHTML += `
                    <button onclick="aprovarVaga(${vaga.id})" class="botao-aprovar">Aprovar</button>
                    <button onclick="reprovarVaga(${vaga.id})" class="botao-reprovar">Reprovar</button>
                `;
            } else if (vaga.statusVaga === "Reprovado") {
                div.innerHTML += `
                    <button onclick="deletarVaga(${vaga.id})" class="botao-deletar">Deletar</button>
                `;
            }

            container.appendChild(div);
        }
    } catch (error) {
        console.error("Erro ao carregar as vagas criadas:", error);
    }
}


async function aprovarVaga(id) {
    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/gerente/${id}/aprovar`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json" // Opcional: para garantir que o servidor entenda a requisição
            }
        });
        
        if (response.ok) {
            alert("Vaga aprovada!");
            loadVagasCriadas();
        } else {
            alert("Você não possui permissão para aprovar essa vaga.");
        }
    } catch (error) {
        console.error("Erro:", error);
    }
}

async function reprovarVaga(id) {
    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/gerente/${id}/reprovar`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json" // Opcional: para garantir que o servidor entenda a requisição
            }
        });

        if (response.ok) {
            alert("Vaga reprovada!");
            loadVagasCriadas(); // Atualiza a lista de vagas para refletir as mudanças
        } else {
            alert("Você não possui permissão para reprovar essa vaga.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
}

// Função para deletar uma vaga
async function deletarVaga(id) {
    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    if (confirm("Tem certeza que deseja deletar esta vaga?")) {
        try {
            const response = await fetch(`http://localhost:8080/gerente/${id}`, {
                method: 'DELETE',
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json" // Opcional: para garantir que o servidor entenda a requisição
                }
            });

            if (response.ok) {
                loadVagasCriadas(); // Atualiza a lista de vagas após a exclusão
            } else {
                alert("Erro ao deletar a vaga.");
            }
        } catch (error) {
            console.error('Erro ao deletar a vaga:', error);
        }
    }
}

async function obterTituloVagaSolicitada(id) {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert("Token JWT não encontrado.");
        return null;
    }

    try {
        const response = await fetch(`http://localhost:8080/vagas_solicitadas/${id}`, {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.ok) {
            const vaga = await response.json();
            return vaga.titulo; // Retorna o título da vaga
        } else {
            console.error("Erro ao buscar o título da vaga:", await response.json());
            return null;
        }
    } catch (error) {
        console.error('Erro ao buscar o título da vaga:', error);
        return null;
    }
}

// Carrega as vagas criadas ao iniciar a página
window.onload = loadVagasCriadas;
