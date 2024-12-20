const titulo = document.querySelector("#titulo");
const justificativa = document.querySelector("#justificativa");
const quantidade = document.querySelector("#quantidade");
const categoria = document.querySelector("#categoria");


document.getElementById("botao-enviar-solicitacao").addEventListener("click", () => {
    enviarSolicitacao("http://localhost:8080/gerente/solicitar-vaga");
});

async function enviarSolicitacao(url) {

    const token = localStorage.getItem('authToken'); // Obtém o token JWT armazenado
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    if (!titulo.value || !categoria.value || !justificativa.value || !quantidade.value) {
        alert("Todos os campos são obrigatórios.");
        return;
    }

    let categoriaCode;
    switch (categoria.value) {
        case 'financeiro':
            categoriaCode = 0;
            break;
        case 'desenvolvimento':
            categoriaCode = 1;
            break;
        case 'administrativo':
            categoriaCode = 2;
            break;
        default:
            alert("Categoria inválida");
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
                titulo: titulo.value,
                categoriaVaga: categoriaCode,
                justificativa: justificativa.value,
                qntd_vagas: parseInt(quantidade.value),
            })
        });

        if (response.ok) {
            const data = await response.json();
            alert("Vaga solicitada com sucesso! ID: " + data.id);
            loadVagasSolicitadas();
        } else {
            const errorData = await response.json();
            alert(`Erro: ${errorData.message || 'Erro ao solicitar a vaga.'}`);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Você não possui permissão para solicitar uma vaga.');
    }
}

async function loadVagasSolicitadas() {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/vagas_solicitadas', {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.ok) {
            const data = await response.json();
            const container = document.getElementById("container-vagas-solicitadas");
            container.innerHTML = "";

            const tituloHtml = `<h1 class="container-titulo" id="titulo-margin">Vagas Solicitadas</h1>`;
            container.innerHTML = tituloHtml;

            data.forEach(vaga => {
                const div = document.createElement("div");
                div.classList.add("vaga-solicitada");
                div.innerHTML = `
                <h3 class="vaga-titulo">${vaga.titulo} - Id ${vaga.id}</h3>
                <p class="vaga-justificativa"><strong>Justificativa:</strong> ${vaga.justificativa}</p>
                <p class="vaga-quantidade"><strong>Quantidade de Vagas:</strong> ${vaga.qntd_vagas}</p>
                <p class="vaga-categoria"><strong>Categoria:</strong> ${vaga.categoriaVaga}</p>
                <p class="vaga-gerente"><strong>Gerente:</strong> ${vaga.gerente.name}</p>
                `;
                container.appendChild(div);
            });
        } else {
            alert("Erro ao carregar as vagas solicitadas.");
        }
    } catch (error) {
        console.error('Erro ao carregar as vagas solicitadas:', error);
        alert('Erro ao se conectar com o servidor.');
    }
}




window.onload = loadVagasSolicitadas;
