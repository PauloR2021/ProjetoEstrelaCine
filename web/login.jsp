<%-- 
    Document   : login
    Created on : 13 de jun. de 2025, 18:36:45
    Author     : Paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Estrela Cine - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-buttons">
                <a href="index.jsp">Início</a> 
            </div>
        </header>
        <div class="container">
            <h2>Login</h2>
            <form action="usuario" method="post">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required autocomplete=off>
                
                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha" required autocomplete=off>
                
                <button type="submit">Entrar</button>
                
            </form>
            <div>
                <a href="cadastro">Criar Conta</a>
            </div>
            
        </div>
        
    </body>
</html>

