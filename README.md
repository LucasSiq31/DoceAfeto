
# 🧁Doce Afeto

![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JS](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
![MySQL](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=MySQL&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

Projeto do 3º Semestre da Graduação de Engenharia de Software na Disciplina de Implementação Orientado a Objeto
Site E-commerce de Doces Artesanais, e gerenciamento de produtos e pedidos.

---

### Objetivo

Criar uma aplicação Web para realizar as operações de CRUD (inserir, deletar, atualizar, consultar por id e consultar todos) em uma entidade fictícia de informação (exemplos: Cliente, Produto, Fornecedor, Funcionario, Pedido, Orcamento, Servico, Relacionamento, Hospedagem, etc) com, pelo menos, 10 atributos. Aprendizagem do uso do MVC para organização e conexão com o Banco de Dados.

---
### Critérios

- Implementação adequada de funcionalidades esperadas (CRUD ou equivalentes)
- Boas práticas de Oriantação a Objeto
- Conformidade ao MVC (Model, View e Controller)
- Conformidade ao DAO (Data Access Object)
- Uso adequado de WEB, HTML, CSS, JS
- Boa Usabilidade
- Bônus 1 - Toque pessoal
- Bônus 2 - Neutralização de Vulnerabilidades 

---
### Funcionalidade

#### Compra:
- O cliente escolhe um produto para compra e a quantidade.
- Os produtos habilitados podem ser pesquisados na barra de pesquisa na página inicial.
- O cliente digita os dados pessoais (nome, sobrenome e telefone) para preparar o pedido.
- Digita o CEP (O endereço é puxado automaticamente) e o Nº do endereço para calculo do frete.
- O cliente escolhe a forma de pagamento e efetua o pedido.
> [!NOTE]
> **Pagamento:** O pagamento não é salvo no sistema, pois se trata de um programa com uma loja fictícia

#### Produtos
- O dono da loja pode cadastrar produtos para o seu site na página `Produtos`. As informações são o nome do produto, descrição, preço, categoria (doce, torta ou bolo) e a imagem (url).
- O dono da loja pode atualizar os dados do produto, incluíndo a opção visibilidade. O produto pode ser desabilitado de aparecer na página inicial e nos resultados da pesquisa. A visibilidade pode ser habilitada novamente.
- Todos os produtos podem ser pesquisados na barra de pesquisa na página `Produtos`.

#### Pedidos
- O dono da loja, pode ver todos os pedidos do sistema na página `Pedidos`. Inicialmente aparece os detalhes principais, e ao clicar na ação `Exibir Detalhes`, todos os dados do pedido serão exibidos.
- Na página de exibição do pedido, o dono da loja pode alterar o status de entrega do produto (Em Preparo, Em rota de entrega e Concluído).
- Todos os pedidos podem ser pesquisados na barra de pesquisa ou ser filtrados pelos botões de ação para visualizar apenas os pedidos concluídos ou os pedidos pendentes.

---
### Estrutura

Separamos as responsabilidades de cada arquivo para manter o código organizado seguindo as regras do MVC, e está organizado da seguinte forma:

```text  
src/  
├── conf/  
│  └── MANIFEST.MF   
└── java/  
   ├── DAO/  
   │  ├── PedidoDAO.java
   │  └── ProdutoDAO.java
   ├── control/  
   │  ├── controle_pedido.java
   │  └── controle_produto.java
   ├── model/  
   │  ├── Pedido.java
   │  └── Produto.java
   └── util/  
      └── Conexao.java
```
## 👤 Autores
Desenvolvido por **Cauã Araújo Marques**, **Lucas Siqueira** e **Yasmim Pacehco**

<p>
  <a href="https://github.com/LucasSiq31">
    <img src="https://img.shields.io/badge/LucasSiq31-181717?style=for-the-badge&logo=github&logoColor=white" alt="LucasSiq31">
  </a>
  
  <a href="https://github.com/YasmimPacheco">
    <img src="https://img.shields.io/badge/YasmimPacheco-181717?style=for-the-badge&logo=github&logoColor=white" alt="YasmimPacheco">
  </a>
</p>
