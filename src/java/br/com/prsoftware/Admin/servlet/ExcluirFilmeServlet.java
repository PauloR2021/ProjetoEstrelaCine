/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.model.FilmeModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "ExcluirFilmeServlet", urlPatterns = {"/excluir"})
public class ExcluirFilmeServlet extends HttpServlet {
    
    private FilmeDAO dao = new FilmeDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String idParam = request.getParameter("id");
        
        try{
                        
            if(idParam != null){
                int id = Integer.parseInt(idParam);
                FilmeModel filme = new FilmeModel();
                
                filme.setId(id);
                
                dao.excluirFilme(filme);

                response.sendRedirect("homeAdmin");
            }
            else{
                request.setAttribute("mensagemErro", "Escolha Um Filme para Excluir");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
                
            }
            
        }catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Erro ao Excluir Filme - Erro: "+e.getMessage());
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
