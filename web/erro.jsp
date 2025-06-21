<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Estrela Cine - Erro</title>
    <style>
        body {
            background-color: #fef2f2;
            font-family: Arial, sans-serif;
            color: #8b0000;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .erro-container {
            background-color: #fff0f0;
            border: 1px solid #ffcccc;
            padding: 30px;
            border-radius: 12px;
            max-width: 500px;
            text-align: center;
            box-shadow: 0 4px 10px rgba(255, 0, 0, 0.1);
        }

        h2 {
            color: #cc0000;
            margin-bottom: 20px;
        }

        .mensagem-erro {
            font-size: 16px;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .mensagem-padrao {
            font-size: 14px;
            color: #a94442;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #cc0000;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
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
