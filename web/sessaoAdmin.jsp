<%@page import="br.com.prsoftware.model.FilmesIdModel"%>
<%@page import="java.util.List"%>
<%
    List<FilmesIdModel> filmes = (List<FilmesIdModel>) request.getAttribute("filmesId");
    if(filmes == null){
      
    }
    
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
        <div class="nav-buttons">
            <a href="homeAdmin">Home</a>
            <a href="filmesAdmin">Cadastrar Filmes</a>
            <a href="reservasAdmin">Visualizar Todas as Reservas</a>
        </div>
    </header>

    <main>
        <div class="container-adicionar">
            <h1>Cadastro de Sessões</h1>
            <form action="adicionarSessoes" method="post">
                <label for="filme">Id Filme:</label>
                <select name="id">
                    <option value="">Selecione um Filme</option>
                    <% 
                        if(filmes != null){
                            for(FilmesIdModel filme : filmes){    
                    %> 
                        <option value="<%= filme.getId() %>"><%= filme.getTitulo() %></option>
                    <% 
                            } 
                        }else{
                    %>
                        <option value="">Nenhum filme disponível</option>
                    <%
                        }
                    %>
                </select>

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
