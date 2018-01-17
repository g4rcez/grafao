package planaridade;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import model.Aresta;
import model.Grafo;
import model.No;

/**
 *
 * @author garcez
 */
public class SubGrafoK5 {

    private Grafo graph;

    public Grafo getGraph() {
        return graph;
    }

    public void setGraph(Grafo graph) {
        this.graph = graph;
    }

    public SubGrafoK5(Grafo graph) {
        this.setGraph(graph);
    }

    public Boolean existeK5() {
        List<No> todosOsNos = this.graph.getNos();
        List<Aresta> edgeList = this.graph.getArestas();
        List<List<No>> k2List = new ArrayList<>();
        List<List<No>> k3List = new ArrayList<>();
        List<List<No>> k4List = new ArrayList<>();
        List<List<No>> k5List = new ArrayList<>();

        /*
         * Preenche a lista k2
         */
        edgeList.forEach((Aresta edge) -> {
            List<No> k2Aux = new ArrayList<>();
            if (edge.getOrigem().getId() == null ? edge.getDestino().getId() != null : !edge.getOrigem().getId().equals(edge.getDestino().getId())) {
                k2Aux.add(edge.getOrigem());
                k2Aux.add(edge.getDestino());
                k2List.add(k2Aux);
            }
        });

        k4List.forEach(new Consumer<List<No>>() {
            @Override
            public void accept(List<No> k4Aux) {
                graph.getNos().stream().filter((No vertexAux) -> {
                    return (!k4Aux.get(0).getId().equals(vertexAux.getId())) && (k4Aux.get(1).getId() == null ? vertexAux.getId() != null : !k4Aux.get(1).getId().equals(vertexAux.getId()))
                            && (k4Aux.get(2).getId() == null ? vertexAux.getId() != null : !k4Aux.get(2).getId().equals(vertexAux.getId())) && (k4Aux.get(3).getId() == null ? vertexAux.getId() != null : !k4Aux.get(3).getId().equals(vertexAux.getId()));
                }).filter((No vertexAux) -> (graph.linkExists(k4Aux.get(0).getId(), vertexAux.getId()) == true) && (graph.linkExists(k4Aux.get(1).getId(), vertexAux.getId()) == true) && (graph.linkExists(k4Aux.get(2).getId(), vertexAux.getId()) == true) && (graph.linkExists(k4Aux.get(3).getId(), vertexAux.getId()) == true)).map((No vertexAux) -> {
                    List<No> k5Aux = new ArrayList<>();
                    k5Aux.add(k4Aux.get(0));
                    k5Aux.add(k4Aux.get(1));
                    k5Aux.add(k4Aux.get(2));
                    k5Aux.add(k4Aux.get(3));
                    k5Aux.add(vertexAux);
                    return k5Aux;
                }).forEachOrdered((k5Aux) -> {
                    k5List.add(k5Aux);
                });
            }
        });

        for (List<No> k4Aux : k4List) {
            for (No vertexAux : todosOsNos) {
                if ((k4Aux.get(0).getId() != vertexAux.getId()) && (k4Aux.get(1).getId() != vertexAux.getId())
                        && (k4Aux.get(2).getId() != vertexAux.getId()) && (k4Aux.get(3).getId() != vertexAux.getId())) {
                    if ((this.graph.linkExists(k4Aux.get(0).getId(), vertexAux.getId()) == true)
                            && (this.graph.linkExists(k4Aux.get(1).getId(), vertexAux.getId()) == true)
                            && (this.graph.linkExists(k4Aux.get(2).getId(), vertexAux.getId()) == true)
                            && (this.graph.linkExists(k4Aux.get(3).getId(), vertexAux.getId()) == true)) {
                        List<No> k5Aux = new ArrayList<>();
                        k5Aux.add(k4Aux.get(0));
                        k5Aux.add(k4Aux.get(1));
                        k5Aux.add(k4Aux.get(2));
                        k5Aux.add(k4Aux.get(3));
                        k5Aux.add(vertexAux);
                        k5List.add(k5Aux);
                    }
                }
            }
        }

        return !k5List.isEmpty();
    }
}
