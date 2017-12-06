<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Grafo</title>
        <link rel="stylesheet" href="css/style.css">
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
                g.addEdge("<c:out value="${aresta.origem.id}"></c:out>", "<c:out value="${aresta.destino.id}"></c:out>", {label: "<c:out value="${aresta.valor}"></c:out>",'label-style' : {'font-size': 17, 'font-weight': 'bolder'}});
            </c:forEach>
                        //var layouter = new Graph.Layout.Ordered(g, topologicalSort(g)); //OUTRA FORMA DE ORGANIZAR O GRAFO
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
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out>
            <p>
                Reorganizar o Grafo: <button id="reorganizar" class="corBotao" onclick="redraw();">Reorganizar</button>
                Editar o Grafo: <a href="editarGrafo.jsp" class="corBotao botoes">Editar</a>
            </p>
            <p><a href="/infografo" class="bnt">Voltar</a></p>
            <div id="canvas"></div> 
            <br />

    </body>
</html>