/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.servlet;


import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.model.FilmesIdModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Paulo
 */
@WebServlet("/filmesId")
public class FilmesIdServlet extends HttpServlet {
    
    private FilmeDAO dao = new FilmeDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        try {
            List<FilmesIdModel> lista = dao.listarFilmesId();
            request.setAttribute("filmesId", lista);
            request.getRequestDispatcher("sessaoAdmin.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace(); // Mantém no log
            request.setAttribute("mensagemErro", "Erro ao Carregar ID dos Filmes");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
