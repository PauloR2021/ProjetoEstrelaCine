/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Usuario.servlet;


import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.model.FilmeModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Paulo
 */

@WebServlet(name = "FilmesServlet", urlPatterns = {"/filmes"})
public class FilmesServlet extends HttpServlet {

   private FilmeDAO dao = new FilmeDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        
        request.setAttribute("usuario", session.getAttribute("usuario"));
        
       
        
        try {
            List<FilmeModel> lista = dao.listarFilmes();
            request.setAttribute("filmes", lista);
            request.getRequestDispatcher("/WEB-INF/Usuario/filmes.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
    
    
}
