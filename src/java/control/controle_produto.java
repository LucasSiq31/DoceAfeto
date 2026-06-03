/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author usuario
 */
@WebServlet(name = "controle_produto", urlPatterns = {"/controle_produto"})
public class controle_produto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String op = request.getParameter("op");
            ProdutoDAO pdao = new ProdutoDAO();
            Produto p = new Produto();
            
            if (op.equals("Cadastrar")) {
                //Puxando dados do HTML
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                double preco = Double.parseDouble(request.getParameter("preco"));
                String categoria = request.getParameter("categoria");
                String imagem = request.getParameter("imagem");
                
                //Criando objeto com os dados
                p.setNome(nome);
                p.setDescricao(descricao);
                p.setPreco(preco);
                p.setCategoria(categoria);
                p.setImagem(imagem);
              
                try {
                    pdao.cadastrar(p);
                    response.sendRedirect("controle_produto?op=exibirTodos");
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if(op.equals("exibirTodos")){
        
                try {
                    List<Produto> lprod = pdao.consultarTodos();
                    request.setAttribute("lprod", lprod);
                    request.getRequestDispatcher("produtos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            } else if(op.equals("pesquisarProduto")){
                String pesquisa = request.getParameter("pesquisa");
                
                try {
                    List<Produto> lprod = pdao.consultarByPesquisaAtivo("%" + pesquisa + "%");
                    
                    request.setAttribute("lprod", lprod);
                    request.setAttribute("pesquisa", pesquisa);
                    request.getRequestDispatcher("buscaProduto.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            } else if(op.equals("pesquisarProdutoAdmin")){
                String pesquisa = request.getParameter("pesquisa");
                
                try {                   
                    List<Produto> lprod = pdao.consultarByPesquisa("%" + pesquisa + "%");
                    request.setAttribute("lprod", lprod);
                    request.getRequestDispatcher("produtos.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if(op.equals("exibirProduto")){
                String reqID = request.getParameter("id");
                int id = Integer.parseInt(reqID);
                
                p.setId(id);
                
                try {                   
                    p = pdao.consultarbyID(p);
                    request.setAttribute("produto", p);
                    System.out.println(p.getNome());
                    request.getRequestDispatcher("efetuarPedido.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if(op.equals("Atualizar")){
                String reqID = request.getParameter("idInput");
                int id = Integer.parseInt(reqID);
                
                String nome = request.getParameter("nomeInput");
                String descricao = request.getParameter("descricaoInput");
                double preco = Double.parseDouble(request.getParameter("precoInput"));
                String categoria = request.getParameter("categoriaInput");
                String imagem = request.getParameter("imagemInput");
                int ativo = Integer.parseInt(request.getParameter("ativo"));
                
                p.setId(id);
                p.setNome(nome);
                p.setDescricao(descricao);
                p.setPreco(preco);
                p.setCategoria(categoria);
                p.setImagem(imagem);
                p.setAtivo(ativo);

                try {                   
                    pdao.atualizar(p);
                    response.sendRedirect("controle_produto?op=exibirTodos");
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if(op.equals("home")){
                try {
                    List<Produto> ldoce = pdao.consultarCategoria("%Doce%");
                    request.setAttribute("ldoce", ldoce);
 
                    List<Produto> ltorta = pdao.consultarCategoria("%Torta%");
                    request.setAttribute("ltorta", ltorta);
                    
                    List<Produto> lbolo = pdao.consultarCategoria("%Bolo%");
                    request.setAttribute("lbolo", lbolo);

                    request.getRequestDispatcher("home.jsp").forward(request, response);

                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp")
                           .forward(request, response);
                }
            }
            
            /* TODO output your page here. You may use following sample code. */
            /**out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controle_produto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controle_produto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");**/
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
