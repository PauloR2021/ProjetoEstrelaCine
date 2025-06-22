/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Admin.servlet;


import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.dao.SessaoDAO;
import br.com.prsoftware.model.FilmeModel;
import br.com.prsoftware.model.SessaoModel;
import br.com.prsoftware.model.UsuarioModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;


/**
 *
 * @author Paulo
 */
@WebServlet(name = "SessaoAdminServlet", urlPatterns = {"/sessaoAdmin"})
public class SessaoAdminServlet extends HttpServlet {

    private final SessaoDAO dao = new SessaoDAO();
    private final FilmeDAO filme = new FilmeDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
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
            List<FilmeModel> lista = filme.listarFilmes();
            request.setAttribute("filmes", lista);
            request.getRequestDispatcher("/WEB-INF/Admin/sessaoAdmin.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }


    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            int idFilme = Integer.parseInt(request.getParameter("id")); 

            String dataStr = request.getParameter("data"); 
            String horaStr = request.getParameter("hora"); 
            Date data = Date.valueOf(dataStr);  
            Time hora = Time.valueOf(horaStr + ":00");
            
            String sala = request.getParameter("sala");
            int assentos = Integer.parseInt(request.getParameter("assentos"));

            SessaoModel sessao = new SessaoModel();
            sessao.setIdFilme(idFilme);
            sessao.setData(data); // ou Date.valueOf(data)
            sessao.setHora(hora); // ou Time.valueOf(hora + ":00")
            sessao.setSala(sala);
            sessao.setAssentos(assentos);

            dao.inserirSessao(sessao);

            response.sendRedirect("filmesAdmin");
        } catch (SQLException ex) {
            request.setAttribute("mensagemErro", "Erro ao Cadastrar o Sessao - Erro: "+ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}

