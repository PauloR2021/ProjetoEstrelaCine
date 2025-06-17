<%-- 
    Document   : reservasAdmin
    Created on : 16 de jun. de 2025, 18:20:01
    Author     : Paulo
--%>


<%@page import="br.com.prsoftware.model.ReservasModel"%>
<%@page import="java.util.List"%>
<%
    List<ReservasModel> lista = (List<ReservasModel>) request.getAttribute("reservas");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estrela Cine - Reservas Admin</title>
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="homeAdmin">Home</a>
                <a href="filmesAdmin">Cadastrar Filmes</a>
                <a href="sessaoAdmin">Cadastrar Sessões</a>
            </div>
        </header>
        
        <div class="container">
        <h1>Todas as Reservas</h1>

        <% if (lista == null || lista.isEmpty()) { %>
            <p>Nenhuma Reservas no Momento.</p>
        <% } else { %>
            <table border="1">
                <th>
                    <tr>
                        <th>ID Reserva</th>
                        <th>ID Sessão</th>
                        <th>Nome Usuário</th>
                        <th>Assentos</th>
                        <th>Status</th>
                    </tr>
                </th>
                <% for (ReservasModel p : lista) { %>
                <th>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getId_sessao() %></td>
                        <td><%= p.getNome_usuario()%></td>
                        <td><%= p.getQtd_assentos()%></td>
                        <td><%= p.getStatus()%></td>

                    </tr>
                </th>
                    
                <% } %>
            </table>
            <% } %>
        </div>
        
    </body>
</html>
