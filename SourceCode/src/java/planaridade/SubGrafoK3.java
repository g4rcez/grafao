package planaridade;

import java.util.ArrayList;
import java.util.List;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;

public class SubGrafoK3 {

    private Grafo graph;

    public SubGrafoK3() {

    }

    public SubGrafoK3(Grafo graph) {
        this.graph = graph;
    }

    public Grafo getGraph() {
        return this.graph;
    }

    public void setGraph(Grafo graph) {
        this.graph = graph;
    }

    public Boolean existeK3x3() {
        List<Aresta> allArestasAux = this.graph.getArestas();
        List<Aresta> allArestas = new ArrayList<>();
        List<List<INo>> k1x1List = new ArrayList<List<INo>>();
        List<List<INo>> k2x2List = new ArrayList<List<INo>>();
        List<List<INo>> k3x3List = new ArrayList<List<INo>>();

        for (Aresta edge : allArestasAux) {
            if (edge.getOrigem().getId() != edge.getDestino().getId()) {
                allArestas.add(edge);
            }
        }

        /*
		 * Preenche a lista k1x1
         */
        for (Aresta edge : allArestas) {
            List<INo> k1x1Aux = new ArrayList<>();
            if (edge.getOrigem().getId() != edge.getDestino().getId()) {
                k1x1Aux.add(edge.getOrigem());
                k1x1Aux.add(edge.getDestino());
                k1x1List.add(k1x1Aux);
            }
        }

        /*
		 * Preenche a lista k2x2
         */
        for (List<INo> k1x1Aux : k1x1List) {
            for (Aresta edgeAux : allArestas) {
                if ((k1x1Aux.get(0).getId() != edgeAux.getOrigem().getId())
                        && (k1x1Aux.get(1).getId() != edgeAux.getOrigem().getId())
                        && (k1x1Aux.get(0).getId() != edgeAux.getDestino().getId())
                        && (k1x1Aux.get(1).getId() != edgeAux.getDestino().getId())) {
                    if (((this.graph.linkExists(edgeAux.getOrigem().getId(), k1x1Aux.get(0).getId())
                            && !this.graph.linkExists(edgeAux.getOrigem().getId(), k1x1Aux.get(1).getId()))
                            && (!this.graph.linkExists(edgeAux.getDestino().getId(), k1x1Aux.get(0).getId())
                            && this.graph.linkExists(edgeAux.getDestino().getId(), k1x1Aux.get(1).getId())))
                            || ((!this.graph.linkExists(edgeAux.getOrigem().getId(), k1x1Aux.get(0).getId())
                            && this.graph.linkExists(edgeAux.getOrigem().getId(), k1x1Aux.get(1).getId()))
                            && (this.graph.linkExists(edgeAux.getDestino().getId(), k1x1Aux.get(0).getId())
                            && !this.graph.linkExists(edgeAux.getDestino().getId(),
                                    k1x1Aux.get(1).getId())))) {
                        List<INo> k2x2Aux = new ArrayList<>();
                        k2x2Aux.add(k1x1Aux.get(0));
                        k2x2Aux.add(k1x1Aux.get(1));
                        k2x2Aux.add(edgeAux.getOrigem());
                        k2x2Aux.add(edgeAux.getDestino());
                        k2x2List.add(k2x2Aux);
                    }
                }
            }
        }

        /*
		 * Preenche a lista k3x3
         */
        for (List<INo> k2x2Aux : k2x2List) {
            for (Aresta edgeAux : allArestas) {
                if ((k2x2Aux.get(0).getId() != edgeAux.getOrigem().getId())
                        && (k2x2Aux.get(1).getId() != edgeAux.getOrigem().getId())
                        && (k2x2Aux.get(2).getId() != edgeAux.getOrigem().getId())
                        && (k2x2Aux.get(3).getId() != edgeAux.getOrigem().getId())
                        && (k2x2Aux.get(0).getId() != edgeAux.getDestino().getId())
                        && (k2x2Aux.get(1).getId() != edgeAux.getDestino().getId())
                        && (k2x2Aux.get(2).getId() != edgeAux.getDestino().getId())
                        && (k2x2Aux.get(3).getId() != edgeAux.getDestino().getId())) {
                    if (((this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(0).getId())
                            && !this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(1).getId()))
                            && (this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(2).getId())
                            && !this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(3).getId())))
                            || ((!this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(0).getId())
                            && this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(1).getId()))
                            && (!this.graph.linkExists(edgeAux.getOrigem().getId(), k2x2Aux.get(2).getId())
                            && this.graph.linkExists(edgeAux.getOrigem().getId(),
                                    k2x2Aux.get(3).getId())))
                            && ((this.graph.linkExists(edgeAux.getDestino().getId(), k2x2Aux.get(0).getId())
                            && !this.graph.linkExists(edgeAux.getDestino().getId(),
                                    k2x2Aux.get(1).getId()))
                            && (this.graph.linkExists(edgeAux.getDestino().getId(),
                                    k2x2Aux.get(2).getId())
                            && !this.graph.linkExists(edgeAux.getDestino().getId(),
                                    k2x2Aux.get(3).getId())))
                            || ((!this.graph.linkExists(edgeAux.getDestino().getId(), k2x2Aux.get(0).getId())
                            && this.graph.linkExists(edgeAux.getDestino().getId(), k2x2Aux.get(1).getId()))
                            && (!this.graph.linkExists(edgeAux.getDestino().getId(), k2x2Aux.get(2).getId())
                            && this.graph.linkExists(edgeAux.getDestino().getId(),
                                    k2x2Aux.get(3).getId())))) {
                        List<INo> k3x3Aux = new ArrayList<>();
                        k3x3Aux.add(k2x2Aux.get(0));
                        k3x3Aux.add(k2x2Aux.get(1));
                        k3x3Aux.add(k2x2Aux.get(2));
                        k3x3Aux.add(k2x2Aux.get(3));
                        k3x3Aux.add(edgeAux.getOrigem());
                        k3x3Aux.add(edgeAux.getDestino());
                        k3x3List.add(k3x3Aux);
                    }
                }
            }
        }

        return k3x3List.isEmpty() ? false : true;
    }
}
