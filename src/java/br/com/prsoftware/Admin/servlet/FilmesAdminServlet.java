/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.model.FilmeModel;
import br.com.prsoftware.model.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "FilmesAdminServlet", urlPatterns = {"/filmesAdmin"})
public class FilmesAdminServlet extends HttpServlet{
    
    private FilmeDAO dao = new FilmeDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        if (!usuario.isAdmin()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso não autorizado.");
            return;
        }
        
        
        try {
            List<FilmeModel> lista = dao.listarFilmes();
            request.setAttribute("filmes", lista);
            request.getRequestDispatcher("/WEB-INF/Admin/filmesAdmin.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao carregar filmes.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        if (!usuario.isAdmin()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso não autorizado.");
            return;
        }
        try{
            String titulo = request.getParameter("titulo");
            int duracao = Integer.parseInt(request.getParameter("duracao"));
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            
            FilmeModel filme = new FilmeModel();
            
            filme.setTitulo(titulo);
            filme.setDuracao(duracao);
            filme.setGenero(genero);
            filme.setSinopse(sinopse);
            
            dao.inserirFilme(filme);
            
            response.sendRedirect("filmesAdmin");
        }catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Duração inválida. Insira um número.");
            request.getRequestDispatcher("/WEB-INF/Admin/filmesAdmin.jsp").forward(request, response);
        }catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao Cadastrar o Filme");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
