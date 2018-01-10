package model.algoritmos;

import java.util.ArrayList;
import java.util.List;
import model.Grafo;

public class Profundidade implements TreeAlgos{

    Grafo graph = null;

    public Profundidade(Grafo grafo) {
        this.graph = grafo;
    }

    /**
     *
     * @param root
     * @param visitados
     * @return
     */
    @Override
    public List<String> calculate(String root, List<String> visitados) {
        List<String> verticesAdjacentesAnterior = graph.gerarVerticesAdjacentes(root);
        List<String> nosAdjacentes = new ArrayList();
        visitados.add(root);

        verticesAdjacentesAnterior.stream().filter((noAdj) -> (!visitados.contains(noAdj))).forEachOrdered((noAdj) -> {
            nosAdjacentes.add(noAdj);
        });

        nosAdjacentes.stream().filter((noAdj) -> (!visitados.contains(noAdj))).forEachOrdered((String noAdj) -> {
            calculate(noAdj, visitados);
        });
        return visitados;
    }
}
