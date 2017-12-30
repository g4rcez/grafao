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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="container">
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
            </div>
            <a href="${path}/view" class="btn btn-warning">Voltar</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </body>
</html>
