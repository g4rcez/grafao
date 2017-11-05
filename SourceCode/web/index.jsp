<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="${path}/assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
        <title>Grafão</title>
    </head>
    <body>
        <h1 class="text-center">${grafo}</h1>
        <div class="row">
            <div class="container">
                <div class="col-md-6 col-lg-6">
                    <h1 class="text-center">Criar um Grafo</h1>
                    <p>
                        Para criar um grafo, informe os nós da seguinte forma:
                    <ul>
                        <li>A-B, A-C</li>
                        <li>A liga com B, A liga com C.</li>
                    </ul>
                    </p>

                    <form method="POST" action="criar">
                        <div class="form-group">
                            <label for="nome">Nome do Grafo:</label>
                            <input class="form-control" id="nome" name="nome" placeholder="Nome do grafo">
                        </div>
                        <div class="form-group">
                            <label for="nos">Número de nós:</label>
                            <input type="number" class="form-control" id="nos" name="nos" placeholder="Número de nós que contém o grafo">
                        </div>
                        <div class="form-group">
                            <label for="grafo">Estrutura do Grafo</label>
                            <textarea class="form-control" rows="5" id="grafo" name="grafo" placeholder="Estrutura do grafo"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="path">Caminho para Download</label>
                            <input class="form-control" id="path" name="path-arquivo" placeholder="Caminho onde será salvo o arquivo..." />
                        </div>
                        <input type="submit" name="enviar" value="Gerar Grafo" class="button button-green" />
                    </form>
                </div>
                <div class="col-md-6 col-lg-6">
                    <h2>Enviar um grafo (Formato XML)</h2>
                    <form method="POST" action="upload" enctype = "multipart/form-data">
                        <div class="form-group">
                            <p>
                                Seu arquivo deve possuir extensão xml e estar no formato GraphXML
                                para a leitura correta. Exemplo do arquivo no formato xml:
                            </p>
                            <img class="img-responsive" src="${path}/assets/img.png" />
                            <label for="arquivo">Selecione um arquivo: </label>
                            <input type="file" id="arquivo" name="file" />
                        </div>
                        <input type="submit" name="enviar" value="Carregar Grafo" class="button button-blue" />
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
