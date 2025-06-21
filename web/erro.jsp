<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Estrela Cine - Erro</title>
    <link rel="stylesheet" href="css-login/estilo_erro.css">
    
</head>
<body>
    <div class="erro-container">
        <h2>Ocorreu um erro</h2>

        <div class="mensagem-erro">
            ${mensagemErro}
        </div>

        <div class="mensagem-padrao">
            Se o problema persistir, entre em contato com o suporte.
        </div>

        <a href="javascript:history.back()">← Voltar</a>
    </div>
</body>
</html>
