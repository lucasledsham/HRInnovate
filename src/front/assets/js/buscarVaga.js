document.addEventListener("DOMContentLoaded", function () {
    
    const userName = localStorage.getItem("userName");

    
    const userNameSpan = document.getElementById("userName");
    if (userName) {
        userNameSpan.textContent = userName;
    } else {
        userNameSpan.textContent = "Usuário não identificado";
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('authToken');
    if (token) {
        const userRole = parseJwt(token)?.role;

        if (userRole !== 'ROLE_GERENTE' && userRole !== 'PROFISSIONAL_RH') {
            // Caso o usuário não seja ADMINISTRADOR ou RH, esconda as opções de "Criar Vagas" e "Solicitar Vagas"
            const solicitarVagasLink = document.querySelector("a[href='./SolicitarVagas.html']");
            const criarVagasLink = document.querySelector("a[href='./CriarVagas.html']");
            
            if (solicitarVagasLink) {
                solicitarVagasLink.style.display = 'none';
            }
            if (criarVagasLink) {
                criarVagasLink.style.display = 'none';
            }
        }
    } else {
        // Caso o usuário não esteja logado, esconda as opções para os não logados
        const solicitarVagasLink = document.querySelector("a[href='./SolicitarVagas.html']");
        const criarVagasLink = document.querySelector("a[href='./CriarVagas.html']");
        
        if (solicitarVagasLink) {
            solicitarVagasLink.style.display = 'none';
        }
        if (criarVagasLink) {
            criarVagasLink.style.display = 'none';
        }
    }
});



async function loadProcessosSeletivos() {
    try {
        const response = await fetch('http://localhost:8080/processo_seletivo');
        const data = await response.json();
        const container = document.getElementById("jobList");
        container.innerHTML = "";

        const token = localStorage.getItem('authToken');
        const userRole = token ? parseJwt(token).role : null;

        data.forEach(processo => {
            const div = document.createElement("div");
            div.classList.add("processo");
            div.innerHTML = `
                <div class="job-item">
                    <h3>${processo.vaga.titulo}</h3>
                    <p>${processo.vaga.descricao}</p>
                    <button onclick="${userRole === 'PROFISSIONAL_RH' 
                        ? `redirectToGerenciar(${processo.id})` 
                        : `showJobDetails(${processo.id})`}">
                        ${userRole === 'PROFISSIONAL_RH' ? 'Gerenciar Processo' : 'Ver Detalhes'}
                    </button>
                </div>
            `;
            container.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar os processos seletivos:', error);
    }
}





function showJobDetails(processoId) {
    // Mostra o modal e armazena o ID do processo seletivo
    document.getElementById('jobDetailsModal').style.display = 'block';

    // Armazena o ID do processo seletivo no formulário de candidatura
    document.getElementById('resumeForm').dataset.processoId = processoId;

    fetch(`http://localhost:8080/processo_seletivo/${processoId}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('jobTitle').innerText = data.vaga.titulo;
            document.getElementById('jobDetails').innerText = data.vaga.descricao; 
            document.getElementById('jobRequirements').innerText = data.vaga.requisitos;
            document.getElementById('jobSalary').innerText = `Salário: R$ ${data.vaga.salario}`; 
        })
        .catch(error => {
            console.error('Erro ao buscar detalhes do processo seletivo:', error);
        });
}

async function submitForm(event) {
    event.preventDefault();

    const processoId = document.getElementById('resumeForm').dataset.processoId;

    const formData = new FormData();
    formData.append("candidato", new Blob([JSON.stringify({
        nomeCompleto: document.getElementById('fullName').value,
        email: document.getElementById('email').value,
        telefone: document.getElementById('phone').value,
        cartaApresentacao: document.getElementById('coverLetter').value,
        processoSeletivo: { id: processoId }
    })], { type: "application/json" }));

    const fileInput = document.getElementById('resume');
    if (fileInput.files.length > 0) {
        formData.append("curriculo", fileInput.files[0]);
    }

    try {
        const token = localStorage.getItem('authToken');

        const response = await fetch('http://localhost:8080/candidato/candidatar', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: formData
        });

        if (response.ok) {
            alert('Candidatura enviada com sucesso!');
            closeModal();
        } else {
            const errorData = await response.json();
            console.error('Erro na candidatura:', errorData);
            alert('Erro ao enviar a candidatura. Por favor, tente novamente.');
        }
    } catch (error) {
        console.error('Erro ao enviar a candidatura:', error);
        alert('Erro ao enviar a candidatura. Por favor, tente novamente.');
    }
}

function closeModal() {
    document.getElementById('jobDetailsModal').style.display = 'none';
}

function goBack() {
    document.getElementById('jobDetailsModal').style.display = 'none';
}


function filtrarProcessos() {
    const inputBusca = document.getElementById('inputBusca'); 
    const processos = document.querySelectorAll('.processo');

    inputBusca.addEventListener('input', () => {
        const textoBusca = inputBusca.value.toLowerCase();

        processos.forEach(processo => {
            if (processo.textContent.toLowerCase().includes(textoBusca)) {
                processo.style.display = ''; 
            } else {
                processo.style.display = 'none'; 
            }
        });
    });
}

document.getElementById('searchBar').addEventListener('input', function () {
    const searchTerm = this.value.toLowerCase();
    const vagas = document.querySelectorAll('.processo');

    vagas.forEach(vaga => {
        const titulo = vaga.querySelector('h3').textContent.toLowerCase();
        if (titulo.includes(searchTerm)) {
            vaga.style.display = 'block';
        } else {
            vaga.style.display = 'none';
        }
    });
});

document.getElementById('clearFilter').addEventListener('click', function () {
    document.getElementById('searchBar').value = ''; 
    const vagas = document.querySelectorAll('.processo');

    vagas.forEach(vaga => {
        vaga.style.display = 'block'; 
    });
});

function parseJwt(token) {
    const base64Url = token.split('.')[1]; // Pega a parte do payload do token
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
        atob(base64)
            .split('')
            .map(c => `%${('00' + c.charCodeAt(0).toString(16)).slice(-2)}`)
            .join('')
    );
    return JSON.parse(jsonPayload);
}

function redirectToGerenciar(processoId) {
    window.location.href = `./AvaliaCandidatos.html?processoId=${processoId}`;
}




document.addEventListener('DOMContentLoaded', init);



async function init() {
    await loadProcessosSeletivos(); 
    filtrarProcessos(); 
}


document.addEventListener('DOMContentLoaded', init);
