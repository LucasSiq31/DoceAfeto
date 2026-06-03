<%-- 
    Document   : editarProduto
    Created on : 28 de mai. de 2026, 10:46:05
    Author     : usuario
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Pedido ped = (Pedido) request.getAttribute("pedido");

    String subFormat = String.format("%.2f", ped.getSub_total());
    String freteFormat = String.format("%.2f", ped.getFrete());
    String totalFormat = String.format("%.2f", ped.getTotal());

    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
    String dataFormatada = ped.getData().format(formatador);
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exibindo: Pedido <%out.print(ped.getId());%> | Doce Afeto</title>

    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/elementos.css">
    <link rel="stylesheet" href="style/responsividade.css">

    <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
</head>
<body>
    <header>
        <div class="container">
            <!-- Logo e ícones -->
            <div class="cabecalho">
                <!-- Botão menu hamburguer -->
                <div>
                    <button class="btnMenu">
                        <svg class="iconMenu" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"></path>
                        </svg>
                    </button>
                </div>

                <!-- Logo -->
                <div class="logo">
                    <img src="img/logo.png" alt="">
                    <p><b>Doce</b> Afeto</p>
                </div>

            </div>
        </div>
    </header>

    <nav>
        <div class="menu">
            <div class="menuHeader">
                <h1>Menu</h1>

                <svg class="closeMenu" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z"/>
                </svg>
            </div>
            <hr>
            
            <a href="controle_produto?op=home" >
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
                    <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293z"/>
                    <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293z"/>
                </svg>
                <p>Home</p>
            </a>

            <a href="controle_pedido?op=exibirTodos" class="selected">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-seam-fill" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.01-.003.268-.108a.75.75 0 0 1 .558 0l.269.108.01.003zM10.404 2 4.25 4.461 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339L8 5.961 5.596 5l6.154-2.461z"/>
                </svg>
                <p>Pedidos</p>
            </a>
            
            <a href="controle_produto?op=exibirTodos">
                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="16" fill="currentColor" version="1.1" id="_x32_" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512"  xml:space="preserve">
                    <path class="st0" d="M325.505,96.79c-6.878-6.886-18.037-6.886-24.923,0l-202.75,202.75c-6.886,6.887-6.886,18.046,0,24.932 l89.692,89.692c6.887,6.886,18.047,6.886,24.932,0l202.75-202.75c6.886-6.886,6.886-18.046,0-24.923L325.505,96.79z"/>
                    <path class="st0" d="M6.704,383.652c-4.005,1.28-6.713,5.009-6.704,9.204c0.018,4.204,2.76,7.907,6.773,9.153l44.05,13.677 l-12.172,45.892c-0.882,3.314,0.07,6.843,2.5,9.274c2.422,2.422,5.952,3.374,9.265,2.492l45.893-12.18l13.667,44.058 c1.254,4.014,4.957,6.756,9.162,6.773c4.196,0.009,7.924-2.699,9.205-6.704l31.004-97.322l-55.322-55.322L6.704,383.652z"/>
                    <path class="st0" d="M505.226,109.974l-44.058-13.668l12.18-45.884c0.873-3.322-0.069-6.852-2.501-9.274 c-2.422-2.431-5.96-3.374-9.265-2.5l-45.892,12.18L402.013,6.769c-1.246-4.014-4.948-6.756-9.153-6.764 c-4.204-0.018-7.924,2.69-9.204,6.695l-31.004,97.322l55.322,55.323l97.322-31.004c4.006-1.281,6.713-5.01,6.705-9.205 C511.982,114.931,509.24,111.228,505.226,109.974z"/>
                </svg>
                <p>Produtos</p>
            </a>
        </div>
    </nav>

    <div class="linha"></div>

    <main>
        <div class="container editarPedido">

            <a href="controle_pedido?op=exibirTodos" class="voltar">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5"/>
                </svg>
                Voltar
            </a>
            
            <div class="sessao">
                <h1><%out.print("Pedido: " + ped.getId());%></h1>
                <div class="linha"></div>
            </div>


            <div class="detalhesPedido">
                <img src="<%out.print(ped.getProduto().getImagem());%>" alt="" id="imgProd">
                <div>
                    <h2 id="nomeProd"><%out.print(ped.getProduto().getNome());%></h2>
                    <p id="descProd"><%out.print(ped.getProduto().getDescricao());%></p>
                    <p id="quantidadePed"><%out.print("Quantidade: " + ped.getQuantidade());%></p>
                </div>
            </div>

            <div class="linha"></div>
            <h3>Endereço: <span id="cepPed"><%out.print(ped.getCep());%></span></h3>

            <p><%out.print("<span id='ruaPed'>Rua exempmlo</span> - " + ped.getNumero());%></p>
            <p id='bairroPed'>Bairro</p>
            <p id='cidadePed'>Cidade - UF</p>

            <div class="linha"></div>
            <p class="pagamento" id="dataPed"><b>Data:</b> <%out.print(dataFormatada);%></p>
            <p class="pagamento" id="clientePed"><b>Cliente:</b> <%out.print(ped.getNome() + " " + ped.getSobrenome());%></p>
            <p class="pagamento" id="telCliente"><b>Telefone:</b> <%out.print(ped.getTelefone());%></p>

            <div class="linha"></div>
            <p class="pagamento" id="subtotalPed"><b>Subtotal:</b> <%out.print("R$" + subFormat);%></p>
            <p class="pagamento" id="fretePed"><b>Frete:</b> <%out.print("R$" + freteFormat);%></p>
            <p class="total" id="totalPed"><b>Total:</b> <%out.print("R$" + totalFormat);%></p>

            <form action="controle_pedido" method="GET">
                <div class="sessao">
                    <h1>Status</h1>
                    <div class="linha"></div>
                </div>
                
                <p>Você pode trocar o status do pedido</p>
                <input type="hidden" name="id" value="<%out.print(ped.getId());%>">
                <select name="status" id="status">
                    <option class="pendente" value="Em Preparo">Em Preparo</option>
                    <option class="pendente" value="Em Rota de Entrega">Em Rota de Entrega</option>
                    <option class="concluido" value="Concluído">Concluído</option>
                </select>
                <button type="submit" name="op" value="Atualizar">Concluir</button>
            </form>
        </div>
    </main>

    <footer>
        <div class="container">
            <div class="logo">
                <img src="img/logoBranco.png" alt="">
                <p><b>Doce</b> Afeto</p>
            </div>

            <div class="paginas">
                <a href="controle_produto?op=home">Home</a>
                <a href="controle_pedido?op=exibirTodos">Pedidos</a>
                <a href="controle_produto?op=exibirTodos">Produtos</a>
            </div>

            <div class="linksExternos">
                <p>Entre em contato:</p>

                <div>

                    <a target="_blank" href="https://www.instagram.com/">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                            <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334"/>
                        </svg>
                    </a>

                    <a target="_blank" href="https://br.linkedin.com/">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-whatsapp" viewBox="0 0 16 16">
                            <path d="M13.601 2.326A7.85 7.85 0 0 0 7.994 0C3.627 0 .068 3.558.064 7.926c0 1.399.366 2.76 1.057 3.965L0 16l4.204-1.102a7.9 7.9 0 0 0 3.79.965h.004c4.368 0 7.926-3.558 7.93-7.93A7.9 7.9 0 0 0 13.6 2.326zM7.994 14.521a6.6 6.6 0 0 1-3.356-.92l-.24-.144-2.494.654.666-2.433-.156-.251a6.56 6.56 0 0 1-1.007-3.505c0-3.626 2.957-6.584 6.591-6.584a6.56 6.56 0 0 1 4.66 1.931 6.56 6.56 0 0 1 1.928 4.66c-.004 3.639-2.961 6.592-6.592 6.592m3.615-4.934c-.197-.099-1.17-.578-1.353-.646-.182-.065-.315-.099-.445.099-.133.197-.513.646-.627.775-.114.133-.232.148-.43.05-.197-.1-.836-.308-1.592-.985-.59-.525-.985-1.175-1.103-1.372-.114-.198-.011-.304.088-.403.087-.088.197-.232.296-.346.1-.114.133-.198.198-.33.065-.134.034-.248-.015-.347-.05-.099-.445-1.076-.612-1.47-.16-.389-.323-.335-.445-.34-.114-.007-.247-.007-.38-.007a.73.73 0 0 0-.529.247c-.182.198-.691.677-.691 1.654s.71 1.916.81 2.049c.098.133 1.394 2.132 3.383 2.992.47.205.84.326 1.129.418.475.152.904.129 1.246.08.38-.058 1.171-.48 1.338-.943.164-.464.164-.86.114-.943-.049-.084-.182-.133-.38-.232"/>
                        </svg>
                    </a>

                    <a target="_blank" href="https://www.facebook.com/?locale=pt_BR">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                            <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951"/>
                        </svg>
                    </a>
                </div>

            </div>

        </div>
    </footer>


    <script src="script/menu.js"></script>
    <script src="script/editarPedido.js"></script>
    <script src="script/apiCep.js"></script>
    <script>
        exibirEndereco();

        document.getElementById("status").value = "<%out.print(ped.getStatus());%>";
    </script>
</body>
</html>