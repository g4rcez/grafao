<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo: ${grafo.id}</title>
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="${path}/assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <script src="${path}/assets/jquery.js" type="text/javascript"></script>
        <script src="${path}/assets/main.js"></script>

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
    </head>
    <body class="container-fluid">
        <h3>Grafo: <c:out value="${grafo.id}"></c:out> </h3>
            <p>Fecho transitivo direto:</p>
        <c:forEach items="${listaFConexa}" var="verticeConexo">
            ${verticeConexo},
        </c:forEach>
        <br />
        <p>Fecho transitivo inverso:</p>
        <c:forEach items="${listaTransitivaInversa}" var="vertice">
            ${vertice},
        </c:forEach>
        <br>
        <button id="reorganizar" class="btn btn-info" onclick="redraw();">Reorganizar</button><br>
        <div class="row">
            <div id="canvas"></div> 
        </div>
        <a href="${path}/view" class="btn btn-warning">Voltar</a> 
    </body>
</html>
