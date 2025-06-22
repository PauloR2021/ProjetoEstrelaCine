<%-- 
    Document   : cadastrarUsuario
    Created on : 21 de jun. de 2025, 19:27:57
    Author     : Paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    br.com.prsoftware.model.UsuarioModel usuario = (br.com.prsoftware.model.UsuarioModel) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Estrela Cine - Cadastrar Usuário</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css-admin/estilo_cadastro_usuario_admin.css">
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
            <div>
                <h3>Olá, ${usuario.nome}! 👋</h3>
            </div>
        </header>
        
        <main>
            <div class="container-adicionar">
                <h1>Adicionar Usuário</h1>
                <form action="criarUsuario" method="post">
                    
                  <label for="nome">Nome:</label>
                  <input type="text" id="nome" name="nome" required autocomplete="off">

                  <label for="email">Email:</label>
                  <input type="email" id="email" name="email" required autocomplete="off">

                  <label for="senha">Senha:</label>
                  <input type="password" id="senha" name="senha" required autocomplete="off">
                  
                  
                  <label for="usuario">Tipo Usuário:</label>
                  
                  <select name="usuario" id="usuario" >
                    <option value="0" id="usuario"> 0 - Usuário Comum</option>
                    <option value="1" id="usuario"> 1 - Usuário Adm</option>
                  </select>
     
                  <button type="submit">Adicionar</button>
                </form>
            </div>
        </main>
    </body>
</html>
