<%-- 
    Document   : editarFilme
    Created on : 21 de jun. de 2025, 21:24:21
    Author     : Paulo
--%>

<%@page import="br.com.prsoftware.model.FilmeModel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<FilmeModel> lista = (List<FilmeModel>) request.getAttribute("filmes");
%>
<%
    br.com.prsoftware.model.UsuarioModel usuario = (br.com.prsoftware.model.UsuarioModel) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Estrela Cine - Editar Filme</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css-admin/estilo_editar_filmes_admin.css">
    </head>
    <body>
        
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="homeAdmin">Home</a>
                <a href="reservasAdmin">Visualizar Todas as Reservas</a>
                <a href="sessaoAdmin">Cadastrar Sessões</a>
                <a href="criarUsuario">Adicionar Usuário</a>
            </div>
            <div>
                <h3>Olá, ${usuario.nome}! 👋</h3>
            </div>
           
        </header>
            
        <main>
            <div class="container-editar">
                <% for (FilmeModel p : lista) { %>
                    <h1>Editar Filme</h1>
                    
                    <%
                    String idFilme = request.getParameter("id");
                    %>
                    <form action="editar" method="post">
                        
                      <input type="hidden" name="idFilme" value="<%= idFilme %>">

                      <label for="titulo">Titulo do Filme:</label>
                      <input type="text" id="titulo" name="titulo" value="<%= p.getTitulo()%>" required autocomplete="off">

                      <label for="duracao">Duração em Minutos: </label>
                      <input type="number" id="duracao" name="duracao"  value="<%= p.getDuracao() %>" required autocomplete="off" >

                      <label for="genero">Gênero:</label>
                      <input type="text" id="genero" name="genero" value="<%= p.getGenero() %>"required autocomplete="off" >

                      <label for="sinopse">Sinopse:</label>
                      <textarea id="sinopse" name="sinopse" rows="5" ><%= p.getSinopse() %></textarea>

                      <!-- Campo para upload da capa -->
                      <label for="capa">Capa do Filme:</label>
                      <input type="text" id="capa" name="capa" required autocomplete="off" value="<%= p.getCapa() %>">

                      <button type="submit">Editar</button>
                    </form>
                <% } %>
            </div>

        </main>
    </body>
</html>
