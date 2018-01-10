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
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="${path}/assets/jquery.min.js" type="text/javascript"></script>
        <title>Grafão</title>
    </head>
    <body>
        <h1 class="text-center">${grafo}</h1>
        <div class="row">
            <div class="container">
                <div class="col-md-6 col-lg-6">
                    <h1 class="text-center">Criar um Grafo</h1>
                    <form action="${path}/criar" method="POST" onsubmit="verificaValorAresta()">
                        <p>
                            <input type="text" id="nomeGrafo" class="form-control" size="20" name="nomeDoGrafo" value="" placeholder="Nome do Grafo" />
                            <label for="gDirecionado">
                                <input type="checkbox" name="direcionado" id="gDirecionado" value="directed">
                                Direcionado [GraphML directed]</label>
                            <label for="gValorado"><input type="checkbox" name="valorado" id="gValorado" value="valorado">Valorado [valor da aresta]</label>
                        </p>
                        <div id="formulario">
                            <p>
                                <input type="text" class="form-control" id="no" size="20" name="nos" value="" placeholder="Insira nome do Nó" />
                                <span class="help-block">O nome do nó deve ser único</span>
                            </p>
                        </div>
                        <button id="adiconaCampo" class="button button-green">
                            <strong><i class="fa fa-plus"></i> Adicionar Nó</strong>
                        </button>
                        <div class="espacos"></div>
                        <p>Arestas(par ordenado):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<span id="txtValor" style="display: none">Valor:</span></p><br />
                        <div id="arestas">
                            <input class="" type="text" id="aresta" name="arestas" value="" size="20" placeholder="A,B"/>
                            <input class="" type="number" id="valorAresta" name="valorAresta" value="" placeholder="10" style="display: none"/>
                        </div>
                        <p><button id="adiconaAresta" class="button button-green">
                                <strong><i class="fa fa-plus"></i>Adicionar Aresta</strong>
                            </button></p>
                        <input type="submit" name="bntIncluir" value="Enviar" class="button button-blue center-block"/>
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
        <script src="${path}/assets/main.js"></script>
    </body>
</html>
