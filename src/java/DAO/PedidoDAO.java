/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package DAO;
 
/**
*
* @author usuario
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import model.Pedido;
import model.Produto;
import util.Conexao;
public class PedidoDAO {
    // Efetuar Pedido
    public void efetuarPedido(Pedido ped)
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "INSERT INTO pedido "
                + "(quantidade, cep, numero, status, frete, nome, sobrenome, telefone, id_produto, total, sub_total) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement comando = con.prepareStatement(SQL);
        comando.setInt(1, ped.getQuantidade());
        comando.setString(2, ped.getCep());
        comando.setInt(3, ped.getNumero());
        comando.setString(4, ped.getStatus());
        comando.setDouble(5, ped.getFrete());
        comando.setString(6, ped.getNome());
        comando.setString(7, ped.getSobrenome());
        comando.setString(8, ped.getTelefone());
        comando.setInt(9, ped.getProduto().getId());
        comando.setDouble(10, ped.getTotal());
        comando.setDouble(11, ped.getSub_total());
        comando.execute();
        con.close();
    }
    // Atualizar apenas o status
    public void atualizarStatus(Pedido ped)
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "UPDATE pedido SET status = ? WHERE id = ?";
        PreparedStatement comando = con.prepareStatement(SQL);
        comando.setString(1, ped.getStatus());
        comando.setInt(2, ped.getId());
        comando.execute();
        con.close();
    }
    // Consultar pedido por ID
    public Pedido consultarById(Pedido p)
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "SELECT p.*, "
                + "pr.nome AS nome_produto, "
                + "pr.preco AS preco_produto, "
                + "pr.descricao AS descricao_produto,"
                + "pr.imagem AS imagem_produto "
                + "FROM pedido p "
                + "INNER JOIN produto pr "
                + "ON p.id_produto = pr.id "
                + "WHERE p.id = ?";
        PreparedStatement comando = con.prepareStatement(SQL);
        comando.setInt(1, p.getId());
        ResultSet rs = comando.executeQuery();
        
        Pedido ped = new Pedido();
        if (rs.next()) {
            ped.setId(rs.getInt("id"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setCep(rs.getString("cep"));
            ped.setNumero(rs.getInt("numero"));
            ped.setStatus(rs.getString("status"));
            ped.setFrete(rs.getDouble("frete"));
            ped.setData(rs.getDate("data").toLocalDate());
            ped.setNome(rs.getString("nome"));
            ped.setSobrenome(rs.getString("sobrenome"));
            ped.setTelefone(rs.getString("telefone"));
            ped.setTotal(rs.getDouble("total"));
            ped.setSub_total(rs.getDouble ("sub_total"));
            
            Produto prod = new Produto();
            
            prod.setId(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome_produto"));
            prod.setPreco(rs.getDouble("preco_produto"));
            prod.setDescricao(rs.getString("descricao_produto"));
            prod.setImagem(rs.getString("imagem_produto"));
            ped.setProduto(prod);
        }
        con.close();
        return ped;
    }
    // Consultar todos os pedidos
    public List<Pedido> consultarTodos()
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "SELECT p.*, "
                + "pr.nome AS nome_produto, "
                + "pr.preco AS preco_produto, "
                + "pr.descricao AS descricao_produto,"
                + "pr.imagem AS imagem_produto "
                + "FROM pedido p "
                + "INNER JOIN produto pr "
                + "ON p.id_produto = pr.id "
                + "ORDER BY p.data DESC";
        PreparedStatement comando = con.prepareStatement(SQL);
        ResultSet rs = comando.executeQuery();
        List<Pedido> listaPedidos = new ArrayList<>();
        while (rs.next()) {
            Pedido ped = new Pedido();
            ped.setId(rs.getInt("id"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setCep(rs.getString("cep"));
            ped.setNumero(rs.getInt("numero"));
            ped.setStatus(rs.getString("status"));
            ped.setFrete(rs.getDouble("frete"));
            ped.setData(rs.getDate("data").toLocalDate());
            ped.setNome(rs.getString("nome"));
            ped.setSobrenome(rs.getString("sobrenome"));
            ped.setTelefone(rs.getString("telefone"));
            ped.setTotal(rs.getDouble("total"));
            ped.setSub_total(rs.getDouble ("sub_total"));
            Produto prod = new Produto();
            prod.setId(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome_produto"));
            prod.setPreco(rs.getDouble("preco_produto"));
            prod.setDescricao(rs.getString("descricao_produto"));
            prod.setImagem(rs.getString("imagem_produto"));
            ped.setProduto(prod);
            listaPedidos.add(ped);
        }
        con.close();
        return listaPedidos;
    }
    // Pesquisar pedidos
    public List<Pedido> pesquisarPor(String pesquisa)
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "SELECT p.*, "
                + "pr.nome AS nome_produto, "
                + "pr.preco AS preco_produto, "
                + "pr.descricao AS descricao_produto,"
                + "pr.imagem AS imagem_produto "
                + "FROM pedido p "
                + "INNER JOIN produto pr "
                + "ON p.id_produto = pr.id "
                + "WHERE p.nome LIKE ? "
                + "OR p.telefone LIKE ? "
                + "OR p.status LIKE ? "
                + "OR pr.nome LIKE ? "
                + "OR DATE_FORMAT(p.data, '%d/%m/%Y') LIKE ?"
                + "OR CAST(p.id AS CHAR) LIKE ?"
                + "ORDER BY p.data DESC";
        PreparedStatement comando = con.prepareStatement(SQL);
        comando.setString(1, "%" + pesquisa + "%");
        comando.setString(2, "%" + pesquisa + "%");
        comando.setString(3, "%" + pesquisa + "%");
        comando.setString(4, "%" + pesquisa + "%");
        comando.setString(5, "%" + pesquisa + "%");
        comando.setString(6, "%" + pesquisa + "%");
        
        ResultSet rs = comando.executeQuery();
        List<Pedido> listaPedidos = new ArrayList<>();
        while (rs.next()) {
            Pedido ped = new Pedido();
            ped.setId(rs.getInt("id"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setCep(rs.getString("cep"));
            ped.setNumero(rs.getInt("numero"));
            ped.setStatus(rs.getString("status"));
            ped.setFrete(rs.getDouble("frete"));
            ped.setData(rs.getDate("data").toLocalDate());
            ped.setNome(rs.getString("nome"));
            ped.setSobrenome(rs.getString("sobrenome"));
            ped.setTelefone(rs.getString("telefone"));
            ped.setTotal(rs.getDouble("total"));
            ped.setSub_total(rs.getDouble ("sub_total"));
            Produto prod = new Produto();
            prod.setId(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome_produto"));
            prod.setPreco(rs.getDouble("preco_produto"));
            prod.setDescricao(rs.getString("descricao_produto"));
            prod.setImagem(rs.getString("imagem_produto"));
            ped.setProduto(prod);
            listaPedidos.add(ped);
        }
        con.close();
        return listaPedidos;
    }
    // Exibir pedidos pendentes
    public List<Pedido> exibirPendentes()
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "SELECT p.*, "
                + "pr.nome AS nome_produto, "
                + "pr.preco AS preco_produto, "
                + "pr.descricao AS descricao_produto,"
                + "pr.imagem AS imagem_produto "
                + "FROM pedido p "
                + "INNER JOIN produto pr "
                + "ON p.id_produto = pr.id "
                + "WHERE p.status <> 'Concluído' "
                + "ORDER BY p.data DESC";
        PreparedStatement comando = con.prepareStatement(SQL);
        ResultSet rs = comando.executeQuery();
        List<Pedido> listaPedidos = new ArrayList<>();
        while (rs.next()) {
            Pedido ped = new Pedido();
            ped.setId(rs.getInt("id"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setCep(rs.getString("cep"));
            ped.setNumero(rs.getInt("numero"));
            ped.setStatus(rs.getString("status"));
            ped.setFrete(rs.getDouble("frete"));
            ped.setData(rs.getDate("data").toLocalDate());
            ped.setNome(rs.getString("nome"));
            ped.setSobrenome(rs.getString("sobrenome"));
            ped.setTelefone(rs.getString("telefone"));
            ped.setTotal(rs.getDouble("total"));
            ped.setSub_total(rs.getDouble ("sub_total"));
            Produto prod = new Produto();
            prod.setId(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome_produto"));
            prod.setPreco(rs.getDouble("preco_produto"));
            prod.setDescricao(rs.getString("descricao_produto"));
            prod.setImagem(rs.getString("imagem_produto"));
            ped.setProduto(prod);
            listaPedidos.add(ped);
        }
        con.close();
        return listaPedidos;
    }
    
    public List<Pedido> exibirConcluidos()
            throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "SELECT p.*, "
                + "pr.nome AS nome_produto, "
                + "pr.preco AS preco_produto, "
                + "pr.descricao AS descricao_produto,"
                + "pr.imagem AS imagem_produto "
                + "FROM pedido p "
                + "INNER JOIN produto pr "
                + "ON p.id_produto = pr.id "
                + "WHERE p.status = 'Concluído' "
                + "ORDER BY p.data DESC";
        PreparedStatement comando = con.prepareStatement(SQL);
        ResultSet rs = comando.executeQuery();
        List<Pedido> listaPedidos = new ArrayList<>();
        while (rs.next()) {
            Pedido ped = new Pedido();
            ped.setId(rs.getInt("id"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setCep(rs.getString("cep"));
            ped.setNumero(rs.getInt("numero"));
            ped.setStatus(rs.getString("status"));
            ped.setFrete(rs.getDouble("frete"));
            ped.setData(rs.getDate("data").toLocalDate());
            ped.setNome(rs.getString("nome"));
            ped.setSobrenome(rs.getString("sobrenome"));
            ped.setTelefone(rs.getString("telefone"));
            ped.setTotal(rs.getDouble("total"));
            ped.setSub_total(rs.getDouble ("sub_total"));
            Produto prod = new Produto();
            prod.setId(rs.getInt("id_produto"));
            prod.setNome(rs.getString("nome_produto"));
            prod.setPreco(rs.getDouble("preco_produto"));
            prod.setDescricao(rs.getString("descricao_produto"));
            prod.setImagem(rs.getString("imagem_produto"));
            ped.setProduto(prod);
            listaPedidos.add(ped);
        }
        con.close();
        return listaPedidos;
    }
}