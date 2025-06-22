/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.dao.UsuarioDAO;
import br.com.prsoftware.model.UsuarioModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "CriarUsuario", urlPatterns = {"/criarUsuario"})
public class CriarUsuarioServlet extends HttpServlet {
    
    
    private UsuarioDAO dao = new UsuarioDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        //Iniciando Sessão de Login para não ter Como acessar a URL sem Login
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        request.setAttribute("usuario", session.getAttribute("usuario"));
        
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
  
        
        
        if (!usuario.isAdmin()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso não autorizado.");
            return;
        }
        // Encaminhar para o JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        try{
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String usuario  = request.getParameter("usuario");
            boolean tipoUser = false;
            
            tipoUser = !usuario.equals("0");
            
            UsuarioModel user = new UsuarioModel();
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            user.setAdmin(tipoUser);
           
            dao.inserir(user);
            
            response.sendRedirect("homeAdmin");
        }catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Não É Possviel Adicionar Usuário - Err: "+e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Não É Possviel Adicionar Usuário  - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

}
