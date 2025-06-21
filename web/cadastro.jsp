<%-- 
    Document   : cadastro
    Created on : 16 de jun. de 2025, 19:11:19
    Author     : Paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>

        <title>Estrela Cine - Cadastro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css-login/estilo_cadastro.css">
    </head>
    <body>
        <header>
            <div>
                <a href="login">Voltar</a>
            </div>
        </header>
        
        <h2>Criar Cadastro</h2>
        <form action="usuario" method="post">
            <input type="hidden" name="acao" value="cadastrar">
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required >

            <label for="senha">Senha</label>
            <input type="password" id="senha" name="senha" required autocomplete=off>

            <button type="submit">Cadastrar</button>

        </form>
        
    </body>
</html>
