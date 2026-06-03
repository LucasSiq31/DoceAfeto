const input = document.getElementById("cep");

input.addEventListener('input', (evento) => {
    if (evento.target.value.trim() !== '') {

        cep = evento.target.value;

        if(cep.length == 8){
            calcularFrete(cep);
        }
    }
});

console.log(input.value)
mensagem = ""

function calcularFrete(numeroCEP){
    fetch(`https://viacep.com.br/ws/${numeroCEP}/json/`).then(response => {
    return response.json()
    }).then(corpo => {

        if(corpo.uf == undefined){
            document.getElementById("mensagem").innerHTML = '<div class="aviso perigo"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-triangle" viewBox="0 0 16 16"><path d="M7.938 2.016A.13.13 0 0 1 8.002 2a.13.13 0 0 1 .063.016.15.15 0 0 1 .054.057l6.857 11.667c.036.06.035.124.002.183a.2.2 0 0 1-.054.06.1.1 0 0 1-.066.017H1.146a.1.1 0 0 1-.066-.017.2.2 0 0 1-.054-.06.18.18 0 0 1 .002-.183L7.884 2.073a.15.15 0 0 1 .054-.057m1.044-.45a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767z"/><path d="M7.002 12a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 5.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/></svg><div><b>Aviso</b><p>Seu CEP é inválido. Tente outro Endereço.</p></div></div>'
            
            mensagem = "Indisponível"
            document.getElementById("pedidoFrete").innerHTML = mensagem
            document.getElementById("totalFrete").innerHTML = mensagem

            document.getElementById("btnCep").disabled = true;
            return
        }

        document.getElementById("rua").value = corpo.logradouro;
        document.getElementById("bairro").value = corpo.bairro;
        document.getElementById("cidade").value = corpo.localidade + " - " + corpo.uf;

        incompativel = false; //Verifica se o CEP é incompativel da eligibidade para entrega
        frete = 50

        // Frete Mogi
        if(corpo.localidade == "Mogi das Cruzes"){
            valorFrete = frete - frete
            mensagem = "Grátis";

        // Frete Sudeste
        }else if(corpo.uf == "SP" || corpo.uf == "RJ" || corpo.uf == "ES" || corpo.uf == "MG"){
            valorFrete = frete/2
            mensagem = formatReal(valorFrete)

        // Frete Sul
        }else if(corpo.uf == "RS"|| corpo.uf == "SC" || corpo.uf == "PR"){
            valorFrete = frete*0.6
            mensagem = formatReal(valorFrete)
            
        // Frete Nordeste
        }else if(corpo.uf == "BA" || corpo.uf == "PE" || corpo.uf == "CE" || corpo.uf == "AL" || corpo.uf == "SE" || corpo.uf == "MA" || corpo.uf == "PB" || corpo.uf == "RN" || corpo.uf == "PI"){
            valorFrete = frete*0.8
            mensagem = formatReal(valorFrete)

        // Frete Norte
        }else if(corpo.uf == "RO" || corpo.uf == "RR" || corpo.uf == "AM" || corpo.uf == "AC" || corpo.uf == "TO" || corpo.uf == "PA" || corpo.uf == "AP"){
            valorFrete = frete
            mensagem = formatReal(valorFrete)

        // Frete Centro Oeste
        }else if(corpo.uf == "GO" || corpo.uf == "MT" || corpo.uf == "MS" || corpo.uf == "DF"){
            valorFrete = frete*0.9
            mensagem = formatReal(valorFrete)
            
        }else{ //Se o CEP for de outro lugar
            incompativel = true;
            frete = 0;
            mensagem = "Indisponível"
            document.getElementById("btnCep").disabled = true;
            document.getElementById("mensagem").innerHTML = '<div class="aviso perigo"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-triangle" viewBox="0 0 16 16"><path d="M7.938 2.016A.13.13 0 0 1 8.002 2a.13.13 0 0 1 .063.016.15.15 0 0 1 .054.057l6.857 11.667c.036.06.035.124.002.183a.2.2 0 0 1-.054.06.1.1 0 0 1-.066.017H1.146a.1.1 0 0 1-.066-.017.2.2 0 0 1-.054-.06.18.18 0 0 1 .002-.183L7.884 2.073a.15.15 0 0 1 .054-.057m1.044-.45a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767z"/><path d="M7.002 12a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 5.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/></svg><div><b>Aviso</b><p>Seu CEP não é compatível para a entrega. Tente outro Endereço.</p></div></div>'
        }

        if(!incompativel){
            document.getElementById("mensagem").innerHTML= ""
            document.getElementById("frete").value = frete
            document.getElementById("btnCep").disabled = false;
        }

        document.getElementById("pedidoFrete").innerHTML = mensagem
        document.getElementById("totalFrete").innerHTML = mensagem

        const total = document.getElementById('total');
        const subtotal = document.querySelector('.subtotal');

        let valorSubtotal = realToNumber(subtotal.innerHTML);

        console.log(frete + valorSubtotal);

        total.innerHTML = formatReal(frete + valorSubtotal);
    })
}

function exibirEndereco(){
    cepPed = document.getElementById("cepPed").innerHTML

    fetch(`https://viacep.com.br/ws/${cepPed}/json/`).then(response => {
    return response.json()
    }).then(corpo => {

        document.getElementById("ruaPed").innerHTML = corpo.logradouro;
        document.getElementById("bairroPed").innerHTML = corpo.bairro;
        document.getElementById("cidadePed").innerHTML = corpo.localidade + " - " + corpo.uf;

    })
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
