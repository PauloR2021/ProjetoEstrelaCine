/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.dao.ReservasDAO;
import br.com.prsoftware.model.ReservasModel;
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
@WebServlet(name = "ReservasAdminServlet", urlPatterns = {"/reservasAdmin"})
public class ReservasAdminServlet extends HttpServlet{
    private ReservasDAO dao = new ReservasDAO();
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
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
             List<ReservasModel> lista = dao.listarReservas();
             request.setAttribute("reservas", lista);
             request.getRequestDispatcher("/WEB-INF/Admin/reservasAdmin.jsp").forward(request, response);
         } catch (SQLException ex) {
             ex.printStackTrace(); // Mantém no log
             request.setAttribute("mensagemErro", "Erro ao Carregar as Reservas");
             request.getRequestDispatcher("erro.jsp").forward(request, response);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(FilmesAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
