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
<%
    br.com.prsoftware.model.UsuarioModel usuario = (br.com.prsoftware.model.UsuarioModel) session.getAttribute("usuario");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Estrela Cine - Reservas Admin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css-admin/estilo_reservas_admin.css">
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
                <a href="criarUsuario">Adicionar Usuário</a>
            </div>
            <div>
                <h3>Olá, ${usuario.nome}! 👋</h3>
            </div>
        </header>
        
        <div class="container">
        <h1>Todas as Reservas</h1>

        <% if (lista == null || lista.isEmpty()) { %>
            <p>Nenhuma Reservas no Momento.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>ID Reserva</th>
                        <th>ID Sessão</th>
                        <th>Nome Usuário</th>
                        <th>Assentos</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ReservasModel p : lista) { %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getId_sessao() %></td>
                        <td><%= p.getNome_usuario() %></td>
                        <td><%= p.getQtd_assentos() %></td>
                        <td><%= p.getStatus() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
        </div>
        
    </body>
</html>
