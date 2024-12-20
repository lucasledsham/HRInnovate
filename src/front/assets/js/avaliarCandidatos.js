document.addEventListener("DOMContentLoaded", function () {

    const userName = localStorage.getItem("userName");


    const userNameSpan = document.getElementById("userName");
    if (userName) {
        userNameSpan.textContent = userName;
    } else {
        userNameSpan.textContent = "Usuário não identificado";
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const processoId = urlParams.get('processoId'); // Obtém o ID do processo

    if (processoId) {
        loadCandidatos(processoId);
    }

    // Adiciona o evento de clique no ícone de notificação
    document.querySelector('.notification-icon').addEventListener('click', () => {
        const notificationPanel = document.getElementById('notificationPanel');
        notificationPanel.style.display = notificationPanel.style.display === 'none' ? 'block' : 'none';  // Alterna a exibição


    });
});

function loadCandidatos(processoId) {
    fetch(`http://localhost:8080/processo_seletivo/${processoId}/candidatos`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    })
        .then(response => response.json())
        .then(data => {
            const candidatosTableBody = document.querySelector('#candidatosTable tbody');
            candidatosTableBody.innerHTML = '';

            data.forEach(candidato => {
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${processoId}</td>
                <td>${candidato.id}</td>
                <td>${candidato.nomeCompleto}</td>
                <td>${candidato.email}</td>
                <td>${candidato.telefone}</td>
                <td>
                    <button class="download-curriculo" data-candidato-id="${candidato.id}">
                    Baixar Currículo
                    </button>
                </td>
                <td>${candidato.cartaApresentacao}</td>
                <td id="status-${processoId}-${candidato.id}">${candidato.status}</td>
                <td id="actions-${processoId}-${candidato.id}" class="actions">
                  ${getActionButtons(processoId, candidato.id, candidato.status)}
                 </td>
            `;
                candidatosTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar os candidatos:', error);
        });
}
function getActionButtons(processoId, candidatoId, status) {
    if (status === 'Em analise') {
        return `
            <button class="btn approve" onclick="aprovarCandidato(${processoId}, ${candidatoId})">Aprovar</button>
            <button class="btn reject" onclick="reprovarCandidato(${processoId}, ${candidatoId})">Reprovar</button>
        `;
    } else if (status === 'Aprovado') {
        return `
            <button class="btn schedule" onclick="agendarEntrevista(${processoId}, ${candidatoId})">Agendar Entrevista</button>
        `;
    } else {
        return ''; // Sem botões para candidatos reprovados
    }
}

function aprovarCandidato(processoId, candidatoId) {
    fetch(`http://localhost:8080/processo_seletivo/${processoId}/candidato/${candidatoId}/aprovar`, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Candidato aprovado com sucesso!');
            updateStatus(processoId, candidatoId, 'APROVADO');
            loadCandidatos(processoId);
        } else {
            alert('Erro ao aprovar candidato.');
        }
    })
    .catch(error => {
        console.error('Erro ao aprovar o candidato:', error);
    });

}

function reprovarCandidato(processoId, candidatoId) {
    fetch(`http://localhost:8080/processo_seletivo/${processoId}/candidato/${candidatoId}/reprovar`, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Candidato reprovado com sucesso!');
            updateStatus(processoId, candidatoId, 'REPROVADO');
            loadCandidatos(processoId);
        } else {
            alert('Erro ao aprovar candidato.');
        }
    })
    .catch(error => {
        console.error('Erro ao reprovar o candidato:', error);
    });
}

function agendarEntrevista(processoId, candidatoId) {
    alert(`Abrindo agenda para o candidato ${candidatoId} do processo ${processoId}`);

    // Variáveis para o modal e overlay
    let modal;
    let overlay;

    function createModal() {
        // Criar o overlay
        overlay = document.createElement('div');
        overlay.className = 'modal-overlay';

        // Criar o modal
        modal = document.createElement('div');
        modal.className = 'modal'; // Adiciona a classe estilizada
        modal.innerHTML = `
            <h2>Agendar Entrevista</h2>
            <form id="agendar-entrevista-form">
                <label for="data-hora">Data e Hora:</label>
                <input type="datetime-local" id="data-hora" name="dataHora" required>
                <br>
                <label for="local">Local:</label>
                <input type="text" id="local" name="local" required>
                <br>
                <label for="observacoes">Observações:</label>
                <textarea id="observacoes" name="observacoes"></textarea>
                <br>
                <button type="submit" id="agendar-entrevista-btn">Agendar Entrevista</button>
            </form>
            <button id="fechar-modal-btn" style="margin-top: 10px;">Fechar</button>
        `;

        // Adicionar o modal e o overlay ao body
        document.body.appendChild(overlay);
        document.body.appendChild(modal);

        // Fechar modal ao clicar no botão ou no overlay
        overlay.addEventListener('click', () => closeModal());
        document.getElementById('fechar-modal-btn').addEventListener('click', () => closeModal());
    }

    function closeModal() {
        // Remover o modal e o overlay da página
        if (modal && overlay) {
            document.body.removeChild(modal);
            document.body.removeChild(overlay);
        }
    }

    // Chamar o modal
    createModal();

    const form = document.getElementById('agendar-entrevista-form');
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const formData = new FormData(form);
        const entrevista = {
            candidatoId: candidatoId,
            dataHora: formData.get('dataHora'),
            local: formData.get('local'),
            observacoes: formData.get('observacoes')
        };

        fetch(`http://localhost:8080/entrevistas/agendar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            },
            body: JSON.stringify(entrevista)
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Erro ao agendar entrevista: ${response.statusText}`);
                }
                return response.json();
            })
            .then((data) => {
                alert('Entrevista agendada com sucesso!');
                console.log(data);
                
                // Atualizar o status e as ações do candidato
                updateStatus(processoId, candidatoId, 'Entrevista Agendada');

                closeModal(); // Fechar modal ao finalizar com sucesso
            })
            .catch((error) => {
                console.error(error);
                alert('Erro ao agendar entrevista. Verifique os dados e tente novamente.');
            });
    });
}


function updateStatus(processoId, candidatoId, newStatus) { 
    const statusElement = document.querySelector(`#status-${processoId}-${candidatoId}`);
    const actionsElement = document.querySelector(`#actions-${processoId}-${candidatoId}`);

    // Atualizar o status na interface
    if (statusElement) {
        statusElement.textContent = newStatus;
    }

    // Atualizar os botões de ação
    if (actionsElement) {
        if (newStatus === 'Entrevista Agendada') {
            actionsElement.innerHTML = '<span>Entrevista já agendada</span>'; // Exibir uma mensagem ao invés de botões
        } else {
            actionsElement.innerHTML = getActionButtons(processoId, candidatoId, newStatus);
        }
    }
}

//------------------------------------------------------------------------------------------------

async function loadNotificacoes() {
    try {
        const response = await fetch(`http://localhost:8080/user/notifications`, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        });

        if (response.status === 403) {
            throw new Error("Permissão negada. O token pode estar expirado ou inválido.");
        }

        if (!response.ok) {
            throw new Error('Erro ao buscar notificações');
        }

        const notificacoes = await response.json();
        const notificationPanel = document.getElementById('notificationPanel');

        if (notificacoes.length === 0) {
            notificationPanel.innerHTML = '<p>Sem notificações no momento</p>';
            return;
        }

        // Buscar status de entrevistas em paralelo
        const notificacoesAtualizadas = await Promise.all(
            notificacoes.map(async notificacao => {
                if (notificacao.tipoNotificacao === 'TIPO_ENTREVISTA') {
                    try {
                        const entrevistaResponse = await fetch(`http://localhost:8080/entrevistas/${notificacao.entrevistaId}`, {
                            method: 'GET',
                            headers: {
                                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                            }
                        });

                        if (entrevistaResponse.ok) {
                            const entrevista = await entrevistaResponse.json();
                            return { ...notificacao, status: entrevista.confirmada }; // Adiciona o status ao objeto notificação
                        } else {
                            console.error(`Erro ao buscar status da entrevista: ${notificacao.entrevistaId}`);
                        }
                    } catch (error) {
                        console.error(`Erro ao buscar entrevista ${notificacao.entrevistaId}:`, error.message);
                    }
                }
                return notificacao; // Retorna a notificação original se não for TIPO_ENTREVISTA
            })
        );

        // Renderizar notificações
        notificationPanel.innerHTML = '';
        notificacoesAtualizadas.forEach(notificacao => {
            const notificationItem = document.createElement('div');
            notificationItem.className = 'notification-item';

            let actionButtons = '';

            if (notificacao.tipoNotificacao === 'TIPO_ENTREVISTA') {
                if (notificacao.status === 'PENDENTE') {
                    actionButtons = `
                        <button class="btn accept" onclick="aceitarEntrevista(${notificacao.entrevistaId})">Confirmar</button>
                        <button class="btn reject" onclick="recusarEntrevista(${notificacao.entrevistaId})">Recusar</button>
                    `;
                } else if (notificacao.status === 'CONFIRMADA') {
                    actionButtons = '<p>Status: Confirmada</p>';
                } else if (notificacao.status === 'RECUSADA') {
                    actionButtons = '<p>Status: Recusada</p>';
                }
            }

            notificationItem.innerHTML = `
                <div class="notification-header">
        <input type="checkbox" class="mark-read" data-id="${notificacao.id}" ${notificacao.lida ? 'checked' : ''}>
        <p><strong>${notificacao.mensagem}</strong></p>
    </div>  
                ${actionButtons}
            `;
            notificationPanel.appendChild(notificationItem);
        });
    } catch (error) {
        console.error('Erro ao carregar notificações:', error.message);
    }
}

async function recusarEntrevista(id) {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/user/${id}/reprovar`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            alert("Entre em contato com a gente caso queira reagendar uma entrevista, Obrigado! Contato: (31)3622-1920");
            loadNotificacoes();
        } else {
            alert("Erro ao reprovar a entrevista");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
}

async function aceitarEntrevista(id) {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert("Token JWT não encontrado.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/user/${id}/confirmar`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            alert("Aguardamos você no local agendado, Obrigado!");
            loadNotificacoes();
        } else {
            alert("Erro ao confirmar a entrevista");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
}

document.addEventListener('click', event => {
    if (event.target.classList.contains('download-curriculo')) {
        const candidatoId = event.target.getAttribute('data-candidato-id');
        baixarCurriculo(candidatoId);
    }
});

function baixarCurriculo(candidatoId) {
    fetch(`http://localhost:8080/candidato/${candidatoId}/curriculo`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao baixar o currículo');
            }
            return response.blob();
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = `curriculo-${candidatoId}.pdf`; // Nome do arquivo
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Erro ao baixar o currículo:', error);
        });
}

async function markAsRead(notificationId) {
    try {
        const response = await fetch(`http://localhost:8080/notificacoes/${notificationId}/mark-as-read`, {
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken'),
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            console.log(`Notificação ${notificationId} marcada como lida.`);
            // Atualizar o status da notificação no front-end
            const checkbox = document.querySelector(`input[data-id="${notificationId}"]`);
            if (checkbox) {
                checkbox.checked = true;  // Marcar o checkbox como "lido"
            }
        } else {
            console.error('Erro ao marcar a notificação como lida');
        }
    } catch (error) {
        console.error('Erro ao marcar a notificação como lida:', error);
    }
}

// Chamar a função de marcação ao clicar no checkbox
document.addEventListener('change', async function (event) {
    if (event.target.classList.contains('mark-read')) {
        const notificationId = event.target.dataset.id;  // Pega o ID do data-id
        const checkbox = event.target;
        
        if (checkbox.checked) {
            try {
                await markAsRead(notificationId);  // Marcar como lido
                updateNotificationCount();
            } catch (error) {
                console.error('Erro ao marcar a notificação como lida:', error);
                // Se falhar, desmarcar o checkbox
                checkbox.checked = false;
            }
        }
    }
});

async function updateNotificationCount() {
    try {
        const response = await fetch('http://localhost:8080/notificacoes/contarNaoLidas', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        });

        if (response.ok) {
            const unreadCount = await response.json();
            const notificationCountElement = document.querySelector('.notification-count');
            notificationCountElement.textContent = unreadCount;
        } else {
            console.error('Erro ao buscar a contagem de notificações não lidas.');
        }
    } catch (error) {
        console.error('Erro ao atualizar a contagem de notificações:', error.message);
    }
}



document.addEventListener('DOMContentLoaded', () => {
    loadNotificacoes();
    updateNotificationCount(); // Carrega a contagem inicial de notificações
});
