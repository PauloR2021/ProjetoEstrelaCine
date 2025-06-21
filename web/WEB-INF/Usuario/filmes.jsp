<%-- 
    Document   : filmes
    Created on : 17 de jun. de 2025, 07:21:17
    Author     : Paulo
--%>

<%@page import="br.com.prsoftware.model.FilmeModel"%>
<%@page import="java.util.List"%>
<%
    List<FilmeModel> lista = (List<FilmeModel>) request.getAttribute("filmes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estrela Cine - Filmes</title>
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-button-logout"> 
                <a href="logout">Logout</a>
            </div>
            <div class="nav-buttons">
                <a href="home">Home</a>
                <a href="reservas">Visualizar Todas as Reservas</a>
                <a href="sessao">Sessão</a>
            </div>
        </header>
        
        <main>
            <div class="container">
                <h1>Filmes</h1>

                <% if (lista == null || lista.isEmpty()) { %>
                    <p>Nenhum Filme Cadastrado.</p>
                <% } else { %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Capa</th>
                                <th>Titulo</th>
                                <th>Duração - Minutos</th>
                                <th>Gênero</th>
                                <th>Sinopse</th>
                                <th>Sessão</th>
                                <th>Reservas</th>
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
                                <td><%= p.getTitulo() %></td>
                                <td><%= p.getDuracao()%></td>
                                <td><%= p.getGenero()%></td>
                                <td><%= p.getSinopse()%></td>
                                <td>
                                    <!-- Criando o Button para Verificar Sessão -->
                                    <form action="sessao" method="get" style="display:inline;">
                                        <input type="hidden" name="acao" value="consultar">
                                        <input type="hidden" name="id" value="<%=p.getId() %>">
                                        <button type="submit" class="btn-sessao" >
                                            <i class="fas fa-check-circle"></i> Verificar Sessão
                                        </button>   
                                    </form>
                                </td>

                                <td>
                                    <!-- Criando o Button para Criar Reserva -->
                                    <form action="formularioReserva" method="get" style="display:inline;">
                                        <input type="hidden" name="acao" value="reservar">
                                        <input type="hidden" name="id" value="<%=p.getId() %>">
                                        <button type="submit" class="btn-reserva" >
                                          <i class="fas fa-times-circle"></i> Reservar Lugar
                                        </button>   
                                    </form>

                                </td> 
                            </tr>
                        </thead>

                        <% } %>
                    </table>
                <% } %>
            </div>
        </main>
            
    </body>
</html>
