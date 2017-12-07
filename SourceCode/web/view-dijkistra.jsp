<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafo</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h3>Grafo: <c:out value="${grafo.id}"></c:out> 
            <c:choose>
                <c:when test="${not empty caminhosDaOrigem}">
                    Caminhos do nó <c:out value="${nomeNoOrigem}"></c:out> para todos os outros:
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
                    Caminho do nó <c:out value="${nomeNoOrigem}"></c:out> para o nó <c:out value="${noDestino}"></c:out>:
                        <ul><li>
                            <c:forEach items="${caminho}" var="no">
                                ${no.id} - 
                            </c:forEach>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
            <a href="index.jsp" class="bnt">Voltar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="bntIncluir" value="Enviar" class="corBotao"/> 
    </body>
</html>