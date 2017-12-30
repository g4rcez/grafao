/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Aresta;
import model.ArestaComparator;
import model.Grafo;
import model.No;

/**
 *
 * @author garcez
 */
public class Prim {
    private Grafo grafo = null;

    public Prim(Grafo givenGrafo) {
        this.grafo = givenGrafo;
    }
    
    public Grafo algoritmoDePrim(Grafo grafo) {
        Grafo subGrafo = new Grafo(grafo.getId(), grafo.getTipo(), grafo.getTipoAresta(), new ArrayList<>(), new ArrayList<>());
        List<String> verticesDoGrafo = new ArrayList<>();
        grafo.getNos().forEach((no) -> {
            verticesDoGrafo.add(no.getId());
        });
        List<String> verticesDoSubGrafo = new ArrayList<>();
        verticesDoSubGrafo.add(verticesDoGrafo.get(0));
        while (!(verticesDoSubGrafo.size() == grafo.getNos().size())) {
            Aresta aresta = getArestaPrim(verticesDoGrafo, verticesDoSubGrafo);
            subGrafo.adicionarAresta(aresta);
            verticesDoSubGrafo.add(aresta.getOrigem().getId());
        }
        List<No> nosDoSubGrafo = new ArrayList<>();
        verticesDoSubGrafo.forEach((vertice) -> {
            nosDoSubGrafo.add(new No(vertice));
        });
        subGrafo.setNos((ArrayList<No>) nosDoSubGrafo);
        return subGrafo;
    }
    
    public Aresta getArestaPrim(List<String> verticesOriginal, List<String> verticesArvore) {
        List<Aresta> arestasOriginal = new ArrayList<>();
        arestasOriginal.addAll(grafo.getArestas());
        Collections.sort(arestasOriginal, new ArestaComparator());
        List<String> diferencaEntreArestas = new ArrayList<>();
        verticesOriginal.removeAll(verticesArvore);
        diferencaEntreArestas.addAll(verticesOriginal);
        for (Aresta aresta : arestasOriginal) {
            if ((diferencaEntreArestas.contains(aresta.getOrigem().getId()) && verticesArvore.contains(aresta.getDestino().getId()))) {
                return aresta;
            }
            if ((diferencaEntreArestas.contains(aresta.getDestino().getId()) && verticesArvore.contains(aresta.getOrigem().getId()))) {
                return new Aresta(aresta.getId(), aresta.getDestino(), aresta.getOrigem(), aresta.getValor());
            }
        }
        return null;
    }
    
}
