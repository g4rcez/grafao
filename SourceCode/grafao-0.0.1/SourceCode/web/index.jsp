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
                <div class="form-group">
                     <label for="path">Caminho para Download</label>
                     <input class="form-control" id="path" name="path-arquivo" />
                 </div>
                <input type="submit" name="enviar" value="Gerar Grafo" class="button button-green"/>
            </form>
            <div class="row">
                <h2>Enviar um grafo (Formato XML)</h2>
                <form method="POST" action="UploadServlet" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="arquivo">Selecione um arquivo: </label>
                        <input type="file" id="arquivo" name="grafo"/>
                    </div>
                    <input type="submit" name="enviar" value="Carregar Grafo" class="button button-blue"/>
                </form>
            </div>
        </div>
    </body>
</html>
