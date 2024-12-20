// js/cadastro.js

const nome = document.querySelector("#user");
const email = document.querySelector("#email");
const senha = document.querySelector("#senha");
const confirmarSenha = document.querySelector("#confirmar-senha");

document.getElementById('cadastroUser').addEventListener('click', () => {
    if (senha.value === confirmarSenha.value) {
        enviarCadastro("http://localhost:8080/auth/register");
    } else {
        alert("As senhas não coincidem. Por favor, verifique.");
    }
});

async function enviarCadastro(url) {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: nome.value,
                email: email.value,
                password: senha.value
            })
        });

        if (response.ok) {
            const data = await response.json();
            alert(`Usuário cadastrado com sucesso! Bem-vindo, ${data.name}.`);
            // Redirecionar ou limpar o formulário após o cadastro
            document.querySelector("form").reset();
        } else {
            const errorData = await response.json();
            alert(`Erro: ${errorData.message || 'Erro ao cadastrar o usuário.'}`);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao se conectar com o servidor.');
    }
}
