package model.algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Grafo;

/**
 *
 * @author garcez
 */
public class Largura implements TreeAlgos {

    Grafo graph = null;

    public Largura(Grafo grafo) {
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
        List<String> verticesAdjacentesAnteriores = graph.gerarVerticesAdjacentes(root);
        List<String> nosAdjacentes = new ArrayList<>();
        visitados.add(root);
        LinkedList<String> filaDeNosAVisitar = new LinkedList();
        filaDeNosAVisitar.add(root);
        while (!filaDeNosAVisitar.isEmpty()) {
            String no = filaDeNosAVisitar.pop();
            graph.gerarVerticesAdjacentes(no).stream().filter((verticeAdjacente) -> (!visitados.contains(verticeAdjacente))).map((verticeAdjacente) -> {
                visitados.add(verticeAdjacente);
                return verticeAdjacente;
            }).forEachOrdered((verticeAdjacente) -> {
                filaDeNosAVisitar.add(verticeAdjacente);
            });
        }
        return visitados;
    }
}
