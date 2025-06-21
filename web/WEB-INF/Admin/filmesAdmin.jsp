<%-- 
    Document   : filmesAdmin
    Created on : 13 de jun. de 2025, 07:07:11
    Author     : Paulo
--%>

<%@page import="br.com.prsoftware.model.FilmeModel"%>
<%@page import="java.util.List"%>
<%
    List<FilmeModel> lista = (List<FilmeModel>) request.getAttribute("filmes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estrela Cine - Filmes Admin</title>
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
            </div>
        </header>
        
        <main>
            <div class="container">
                <h1>Filmes Cadastrados</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Nenhum Filme Cadastrado.</p>
                <% } else { %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Capa</th>
                                <th>Titulo</th>
                                <th>Duração - Minutos</th>
                                <th>Gênero</th>
                                <th>Sinopse</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% for (FilmeModel p : lista) { %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <!-- Coluna da capa -->
                                <td>
                                    <% if (p.getCapa() != null && !p.getCapa().isEmpty()) { %>
                                       <img src="<%= p.getCapa() %>" alt="Capa de <%= p.getTitulo() %>" style="max-height:100px;" />
                                    <% } else { %>
                                        Sem capa
                                    <% } %>
                                </td>
                                <td><%= p.getTitulo() %></td>
                                <td><%= p.getDuracao() %></td>
                                <td><%= p.getGenero() %></td>
                                <td><%= p.getSinopse() %></td>
                                
                                    <!-- Botão para editar -->
                                    <form action="editar" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= p.getId() %>">
                                        <button type="submit" class="btn-editar" onclick="return confirm('Deseja realmente editar esse filme?')">
                                            Editar
                                        </button>   
                                    </form>
                                    <!-- Botão para excluir -->
                                    <form action="excluir" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= p.getId() %>">
                                        <button type="submit" class="btn-excluir" onclick="return confirm('Deseja realmente excluir esse filme?')">
                                            Excluir
                                        </button>   
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            
            <div class="container-adicionar">
                <h1>Adicionar Filmes</h1>
                <form action="formularioReserva" method="post">
                    
                  <label for="titulo">Titulo do Filme:</label>
                  <input type="text" id="titulo" name="titulo" required autocomplete="off">

                  <label for="duracao">Duração em Minutos: </label>
                  <input type="number" id="duracao" name="duracao" required autocomplete="off">

                  <label for="genero">Gênero:</label>
                  <input type="text" id="genero" name="genero" required autocomplete="off">

                  <label for="sinopse">Sinopse:</label>
                  <textarea id="sinopse" name="sinopse" rows="5"></textarea>

                  <!-- Campo para upload da capa -->
                  <label for="capa">Capa do Filme:</label>
                  <input type="text" id="capa" name="capa" required autocomplete="off">
     
                  <button type="submit">Adicionar</button>
                </form>
            </div>
        </main>
    </body>
</html>
