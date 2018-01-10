package model.algoritmos;

import java.util.List;
import java.util.Map;
import model.Grafo;

public class Malgrange {
    
    // Se o grafo não for fortemente conexo, vai dar pau
    // Fortemente conexo com estimativa de n^n, sem implementação afim de manter tempos
    // baixos nos algoritmos

    Grafo graph = null;

    public Malgrange(Grafo grafo) {
        this.graph = grafo;
    }

    public List<String> malgrangeWithTransitivoDireto(int linha, String primeiroNo, List<String> visitados, Map<Integer, String> graphNodes, int[][] matrizAdjacencia) {
        int counter = 0;
        while (linha < graph.getNos().size()) {
            while (counter < graph.getNos().size()) {
                if (visitados.contains(primeiroNo)) {
                    visitados.remove(primeiroNo);
                    return visitados;
                }
                if (matrizAdjacencia[linha][counter] == 1) {
                    visitados.add(graphNodes.get(counter));
                    if (graph.possuiElementoRepetido(visitados)) {
                        visitados.remove(graphNodes.get(counter));
                        return visitados;
                    }
                    malgrangeWithTransitivoDireto(counter, primeiroNo, visitados, graphNodes, matrizAdjacencia);
                }
                counter++;
            }
            return visitados;
        }
        return visitados;
    }

    public List<String> malgrangeWithTransitivoInverso(int linha, String primeiroNo, List<String> nosVisitados, Map<Integer, String> nosDoGrafo, int[][] matrizAdjacencia) {
        int counter = 0;
        while (linha < graph.getNos().size()) {
            while (counter < graph.getNos().size()) {
                if (nosVisitados.contains(primeiroNo)) {
                    nosVisitados.remove(primeiroNo);
                    return nosVisitados;
                }
                if (matrizAdjacencia[counter][linha] == 1) {
                    nosVisitados.add(nosDoGrafo.get(counter));
                    if (graph.possuiElementoRepetido(nosVisitados)) {
                        nosVisitados.remove(nosDoGrafo.get(counter));
                        return nosVisitados;
                    }
                    malgrangeWithTransitivoInverso(counter, primeiroNo, nosVisitados, nosDoGrafo, matrizAdjacencia);
                }
                counter++;
            }
            return nosVisitados;
        }
        return nosVisitados;
    }

}
