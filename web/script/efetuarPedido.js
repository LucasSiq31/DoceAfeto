subtotal = document.querySelector('.subtotal');
quantidade = document.getElementById('qtd');
pedidoQtd = document.querySelector('.pedidoQtd');
totalQtd = document.querySelector('.totalQtd');

preco = realToNumber(subtotal.innerHTML); 

quantidade.addEventListener('input', (evento) => {
    if (evento.target.value.trim() !== '') {

        totalQtd.innerHTML = "x " + quantidade.value;

        novoPreco = preco * parseInt(quantidade.value);
        subtotal.innerHTML = formatReal(novoPreco)
    }
});

function abrirPedidos(){
    window.location.href = "controle_pedido?op=exibirTodos";
}
function addQtd(){
    quantidade.value ++;
    totalQtd.innerHTML = "x " + quantidade.value;

    novoPreco = preco * parseInt(quantidade.value);
    subtotal.innerHTML = formatReal(novoPreco)
}
function removeQtd(){

    if(quantidade.value == 1){
        return
    }else{
        quantidade.value -= 1;
        totalQtd.innerHTML = "x " + quantidade.value;
        
        novoPreco = preco * parseInt(quantidade.value);
        subtotal.innerHTML = formatReal(novoPreco)
    }
}

function realToNumber(valor){
    valor = valor.replace("R$", "")
             .replace(",", ".")
             .trim();

    return parseFloat(valor);
}

function formatReal(valor){
    novoPreco = "R$" + valor.toLocaleString('pt-BR', { 
        minimumFractionDigits: 2, 
        maximumFractionDigits: 2 
    });

    return novoPreco;
}

const formulario = document.querySelector('form');

formulario.addEventListener('keydown', function(event) {
  if (event.key === 'Enter') {
    event.preventDefault(); // Impede o envio do formulário
    return false;
  }
});

//Não avançar etapa qenquanto não conlcuir
function avancoCliente(){
    nomeInput = document.getElementsByName("nome")[0].value
    sobrenomeInput = document.getElementsByName("sobrenome")[0].value
    telefoneInput = document.getElementsByName("telefone")[0].value

    if(nomeInput == "" || sobrenomeInput == "" || telefoneInput == ""){
        ativarAlert('alertPedido')
    }else{
        proximoPasso();
    }
}

function avancoCep(){
    cepInput = document.getElementsByName("cep")[0].value
    numeroInput = document.getElementsByName("numero")[0].value

    if(cepInput == "" || numeroInput == ""){
        ativarAlert('alertPedido')
    }else{
        proximoPasso();
    }
}

function verificarTudo(){
    nomeInput = document.getElementsByName("nome")[0].value
    sobrenomeInput = document.getElementsByName("sobrenome")[0].value
    telefoneInput = document.getElementsByName("telefone")[0].value
    cepInput = document.getElementsByName("cep")[0].value
    numeroInput = document.getElementsByName("numero")[0].value

    if(nomeInput == "" || sobrenomeInput == "" || telefoneInput == "" || cepInput == "" || numeroInput == ""){
        ativarAlert('alertPedido')
    }else{
        document.getElementById("meuForm").submit();
    }
    
}



