$(function () {
    var i = 1;
    var j = 1;
    $('#adiconaCampo').live('click', function () {
        $('<p><input type="text" id="no' + i + '" size="20" name="nos" value="" class="form-control" placeholder="Insira nome do Nó" /> \n\
            <span class="help-block">O nome do nó deve ser único</span><a href="#" id="removerNo" class="button button-red"><i class="fa fa-minus" aria-hidden="true"></i> Remove</a>\n\
            </p>').appendTo(formulario);
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

