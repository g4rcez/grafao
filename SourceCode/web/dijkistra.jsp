<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="${path}/assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo</title>
        <script type="text/javascript" src="${path}/assets/vendor/raphael.js"></script>
        <script type="text/javascript" src="${path}/assets/vendor/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="${path}/assets/lib/dracula_graffle.js"></script>
        <script type="text/javascript" src="${path}/assets/lib/dracula_graph.js"></script>
        <script type="text/javascript" src="${path}/assets/lib/dracula_algorithms.js"></script>
        <script type="text/javascript">
            var redraw;
            window.onload = function () {
                var width = $(document).width() - 100;
                var height = $(document).height() - 200;
                var g = new Graph();
                Dracula.Edge.style.directed =<c:choose>
                <c:when test="${grafo.tipo == 'directed'}">true</c:when>
                <c:otherwise>false</c:otherwise>
            </c:choose>
            <c:forEach items="${grafo.nos}" var="no">
                        g.addNode("<c:out value="${no.id}"></c:out>");
            </c:forEach>
            <c:forEach items="${grafo.arestas}" var = "aresta"> //nao funciona para nos isolados, tente adicionar tds os nos e depois criar as arestas
                        g.addEdge("<c:out value="${aresta.origem.id}"></c:out>", "<c:out value="${aresta.destino.id}"></c:out>", {label: "<c:out value="${aresta.valor}"></c:out>", 'label-style': {'font-size': 17, 'font-weight': 'bolder'}});
            </c:forEach>
                        var layouter = new Graph.Layout.Spring(g);
                        var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
                        var layouter = new Graph.Layout.Spring(g);
                        layouter.layout();
                        renderer.draw();
                        redraw = function () {
                            layouter.layout();
                            renderer.draw();
                        };
                    };
        </script>
        <style>
            body{
                overflow-x: hidden;
            }
        </style>
    </head>
    <body class="container-fluid">
        <h1>Grafo: ${grafo.id}</h1>
        <div class="row">
            <c:choose>
                <c:when test="${not empty caminhosDaOrigem}">
                    <p>
                        Caminhos do nó ${nomeNoOrigem} para todos os outros:
                    </p>
                    <ul>
                        <c:forEach items="${caminhosDaOrigem}" var="caminho">
                            <li>
                                <c:forEach items="${caminho}" var="no">
                                    ${no.id} - 
                                </c:forEach>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    Caminho do nó ${nomeNoOrigem} para o nó ${noDestino}
                    <ul><li>
                            <c:forEach items="${caminho}" var="no">
                                ${no.id} - 
                            </c:forEach>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
            <button id="reorganizar" class="btn btn-info" onclick="redraw();">Reorganizar</button>
        </div>
        <div class="row">
            <div id="canvas"></div> 
        </div>
        <a href="${path}/view" class="btn btn-warning">Voltar</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </body>
</html>
