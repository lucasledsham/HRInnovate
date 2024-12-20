// Seleciona os elementos do formulário
const email = document.querySelector("#email");
const senha = document.querySelector("#senha");

document.getElementById('logar').addEventListener('click', () => {
    enviarLogin("http://localhost:8080/auth/login");
});

async function enviarLogin(url) {
    try {
        // Envia a requisição POST com os dados de login
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email.value,
                password: senha.value
            })
        });

        if (response.ok) {
            const data = await response.json();
            // Armazena o token JWT no localStorage
            localStorage.setItem('authToken', data.token);
            localStorage.setItem("userName", data.name);
            alert(`Login bem-sucedido! Bem-vindo, ${data.name}.`);

            window.location.href = data.redirectPage;
        } else {
            const errorData = await response.json();
            alert(`Erro: ${errorData.message || 'Erro ao fazer login.'}`);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Dados inválidos. Por favor, tente novamente.');
    }
}
