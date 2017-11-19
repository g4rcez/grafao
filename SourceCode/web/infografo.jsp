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
        <div class="row"></div>

        <div class="col-md-12 col-lg-12">
            <div class="container-fluid">
                <h2>Xml Gerado</h2>
                <code>
                    ${grafoVisual}
                </code>
            </div>
        </div>
        <div class="col-md-12 col-lg-12">
            <h1>Informações sobre o grafo ${nome}</h1>
            <ul>
                <li>Ordem do grafo: ${ordem}</li>
                <li>Tipo do grafo: ${tipo}</li>
                <li>Matriz de Incidência: 
                    <c:forEach items="${incidencia}" var="incidenciaInt">
                        ${incidenciaInt}
                    </c:forEach>
                </li>
                <li>Matriz de Adjacência: <c:forEach items="${adjacencia}" var="adjacenciaInt">
                        ${adjacenciaInt},
                    </c:forEach>
                </li>
            </ul>
        </div>
    </body>
</html>
