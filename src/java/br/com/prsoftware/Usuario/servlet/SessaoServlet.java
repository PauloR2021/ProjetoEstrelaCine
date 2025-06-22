/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Usuario.servlet;

import br.com.prsoftware.dao.SessaoDAO;
import br.com.prsoftware.model.SessaoModel;
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
@WebServlet(name = "SessaoServlet", urlPatterns = {"/sessao"})
public class SessaoServlet extends HttpServlet {
    
    private SessaoDAO dao = new SessaoDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("usuarioId");
        String acao = request.getParameter("acao");
        request.setAttribute("usuario", session.getAttribute("usuario"));

        if (userId != null) {
            try {
                if ("consultar".equals(acao)) {
                    // Buscar sessão por ID
                    String idParam = request.getParameter("id");
                    if (idParam != null) {
                        int id = Integer.parseInt(idParam);
                        List<SessaoModel> lista = dao.listarSessaoId(id);
                        request.setAttribute("sessao", lista);
                        request.getRequestDispatcher("/WEB-INF/Usuario/sessao.jsp").forward(request, response);
                    } else {
                        response.getWriter().println("ID da sessão não fornecido.");
                    }

                } else {
                    // Listar todas as sessões
                    List<SessaoModel> lista = dao.listarSessao();
                    request.setAttribute("sessao", lista);
                    request.getRequestDispatcher("/WEB-INF/Usuario/sessao.jsp").forward(request, response);
                }

            } catch (SQLException | ClassNotFoundException ex) {
             
                request.setAttribute("mensagemErro", "Erro ao carregar Sessão- Erro: "+ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
    }

}
