function editarProduto(id, nome, descricao, preco, categoria, imagem, ativo){

    // abre modal
    ativarModal('atualizaProduto');

    // preenche campos
    document.getElementById("idProduto").value = id;
    document.getElementById("nomeInput").value = nome;
    document.getElementById("descricaoInput").value = descricao;
    document.getElementById("precoInput").value = preco;
    document.getElementById("categoriaInput").value = categoria;
    document.getElementById("imagemInput").value = imagem;

    if (ativo == 1) {
        document.getElementById("ativo").checked = true;
    } else {
        document.getElementById("inativo").checked = true;
    }
    

    // altera imagem preview
    document.getElementById("imagemUpdate").src = imagem; 

}


console.log("TESTE")
// Banner a partir do input{
banner = document.getElementById("imagemInput")


banner.addEventListener("input", function () {
    
    document.getElementById("imagemUpdate").src = banner.value 
});