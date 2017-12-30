<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo ${grafo.id}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/assets/bootstrap/beautify.min.css" rel="stylesheet">
        <link href="${path}/assets/bootstrap/font-awesome.min.css" rel="stylesheet">
        <script src="${path}/assets/bootstrap/beautify.min.js"></script>
        <link href="${path}/assets/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="${path}/assets/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h3 class="text-center">Grafo: ${grafo.id}
                    <small>
                        <c:choose>
                            <c:when test="${grafo.tipo == 'directed'}"> - Grafo direcionado</c:when>
                            <c:otherwise> - Grafo não direcionado</c:otherwise>
                        </c:choose>
                    </small>
                </h3>
            </div>
        </h3>
        <div class="row">
            <div class="col-md-4">
                <p>Todos os Nós:</p>
                <ul>
                    <c:forEach items="${grafo.nos}" var="no">
                        <li>${no.id}</li>
                        </c:forEach>
                </ul>
            </div>

            <div class="col-md-4">
                <p>Arestas:</p>
                <ul>
                    <c:forEach items="${grafo.arestas}" var="aresta">
                        <li>${aresta.id}: ${aresta.origem.id} - ${aresta.destino.id}. <c:if test="${not empty aresta.valor}"><strong>Valor: ${aresta.valor}</strong></c:if>
                        </c:forEach>
                </ul>
            </div>

            <div class="col-md-4">
                <p>Grau dos Nós:</p>
                <ul>
                    <c:forEach items="${nosComGrau}" var="no">
                        <li>
                            ${no.key.id}: <strong>${no.value}</strong>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-md-4">
                <p>Ordem do Grafo: <strong>${ordemGrafo}</strong></p>
            </div>
            <div class="col-md-4">
                <p>Arestas Incidentes:</p>
                <ul>
                    <c:forEach items="${grafo.arestas}" var="aresta">
                        <li>${aresta.id}: <strong>${aresta.origem.id}</strong> e <strong>${aresta.destino.id}</strong></li>
                        </c:forEach>
                </ul>
            </div>
            <div class="col-md-4">
                <c:if test="${nosComGrauSize > 1}">
                    <p>Nós Isolados: </p>
                    <ul>
                        <c:forEach items="${nosComGrau}" var="no">
                            <c:if test="${no.value == 0}" >
                                <li>
                                    <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                    </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
            <div class="col-md-4">
                <p>Vertices Adjacentes: </p>
                <ul>
                    <c:forEach items="${mapaVerticesAdj}" var="vertice">
                        <li>
                            Vértice <strong>${vertice.key}</strong>:
                            <ul>
                                <c:forEach items="${vertice.value}" var="verticeAdjacente">
                                    <li>
                                        Vértice Adjacente: <strong>${verticeAdjacente}</strong>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-4">
                <p>Arestas Adjacentes: </p>
                <ul>
                    <c:forEach items="${mapaArestasAdj}" var="aresta">
                        <li>
                            Aresta <strong>${aresta.key}</strong>:
                            <ul>
                                <c:forEach items="${aresta.value}" var="arestaAdjacente">
                                    <li>${arestaAdjacente}</li>
                                    </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-4">
                <p>Vertices Independentes:</p>
                <ul>
                    <c:forEach items="${mapaVerticesIndependentes}" var="vertice">
                        <li>
                            <c:out value="${vertice.key}"></c:out>
                                <ul>
                                <c:forEach items="${vertice.value}" var="verticeIndependente">
                                    <li>
                                        ${verticeIndependente.id}
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-4">
                <p>Arestas Independentes:</p>
                <ul>
                    <c:forEach items="${mapaArestasIndependentes}" var="aresta">
                        <li>
                            <c:out value="${aresta.key}"></c:out>
                                <ul>
                                <c:forEach items="${aresta.value}" var="arestaIndependente">
                                    <li>
                                        ${arestaIndependente.id}
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>


        <div class="row">
            <c:if test="${tipoGrafoArestas}">
                <div class="col-md-4">
                    <p>Grau de emissão:</p>
                    <ul>
                        <c:forEach items="${grauDeEmissao}" var="no">
                            <li>
                                ${no.key.id}: ${no.value}
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="col-md-4">
                    <p>Grau de Recepção:</p>
                    <ul>
                        <c:forEach items="${grauDeRecepcao}" var="no">
                            <li>
                                ${no.key.id}: ${no.value}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <p>Nós Sumidouros - <small>grau de saida 0</small>:</p>
                    <ul>
                        <c:forEach items="${grauDeEmissao}" var="no">
                            <c:if test="${no.value == 0}">
                                <li>
                                    <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                    </li>
                            </c:if>

                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <p>Nós Fonte - <small>grau de entrada 0</small>:</p>
                    <ul>
                        <c:forEach items="${grauDeRecepcao}" var="no">
                            <c:if test="${no.value == 0}" >
                                <li>
                                    <c:out value="${no.key.id}"></c:out>: <c:out value="${no.value}"></c:out>
                                    </li>
                            </c:if>

                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <p>Nós Sucessores: </p>
                    <ul>
                        <c:forEach items="${nosSucessores}" var="no">
                            <li>${no.key}</li>
                            <ul>
                                <c:forEach items="${no.value}" var="sucessor">
                                    <li>${sucessor}</li>
                                    </c:forEach>
                            </ul>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <p>Nós Antecessores: </p>
                    <ul>
                        <c:forEach items="${nosAntecessores}" var="no">
                            <li>${no.key}</li>
                            <ul>
                                <c:forEach items="${no.value}" var="antecessor">
                                    <li>${antecessor}</li>
                                    </c:forEach>
                            </ul>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:if>
    </div>
    <div class="row text-center">
        <a href="${path}/view" class="btn btn-warning text-center">Voltar</a>
    </div>
</div>
</body>
</html>
