<%-- 
    Document   : index
    Created on : Sep 27, 2017, 9:10:55 AM
    Author     : grafao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="assets/bootstrap/beautify.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"> 
        <title>Grafão</title>
        <style>
            body{
                font-family: 'Nunito', sans-serif;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Criar um Grafo</h1>
            <p>
                Para criar um grafo, informe os nós da seguinte forma:<br>
                A-B, A-C<br>
                A liga com B, A liga com C.
            </p>

            <form method="POST" action="criar">
                <div class="form-group">
                    <label for="nome">Nome do Grafo:</label>
                    <input class="form-control" id="nome" name="nome">
                </div>
                <div class="form-group">
                    <label for="nos">Número de nós:</label>
                    <input type="number" class="form-control" id="nos" name="nos">
                </div>
                 <div class="form-group">
                     <label for="grafo">Estrutura do Grafo</label>
                     <textarea class="form-control" rows="5" id="grafo" name="grafo"></textarea>
                 </div> 
                <input type="submit" name="enviar" value="Gerar Grafo" />
            </form>
        </div>
    </body>
</html>
