passoAtual = 1;
maiorPassoLiberado = 1;

function proximoPasso(){
    passoAtual += 1;

    if(passoAtual > maiorPassoLiberado){
        maiorPassoLiberado = passoAtual;
    }

    ativarPasso(passoAtual)
}

function ativarPasso(step){

    if(step > maiorPassoLiberado) return;

    passoAtual = step;

    document.querySelectorAll('.passo').forEach(passo => {
        passo.style.display = 'none';
    });
    document.getElementById('passo'+step).style.display = "block";

    // stepper
    document.querySelectorAll('.stepper .step').forEach((botao, index) => {

        const numero = index + 1;

        botao.classList.remove('active');
        botao.classList.remove('completed');

        if(numero < passoAtual){
            botao.classList.add('completed');
        }
        else if(numero == passoAtual){
            botao.classList.add('active');
        }

    });
}

document.querySelectorAll('.stepper .step').forEach((botao, index) => {

    botao.addEventListener('click', () => {

        const passo = index + 1;

        ativarPasso(passo);
    });

});

ativarPasso(1)