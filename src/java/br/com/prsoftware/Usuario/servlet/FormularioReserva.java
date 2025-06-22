/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.prsoftware.Usuario.servlet;


import br.com.prsoftware.dao.FilmeDAO;
import br.com.prsoftware.dao.ReservasDAO;
import br.com.prsoftware.model.FilmeModel;
import br.com.prsoftware.model.ReservasModel;
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
@WebServlet(name = "FormularioReserva", urlPatterns = {"/formularioReserva"})
public class FormularioReserva extends HttpServlet {
    
    private ReservasDAO dao = new ReservasDAO();
    private FilmeDAO filme = new FilmeDAO();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        
        String acao = request.getParameter("acao");

        
        if ("reservar".equals(acao)) {
            try {
                String idSessao = request.getParameter("id");
                int id = Integer.parseInt(idSessao);
                
                List<FilmeModel> lista = filme.listarFilmesId(id);
                request.setAttribute("filmes", lista);
                request.getRequestDispatcher("/WEB-INF/Usuario/formularioReserva.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("mensagemErro", "Não é Possivel Mostrar os Filmes - Por causa do Erro: "+ ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (ClassNotFoundException ex) {
                request.setAttribute("mensagemErro", "Erro ao carregar filmes - Erro: "+ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
         
        } else {
            request.setAttribute("mensagemErro", "Nenhum Filme Escolhido para a Reserva.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
           
        }

        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession(false); // false = não cria nova sessão
        
        if (session == null || session.getAttribute("usuario") == null) {
           response.sendRedirect("login.jsp");
           return;
        }
        
        try{
            
            String idFilme = request.getParameter("idFilme");
            if (idFilme != null) {
                
                Integer userId = (Integer) session.getAttribute("usuarioId");
                int idSesao = Integer.parseInt(request.getParameter("sessao"));
                String dataStr = request.getParameter("data"); 
                String horaStr = request.getParameter("hora"); 
                Date data = Date.valueOf(dataStr);  
                Time hora = Time.valueOf(horaStr + ":00");
            
                String sala = request.getParameter("sala");
                
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                String status = request.getParameter("status");
                
                ReservasModel reserva = new ReservasModel();
                
                reserva.setId_usuario(userId);
                reserva.setId_sessao(idSesao);
                reserva.setData(data);
                reserva.setHora(hora);
                reserva.setSala(sala);
                reserva.setQtd_assentos(quantidade);
                reserva.setStatus(status);
                
                dao.inserirReservas(reserva);
              
                request.getRequestDispatcher("/WEB-INF/Usuario/home.jsp").forward(request, response);
            } else {
               
                request.setAttribute("mensagemErro", "D da sessão não fornecido.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }catch (SQLException | ClassNotFoundException ex) {
           
            request.setAttribute("mensagemErro", "Não é Possivel Criar Reserva - Por causa do Erro: "+ ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagemErro", "Não é Possivel Criar Reserva - Por causa do Erro: "+ ex.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
       
        
    }
    
    
    

    

}
