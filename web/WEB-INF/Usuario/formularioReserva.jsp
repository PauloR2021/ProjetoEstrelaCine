<%-- 
    Document   : formularioReserva
    Created on : 20 de jun. de 2025, 21:31:19
    Author     : Paulo
--%>
<%@page import="br.com.prsoftware.model.FilmeModel"%>
<%@page import="java.util.List"%>
<%
    List<FilmeModel> lista = (List<FilmeModel>) request.getAttribute("filmes");
%>
<%
    br.com.prsoftware.model.UsuarioModel usuario = (br.com.prsoftware.model.UsuarioModel) session.getAttribute("usuario");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Estrela Cine - Formulário de Reservas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css-usuario/estilo_formulario_usuario.css">
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="filmes">Filmes</a>
                <a href="reservas">Minhas Reservas</a>
                <a href="sessao">Sessões</a>
            </div>
            <div>
                <h3>Olá, ${usuario.nome}! 👋</h3>
            </div>
        </header>
        
        <main>
            <div class="container">
                <h1>Sessões do Filme</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Nenhum Filme Cadastrado.</p>
                <% } else { %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Capa</th>
                                <th>Titulo</th>
                                <th>ID Sessão</th>
                                <th>Data</th>
                                <th>Horario</th>
                                <th>Sala</th>
                            </tr>
                        </thead>
                        <% for (FilmeModel p : lista) { %>
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
                                <td><%= p.getIdSesao()%></td>
                                <td><%= p.getData()%></td>
                                <td><%= p.getHora()%></td>
                                <td><%= p.getSala()%></td>
                                
                        </thead>

                        <% } %>
                    </table>
                <% } %>
            </div>
            
            
            <div class="formulario">
                <h1>Reserva Um Lugar</h1>
                <%
                    String idSessao = request.getParameter("id");
                %>
                <form action="formularioReserva" method="post">
                    
                    
                    <input type="hidden" name="idFilme" value="<%= idSessao %>">
                    
                    <label for="sessao">ID da Sessão:</label>
                    <input type="number" id="sessao" name="sessao" required autocomplete="off">
                   
                    <label for="data">Data da Sessão:</label>
                    <input type="date" id="data" name="data" required autocomplete="off">

                    <label for="hora">Horário da Sessão:</label>
                    <input type="time" id="hora" name="hora" required autocomplete="off">

                    <label for="sala">Sala da Sessão:</label>
                    <input type="text" id="sala" name="sala" required autocomplete="off">
                    
                    <label for="quantidade">Quantidade de Assentos:</label>
                    <input type="number" id="quantidade" name="quantidade" required autocomplete="off">

                    <select name="status" id="status" >
                        <option value="RESERVADO" id="status">RESERVADO</option>
                    </select>

                    <button type="submit">Reservar Lugares</button>
                </form>
                
            </div>
            
        </main>
       
    </body>
</html>
