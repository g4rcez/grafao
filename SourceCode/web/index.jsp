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
        <title>Grafão</title>
    </head>
    <body>
        <h1>Criar um Grafo</h1>
        <p>
            Para criar um grafo, informe os nós da seguinte forma:<br>
            A-B, A-C<br>
            A liga com B, A liga com C.
        </p>
        
        <form method="POST">
            <textarea name="mapaGrafos"></textarea>
            <input type="submit" name="enviar" value="Gerar Grafo" />
        </form>
    </body>
</html>
