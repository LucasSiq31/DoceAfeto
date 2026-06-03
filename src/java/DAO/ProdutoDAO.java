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
import model.Produto;
import util.Conexao;
/**
 *
 * @author usuario
 */
public class ProdutoDAO {
    
    public void cadastrar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("insert into produto (nome, descricao, preco, categoria, imagem) values (?, ?, ?, ?, ?)");
        comando.setString(1, p.getNome());
        comando.setString(2, p.getDescricao());
        comando.setDouble(3, p.getPreco());
        comando.setString(4, p.getCategoria());
        comando.setString(5, p.getImagem());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        String SQL = "update produto set nome = ?, descricao = ?, preco = ?, categoria = ?, imagem = ?, ativo = ? where id = ?";
        PreparedStatement comando = con.prepareStatement(SQL);
        comando.setString(1, p.getNome());
        comando.setString(2, p.getDescricao());
        comando.setDouble(3, p.getPreco());
        comando.setString(4, p.getCategoria());
        comando.setString(5, p.getImagem());
        comando.setInt(6, p.getAtivo());
        comando.setInt(7, p.getId());
        comando.execute();
        con.close();
    } 
    
    public Produto consultarbyID(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from produto WHERE id = ?");
        comando.setInt(1, p.getId());
        ResultSet rs = comando.executeQuery();
        
        Produto prod = new Produto();
        if (rs.next()){
            
            prod.setId(rs.getInt("id"));
            prod.setNome(rs.getString("nome"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setCategoria(rs.getString("categoria"));
            prod.setImagem(rs.getString("imagem"));
            prod.setAtivo(rs.getInt("ativo"));
        }        
        return prod;
    }
    
    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from produto");

        ResultSet rs = comando.executeQuery();
        
        List<Produto> listProd = new ArrayList<Produto>();
        while (rs.next()){
            Produto prod = new Produto();
            
            prod.setId(rs.getInt("id"));
            prod.setNome(rs.getString("nome"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setCategoria(rs.getString("categoria"));
            prod.setImagem(rs.getString("imagem"));
            prod.setAtivo(rs.getInt("ativo"));
            
            listProd.add(prod);
        }        
        return listProd;
    }
    
    public List<Produto> consultarByPesquisa(String pesquisa) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM produto WHERE categoria LIKE ? OR nome LIKE ? OR descricao LIKE ?");
        comando.setString(1, pesquisa);
        comando.setString(2, pesquisa);
        comando.setString(3, pesquisa);
        ResultSet rs = comando.executeQuery();
        
        List<Produto> listProd = new ArrayList<Produto>();
        while (rs.next()){
            Produto prod = new Produto();
            
            prod.setId(rs.getInt("id"));
            prod.setNome(rs.getString("nome"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setCategoria(rs.getString("categoria"));
            prod.setImagem(rs.getString("imagem"));
            prod.setAtivo(rs.getInt("ativo"));
            
            listProd.add(prod);
        }        
        return listProd;
    }
    
    public List<Produto> consultarByPesquisaAtivo(String pesquisa) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM produto WHERE (categoria LIKE ? OR nome LIKE ? OR descricao LIKE ?) AND ativo = 1");
        comando.setString(1, pesquisa);
        comando.setString(2, pesquisa);
        comando.setString(3, pesquisa);
        ResultSet rs = comando.executeQuery();
        
        List<Produto> listProd = new ArrayList<Produto>();
        while (rs.next()){
            Produto prod = new Produto();
            
            prod.setId(rs.getInt("id"));
            prod.setNome(rs.getString("nome"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setCategoria(rs.getString("categoria"));
            prod.setImagem(rs.getString("imagem"));
            prod.setAtivo(rs.getInt("ativo"));
            
            listProd.add(prod);
        }        
        return listProd;
    }
    
    public List<Produto> consultarCategoria(String categoria) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from produto WHERE categoria LIKE ? AND ativo = 1");
        comando.setString(1,categoria);
        ResultSet rs = comando.executeQuery();
        List<Produto> listProd = new ArrayList<Produto>();
        while (rs.next()){
            Produto prod = new Produto();
            prod.setId(rs.getInt("id"));
            prod.setNome(rs.getString("nome"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setCategoria(rs.getString("categoria"));
            prod.setImagem(rs.getString("imagem"));
            prod.setAtivo(rs.getInt("ativo"));
            
            listProd.add(prod);
        }        
        return listProd;
    }   
}

