<%-- 
    Document   : sessao
    Created on : 17 de jun. de 2025, 18:27:59
    Author     : Paulo
--%>

<%@page import="br.com.prsoftware.model.SessaoModel"%>
<%@page import="br.com.prsoftware.model.FilmeModel"%>
<%@page import="java.util.List"%>
<%
    List<SessaoModel> lista = (List<SessaoModel>) request.getAttribute("sessao");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estrela Cine - Minha Sessões</title>
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="home">Home</a>
                 <a href="filmes">Filmes</a>
                <a href="reservas">Visualizar Todas as Reservas</a>  
            </div>
        </header>
           
           
        <main>
            <div class="container">
                <h1>Sessões</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Nenhuma Sessão Para Esse Filme.</p>
                <% } else { %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Capa</th>
                                <th>Titulo</th>
                                <th>Genero</th>
                                <th>Data</th>
                                <th>Horario</th>
                                <th>Sala</th>
                                <th>Assentos</th>
                            </tr>
                        </thead>
                        <% for (SessaoModel p : lista) { %>
                        <thead>
                            <tr>
                                <td>
                                    <% if (p.getCapa() != null && !p.getCapa().isEmpty()) { %>
                                       <img src="<%= p.getCapa() %>" alt="Capa de <%= p.getTitulo() %>" style="max-height:100px;" />
                                    <% } else { %>
                                        Sem capa
                                    <% } %>
                                </td>

                                <td><%= p.getTitulo()%></td>
                                <td><%= p.getGenero()%></td>
                                <td><%= p.getData()%></td>
                                <td><%= p.getHora()%></td>
                                <td><%= p.getSala()%></td>
                                <td><%= p.getAssentos()%></td>
                                
                            </tr>
                        </thead>

                        <% } %>
                    </table>
                <% } %>
            </div>
        </main>
        
    </body>
</html>
