/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.model.FilmeModel;
import br.com.prsoftware.model.UsuarioModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "EditarFilmesServlet", urlPatterns = {"/editar"})
public class EditarFilmesServlet extends HttpServlet {

    private FilmeDAO dao = new FilmeDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
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
        
        try {
            String idParam = request.getParameter("id");
            if(idParam!=null){
                int id = Integer.parseInt(idParam);
                
                List<FilmeModel> lista = dao.listarFilmesEditarId(id);
                request.setAttribute("filmes", lista);
                request.getRequestDispatcher("/WEB-INF/Admin/editarFilme.jsp").forward(request, response);
                
            }
            else{
                request.setAttribute("mensagemErro", "Escolha Um Filme para Editar");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro : "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String idParam = request.getParameter("idFilme");
        
        try{
                        
            if(idParam != null){
                int id = Integer.parseInt(idParam);
            
                String titulo = request.getParameter("titulo");
                int duracao = Integer.parseInt(request.getParameter("duracao"));
                String genero = request.getParameter("genero");
                String sinopse = request.getParameter("sinopse");
                String capa = request.getParameter("capa");

                FilmeModel filme = new FilmeModel();

                filme.setId(id);
                filme.setTitulo(titulo);
                filme.setDuracao(duracao);
                filme.setGenero(genero);
                filme.setSinopse(sinopse);
                filme.setCapa(capa);

                dao.editarFilme(filme);

                response.sendRedirect("homeAdmin");
            }
            else{
                request.setAttribute("mensagemErro", "Escolha Um Filme para Editar");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
                
            }
            
        }catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Erro ao Editar Filme - Erro: "+e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao Editar o Filme - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

}
