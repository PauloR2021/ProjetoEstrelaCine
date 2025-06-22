/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;

import br.com.prsoftware.model.UsuarioModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "HomeAdmin", urlPatterns = {"/homeAdmin"})
public class HomeAdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/homeAdmin.jsp");
        dispatcher.forward(request, response);
        
    }
}
