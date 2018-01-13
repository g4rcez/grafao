package planaridade;

import java.util.ArrayList;
import java.util.List;
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

        /*
         * Preenche a lista k3
         */
        
        return k5List.isEmpty();
    }
}
