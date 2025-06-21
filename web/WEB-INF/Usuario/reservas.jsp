<%-- 
    Document   : reservas
    Created on : 17 de jun. de 2025, 07:21:35
    Author     : Paulo
--%>
<%@page import="br.com.prsoftware.model.TodasReservasDoUsuario"%>
<%@page import="java.util.List"%>
<%
    List<TodasReservasDoUsuario> lista = (List<TodasReservasDoUsuario>) request.getAttribute("reservas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estrela Cine - Minhas Reservas</title>
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="home">Home</a>
                <a href="filmes">Filmes</a>
                <a href="sessao">Sessão</a>
            </div>
        </header>
        
        <main>
            <div class="container">
                <h1>Minhas Reservas</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Sem Reservas.</p>
                <% } else { %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Capa</th>
                                <th>Titulo</th>
                                <th>Nome</th>
                                <th>Data</th>
                                <th>Horário</th>
                                <th>Sala</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <% for (TodasReservasDoUsuario p : lista) { %>
                        <thead>
                            <tr>
                                <!-- Coluna da capa -->
                                <td>
                                    <% if (p.getCapa() != null && !p.getCapa().isEmpty()) { %>
                                       <img src="<%= p.getCapa() %>" alt="Capa de <%= p.getTitulo() %>" style="max-height:100px;" />
                                    <% } else { %>
                                        Sem capa
                                    <% } %>
                                </td>
                               
                                <td><%= p.getTitulo()%></td>
                                <td><%= p.getNome() %></td>
                                <td><%= p.getData()%></td>
                                <td><%= p.getHora()%></td>
                                <td><%= p.getSala()%></td>
                                <td><%= p.getStatus()%></td>
                            </tr>
                                
                               
                        </thead>

                        <% } %>
                    </table>
                <% } %>
            </div>
    </body>
</html>
