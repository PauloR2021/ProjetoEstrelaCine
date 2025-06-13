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
            <div class="nav-buttons">
                <a href="homeAdmin.jsp">Voltar</a>
                <a href="reservasAdmin">Visualizar Todas as Reservas</a>
                <a href="sessoesAdmin">Cadastrar Sessões</a>
            </div>
        </header>
        
        <main>
            <div class="container">
                <h1>Filmes Cadastrados</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Nenhum Filme Cadastrado.</p>
                <% } else { %>
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Titulo</th>
                            <th>Duração - Minutos</th>
                            <th>Gênero</th>
                            <th>Sinopse</th>
                        </tr>
                        <% for (FilmeModel p : lista) { %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <td><%= p.getTitulo() %></td>
                                <td><%= p.getDuracao()%></td>
                                <td><%= p.getGenero()%></td>
                                <td><%= p.getSinopse()%></td>
                                <td>
                                    <!-- Criando o Button para Editar o Filme -->
                                    <form action="editar" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%=p.getId() %>">
                                        <button type="submit" class="btn-editar" onclick="return confirm('Deseja realmente Editar esse Filme?')">
                                            <i class="fas fa-check-circle"></i> DEVOLVER
                                        </button>   
                                    </form>
                                </td>

                                <td>
                                    <!-- Criando o Button para Excluir o Filme -->
                                    <form action="excluir" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%=p.getId() %>">
                                        <button type="submit" class="btn-excluir" onclick="return confirm('Deseja realmente Excluir o Filme?')">
                                          <i class="fas fa-times-circle"></i> CANCELAR
                                        </button>   
                                    </form>

                                </td> 
                            </tr>

                        <% } %>
                    </table>
                <% } %>
            </div>
            
            <div class="container-adicionar">
                <h1>Adicionar Filmes</h1>
                <form action="adicionarFilmes" method="post">
                  <label for="titulo">Titulo do Filme:</label>
                  <input type="text" id="titulo" name="titulo" required autocomplete="off">

                  <label for="duracao">Duração em Minutos: </label>
                  <input type="number" id="duracao" name="duracao" required autocomplete="off">

                  <label for="genero">Gênero:</label>
                  <input type="text" id="genero" name="genero" required autocomplete="off">

                  <label for="sinopse">Sinopse:</label>
                  <textarea id="sinopse" name="sinopse" rows="5"></textarea>
     
                  <button type="submit">Adicionar</button>
                </form>
            </div>
        </main>
            
            
     
    </body>
</html>
