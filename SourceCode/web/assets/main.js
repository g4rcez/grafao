//função para ocultar a mensagem do index apos 4s;
$(document).ready(function () {
    setTimeout("$('#mensagem').hide(500)", 4000);
});
//função para adicionar elementos no inserirGrafo, verifica se ele é valorado ou nao e insere o input de forma adequada;
$(function () {
    var i = 1;
    var j = 1;
    $('#adiconaCampo').live('click', function () {
        $('<p><input type="text" id="no' + i + '" size="20" name="nos" value="" placeholder="Insira nome do Nó" /> \n\
            <a href="#" id="removerNo" class="bnt bntExcluir">Remove</a></p>').appendTo(formulario);
        $("#no" + i).focus();
        i++;
        return false;
    });
    $('#adiconaAresta').live('click', function () {
        if (!$("#gValorado").attr("checked")) {
            $('<p><input type="text" id="aresta' + j + '" size="20" name="arestas" value="" placeholder="A,B" /> \n\
            <a href="#" id="removerAresta" class="bnt bntExcluir">Remove</a></p>').appendTo(arestas);
        } else {
            $('<p><input type="text" id="aresta' + j + '" size="20" name="arestas" value="" placeholder="A,B" /> <input type="number" name="valorAresta" value="" placeholder="10"/>\n\
            <a href="#" id="removerAresta" class="bnt bntExcluir">Remove</a></p>').appendTo(arestas);
        }
        $("#aresta" + j).focus();
        j++;
        return false;
    });

    $('#removerNo').live('click', function () {
        if (i > 1) {
            $(this).parents('p').remove();
            i--;
        }
        return false;
    });
    $('#removerAresta').live('click', function () {
        if (j > 1) {
            $(this).parents('p').remove();
            j--;
        }
        return false;
    });

    //função para mostrar valor do primeiro input;
    $("#gValorado").click(function () {
        if (!$("#valorAresta").is(':visible')) {
            $("#valorAresta").show("fast");
            $("#txtValor").show("fast");
            $("input[name=valorAresta]").show("fast");
        } else {
            $("#valorAresta").hide("fast");
            $("#txtValor").hide("fast");
            $("input[name=valorAresta]").hide("fast");
        }
    });
});

