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
    <title>Estrela Cine - Sessões Admin</title>
</head>
<body>
    <header class="cabecalho">
        <div class="nav-button-logout"> 
            <a href="logout">Logout</a>
        </div>
        <div class="nav-buttons">
            <a href="homeAdmin">Home</a>
            <a href="filmesAdmin">Cadastrar Filmes</a>
            <a href="reservasAdmin">Visualizar Todas as Reservas</a>
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
                                <th>Capa</th>
                                <th>ID</th>
                                <th>Titulo</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% for (FilmeModel p : lista) { %>
                            <tr>
                                <!-- Coluna da capa -->
                                <td>
                                    <% if (p.getCapa() != null && !p.getCapa().isEmpty()) { %>
                                       <img src="<%= p.getCapa() %>" alt="Capa de <%= p.getTitulo() %>" style="max-height:100px;" />
                                    <% } else { %>
                                        Sem capa
                                    <% } %>
                                </td>
                                <td><%= p.getId() %></td>
                                <td><%= p.getTitulo() %></td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
        
        
        
        
        
        <div class="container-adicionar">
            <h1>Cadastro de Sessões</h1>
            <form action="sessaoAdmin" method="post">
                <label for="filme">Id Filme:</label>
                <input type="number" id="id" name="id" required autocomplete="off">

                <label for="data">Data da Sessão:</label>
                <input type="date" id="data" name="data" required autocomplete="off">

                <label for="hora">Horário da Sessão:</label>
                <input type="time" id="hora" name="hora" required autocomplete="off">

                <label for="sala">Sala da Sessão:</label>
                <input type="text" id="sala" name="sala" required autocomplete="off">

                <label for="assentos">Assentos Disponíveis:</label>
                <input type="number" id="assentos" name="assentos" required autocomplete="off">

                <br><br>
                <button type="submit">Cadastrar Sessão</button>
            </form>
        </div>
    </main>
</body>
</html>
