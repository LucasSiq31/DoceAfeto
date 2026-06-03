//MENU HAMBURGUER
const btnMenu = document.querySelector('.btnMenu');
const menu = document.querySelector('.menu');

// Cria o overlay dinamicamente
// Verifica se já existe para não duplicar
let overlay = document.querySelector('.fundo');
if (!overlay) {
    overlay = document.createElement('div');
    overlay.classList.add('fundo');
    document.body.appendChild(overlay);
}

// Abrir/fechar menu
btnMenu.addEventListener('click', () => {
    menu.classList.toggle('active');
    overlay.classList.toggle('active');
});

// Fechar clicando fora
overlay.addEventListener('click', () => {
    menu.classList.remove('active');
    overlay.classList.remove('active');
});

const closeMenuBtn = document.querySelector('.closeMenu');

closeMenuBtn.addEventListener('click', () => {
    menu.classList.remove('active');
    overlay.classList.remove('active');
});


//MODAL E ALERT

//Função que ativa o modal
function ativarModal(id){
    document.getElementById(id).style.display = "flex";

    //Ativa a função de fechar no X do modal
    const closeMenuBtn = document.querySelector(`.${id}`);
    closeMenuBtn.addEventListener('click', () => {
        fechar(id)//Chama a função de fechar ao clicar no X
    });
}

//Função que ativa o alert
function ativarAlert(id){
    document.getElementById(id).style.display = "flex";
}

//Função que fecha o modal e o alert
function fechar(id){
    document.getElementById(id).style.display = "none";
}

document.addEventListener("DOMContentLoaded", () => {
    const banner = document.getElementById("arquivo");

    console.log(banner);

    banner.addEventListener("input", function () {
        console.log("digitou");
        document.getElementById("imagem").src = banner.value;
    });
});