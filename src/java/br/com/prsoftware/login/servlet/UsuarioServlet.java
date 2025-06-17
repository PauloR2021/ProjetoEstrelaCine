/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.login.servlet;
import br.com.prsoftware.dao.UsuarioDAO;
import br.com.prsoftware.model.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuario"})
public class UsuarioServlet extends HttpServlet {
     private final UsuarioDAO dao = new UsuarioDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String acao = request.getParameter("acao");
        
        
        
        try{
            
            if("cadastrar".equals(acao)){
                
                UsuarioModel u = new UsuarioModel();
                u.setNome(request.getParameter("nome"));
                u.setEmail(request.getParameter("email"));
                u.setSenha(request.getParameter("senha"));
                u.setAdmin(false);
                
                try {
                    dao.inserir(u);
                    response.sendRedirect("login.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Erro ao cadastrar: " + e.getMessage());
                }
                
                
            }else{
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");          

                UsuarioModel u = dao.autenticar(email, senha);
                if(u != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", u);

                    if(u.isAdmin()){
                        request.getRequestDispatcher("/WEB-INF/Admin/homeAdmin.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("/WEB-INF/Usuario/home.jsp").forward(request, response);
                      
                    }
                }else{
                  response.getWriter().println("Login inválido." );
                }

            }
            
        }catch (SQLException ex){
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao Cadastrar o Filme");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    
    
}
