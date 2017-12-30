<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${grafo.id}</title>
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="${path}/assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <script src="${path}/assets/jquery.js" type="text/javascript"></script>
        <script src="${path}/assets/main.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <h2 class="text-left">Grafo: ${grafo.id}</h2>
                </div>
            </div>

            <div class="row">
                <div class="container-fluid">
                    <div class="col-lg-6 col-md-6">
                        <c:if test="${not empty grafo}">
<!--                            <form action="DownloadGrafo" enctype="multipart/form-data" method="post" class="form-inline">
                                <a href="inserirGrafo.jsp" class="button button-green">Inserir Grafo</a>
                                <a href="carregarGrafo.jsp" class="button button-blue">Carregar Grafo</a>
                                <a href="editarGrafo.jsp" class="button button-orange">Editar Grafo</a>
                                <input type="hidden" name="idGrafo" value="${grafo.id}" />
                                <input type="submit" value="Download" class="button button-green"/> 
                                <a href="visualizaGrafoCanvas.jsp" class="button button-blue">Ver Grafo</a>
                            </form>-->
                            <div class="espacos"></div>
                            <form action="${path}/infografo" method="post" class="form-inline">
                                <input type="submit" value="Informações do Grafo" class="button button-cyan" />
                            </form>
                            <div class="espacos"></div>
                            <form action="${path}/dijkstra" method="post" class="form-inline">
                                <input type="submit" value="Calcular Dijkstra" class="button button-blue" />
                                <div class="form-group">
                                    Nó de Origem:
                                    <select name="noOrigem" class="form-control-static">
                                        <c:forEach items="${grafo.nos}" var="no">
                                            <option value="<c:out value="${no.id}"></c:out>"><c:out value="${no.id}"></c:out></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    Nó de Destino:
                                    <select name="noDestino" class="form-control-static">
                                        <option value="todosNos">Todos os nós</option>
                                        <c:forEach items="${grafo.nos}" var="no">
                                            <option value="<c:out value="${no.id}"></c:out>"><c:out value="${no.id}"></c:out></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                            <div class="espacos"></div>
                            <div class="espacos"></div>
                            <c:if test="${grafo.tipo == 'undirected' && grafo.tipoAresta}">
                                <h2>Algoritmos de Grafo</h2>
                                <form action="${path}/kruskal" method="post" class="form-inline">
                                    <input type="submit" value="Algoritmo de Kruskal" class="button button-black" />
                                </form>
                                <form action="${path}/prim" method="post" class="form-inline">
                                    <input type="submit" value="Algoritmo de Prim" class="button button-black" />
                                </form>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="col-lg-6 col-sm-6">
                        <h2>XML do Grafo</h2>
                        <code>
                            ${grafoHTML}
                        </code>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
