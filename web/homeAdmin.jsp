<%-- 
    Document   : homeAdmin
    Created on : 13 de jun. de 2025, 18:45:32
    Author     : Paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Estrela Cine - Home Admin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <header class="cabecalho">
            <div class="nav-buttons">
                <a href="login">Login</a>
                <a href="filmesAdmin">Cadastrar Filmes</a>
                <a href="reservasAdmin">Visualizar Todas as Reservas</a>
                <a href="sessaoAdmin">Cadastrar Sessões</a>
            </div>
        </header>
        <main>
            <h2>Bem-vindo ao Estrela Cine</h2>
            <p>
                O <strong>Estrela Cine</strong> é um sistema web de reservas de ingressos para cinema, desenvolvido em Java com Servlets e banco de dados MySQL. 
                
            </p>
            <p>
                Com este sistema, os usuários podem criar uma conta, fazer login, visualizar os filmes em cartaz, escolher sessões e reservar seus assentos de forma rápida e segura.
            </p>
            <p>
                Administradores têm acesso exclusivo para gerenciar os filmes em exibição, cadastrar novas sessões e acompanhar as reservas realizadas.
            </p>
            <p>
                Este projeto foi desenvolvido como parte de um estudo prático em Java Web, com foco na aplicação dos conceitos de autenticação, MVC, controle de sessões e integração com banco de dados.
            </p>
        </main>
        <hr>
        <footer style="text-align: center; font-size: 14px; color: #777; margin-top: 20px;">
            <p>&copy; 2025 Estrela Cine. Todos os direitos reservados.</p>
            <p>Desenvolvido por Paulo Ricardo com Java, Servlets e MySQL.</p>
        </footer>
    </body>
</html>
