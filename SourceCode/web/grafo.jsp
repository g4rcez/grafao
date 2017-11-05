<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
        <title>Grafão</title>
    </head>

    <body>
        <div class="row">
            <div class="text-center">
                <h2>Operações</h2>
                <div class="btn-group">
                    <a class="btn btn-primary" data-toggle="collapse" data-target="#adicionarAresta">Adicionar Aresta</a>
                    <a class="btn btn-primary" data-toggle="collapse" data-target="#removerAresta">Remover Aresta</a>
                    <a class="btn btn-success" data-toggle="collapse" data-target="#adicionarNo">Adicionar Nó</a>
                    <a class="btn btn-success" data-toggle="collapse" data-target="#removerNo">Remover Nó</a>
                </div>
                <div class="row">
                    <div id="adicionarAresta" class="collapse">
                        <h2>Form pra adicionar aresta</h2>
                    </div>
                    <div id="removerAresta" class="collapse">
                        <h2>Form pra adicionar aresta</h2>
                    </div>
                    <div id="adicionarNo" class="collapse">
                        <h2>Form pra adicionar aresta</h2>
                    </div>
                    <div id="removerNo" class="collapse">
                        <h2>Form pra adicionar aresta</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <div class="container">
                    <c:if test="${tipo == 'download'}">
                        <h2>${sucesso}</h2>
                        <a href="${download}" class="btn btn-primary">
                            <i class="fa fa-download"> Download</i>
                        </a>
                        <p>Caso não consiga baixar, acesse este link:
                            <strong>${download}</strong>
                        </p>
                    </c:if>
                    <c:if test="${tipo == 'upload'}">
                        <h2>${sucesso}</h2>
                        <a href="${pathFile}" class="btn btn-primary">
                            <i class="fa fa-download"> Download</i>
                        </a>
                        <p>Caso não consiga baixar, acesse este link:
                            <strong style="word-break: break-all;word-wrap: break-word">${pathFile}</strong>
                        </p>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6 col-lg-6">
                <div class="container">
                    <h2>Xml Gerado</h2>
                    <code>
                        ${grafoVisual}
                    </code>
                </div>
            </div>
        </div>
    </body>

</html>