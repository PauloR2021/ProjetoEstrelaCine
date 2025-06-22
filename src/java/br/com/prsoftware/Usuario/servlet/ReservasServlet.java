/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Usuario.servlet;

import br.com.prsoftware.dao.ReservasDAO;
import br.com.prsoftware.model.TodasReservasDoUsuario;
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
@WebServlet(name = "ReservasServlet", urlPatterns = {"/reservas"})
public class ReservasServlet extends HttpServlet {

    
    private ReservasDAO dao = new ReservasDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
         
        Integer userId = (Integer) session.getAttribute("usuarioId");
        request.setAttribute("usuario", session.getAttribute("usuario"));

        
        if (userId != null) {
            try {
                
                List<TodasReservasDoUsuario> lista = dao.listarTodasReservasDoUsuario(userId);
                request.setAttribute("reservas", lista);
                request.getRequestDispatcher("/WEB-INF/Usuario/reservas.jsp").forward(request, response);
             

            } catch (SQLException | ClassNotFoundException ex) {
            
                request.setAttribute("mensagemErro", "Erro ao carregar Sessão.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
        
       
        
        
    }
}
