/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.Produto;

/**
 *
 * @author usuario
 */
@WebServlet(name = "controle_pedido", urlPatterns = {"/controle_pedido"})
public class controle_pedido extends HttpServlet {

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
            
            PedidoDAO pedDao = new PedidoDAO();
            Pedido ped = new Pedido();
            
            ProdutoDAO pdao = new ProdutoDAO();
            Produto p = new Produto();
            
            if (op.equals("Cadastrar")) {
                
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                String cep = request.getParameter("cep");
                int numero = Integer.parseInt(request.getParameter("numero"));
                double frete = Double.parseDouble(request.getParameter("frete"));
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String telefone = request.getParameter("telefone");
                
                int idProd = Integer.parseInt(request.getParameter("idProd"));
                
                p.setId(idProd);
                
                ped.setQuantidade(quantidade);
                ped.setCep(cep);
                ped.setNumero(numero);
                ped.setFrete(frete);
                ped.setNome(nome);
                ped.setSobrenome(sobrenome);
                ped.setTelefone(telefone);
                
                ped.setStatus("Em Preparo");
                //Data inserida automaticamento no sql
                
                try {
                    p = pdao.consultarbyID(p);
                    ped.setProduto(p);

                    ped.calcular_Sub_total();
                    ped.calcular_total();
                    
                    System.out.println(ped.getTotal());

                    pedDao.efetuarPedido(ped);

                    response.sendRedirect("controle_pedido?op=exibirTodos");
                }catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }

            }else if (op.equals("exibirTodos")){
                try {
                    List<Pedido> lped = pedDao.consultarTodos();
                    request.setAttribute("lped", lped);
                    request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if (op.equals("exibirPedido")){
                String reqID = request.getParameter("id");
                int id = Integer.parseInt(reqID);
                
                ped.setId(id);
                
                try{
                    ped = pedDao.consultarById(ped);
                    request.setAttribute("pedido", ped);
                    request.getRequestDispatcher("editarPedido.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                
            }else if (op.equals("Atualizar")) {
                int idPedido = Integer.parseInt(request.getParameter("id"));
                String status = request.getParameter("status");
                
                ped.setId(idPedido);
                ped.setStatus(status);
                
                try {
                    pedDao.atualizarStatus(ped);
                    response.sendRedirect("controle_pedido?op=exibirTodos");
 
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op.equals("pesquisarPedido")) {
                String pesquisa = request.getParameter("pesquisa");
                
                try {
                    List<Pedido> lped = pedDao.pesquisarPor(pesquisa);
                    
                    request.setAttribute("lped", lped);
                    request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op.equals("exibirPendentes")) {
                try {
                    List<Pedido> lped = pedDao.exibirPendentes();
                    
                    request.setAttribute("lped", lped);
                    request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (op.equals("exibirConcluidos")) {
                try {
                    List<Pedido> lped = pedDao.exibirConcluidos();
                    
                    request.setAttribute("lped", lped);
                    request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
 
            
            /** TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controle_pedido</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controle_pedido at " + request.getContextPath() + "</h1>");
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
