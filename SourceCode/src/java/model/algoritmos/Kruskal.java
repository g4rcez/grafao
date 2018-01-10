/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Aresta;
import model.ArestaComparator;
import model.Grafo;
import model.No;

/**
 *
 * @author garcez
 */
public class Kruskal {

    public Kruskal() {
    }
    

    public Grafo kruskal(Grafo grafo) {
        Grafo subGrafo = grafo;
        List<Aresta> arestasDoGrafo = new ArrayList<>();
        arestasDoGrafo.addAll(grafo.getArestas());
        Map<String, Integer> nosDoGrafo = new HashMap<>();
        int numeroVertices = grafo.getNos().size(), i = 0;
        int[][] matrizComponentes = new int[2][grafo.getNos().size()];
        Collections.sort(arestasDoGrafo, new ArestaComparator());
        for (No no : grafo.getNos()) {
            nosDoGrafo.put(no.getId(), i);
            matrizComponentes[0][i] = i;
            matrizComponentes[1][i] = i;
            i++;
        }
        while ((subGrafo.getArestas().size() <= numeroVertices - 1) && !arestasDoGrafo.isEmpty()) {
            Aresta aresta = arestasDoGrafo.get(0);
            arestasDoGrafo.remove(aresta);
            int componente_u = this.getComponenteKruskal(matrizComponentes, nosDoGrafo.get(aresta.getOrigem().getId()));
            int componente_v = this.getComponenteKruskal(matrizComponentes, nosDoGrafo.get(aresta.getDestino().getId()));
            if (componente_u != componente_v) {
                this.mergeComponenteKruskal(matrizComponentes, componente_u, componente_v);
                subGrafo.adicionarAresta(aresta);
            }
        }
        return subGrafo;
    }

    public int getComponenteKruskal(int[][] matriz, int componente) {
        int valor = matriz[1][componente];
        return valor;
    }

    public int[][] mergeComponenteKruskal(int[][] matriz, int componente_1, int componente_2) {
        int i, componenteATrocada = matriz[1][componente_2];
        for (i = 0; i < matriz[0].length; i++) {
            if (matriz[1][i] == componenteATrocada) {
                matriz[1][i] = matriz[1][componente_1];
            }
        }
        return matriz;
    }
}
