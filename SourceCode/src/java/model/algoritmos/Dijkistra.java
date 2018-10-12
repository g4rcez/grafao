package model.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Aresta;
import model.Grafo;
import model.No;

/**
 *
 * @author garcez
 */
public class Dijkistra {

    private Grafo grafo = null;

    public Dijkistra(Grafo givenGrafo) {
        this.grafo = givenGrafo;
    }

    public Map<String, String> calcularDijkstra(No inicio) {
        Map<String, Integer> dijkstraListaEstimativas = new HashMap<>();
        Map<String, String> dijkstraListaPrecedentes = new HashMap<>();
        List<String> listaVarridos = new ArrayList<>();
        List<Aresta> arestasAdjacentes = null;
        String noSelecionado = null;
        int menorEstimativa = 0;
        for (No no : grafo.getNos()) {
            dijkstraListaPrecedentes.put(no.getId(), "0");
            if (inicio.getId().equals(no.getId())) {
                noSelecionado = no.getId();
                dijkstraListaEstimativas.put(no.getId(), 0);
                dijkstraListaPrecedentes.put(no.getId(), no.getId());
            } else {
                dijkstraListaEstimativas.put(no.getId(), Integer.MAX_VALUE);
                if (dijkstraListaEstimativas.get(no.getId()) != Integer.MAX_VALUE) {
                    noSelecionado = no.getId();
                    dijkstraListaEstimativas.put(no.getId(), 0);
                    dijkstraListaPrecedentes.put(no.getId(), no.getId());
                }
            }
        }
        while (listaVarridos.size() != grafo.getNos().size()) {
            listaVarridos.add(noSelecionado);
            arestasAdjacentes = arestasSaemNoAtual(No.getNoById(noSelecionado, grafo.getNos()), listaVarridos);
            if (!arestasAdjacentes.isEmpty()) {
                if (arestasAdjacentes.get(0).getOrigem().getId().equals(noSelecionado)) {
                    menorEstimativa = dijkstraListaEstimativas.get(arestasAdjacentes.get(0).getDestino().getId());
                }
                for (Iterator<Aresta> it = arestasAdjacentes.iterator(); it.hasNext();) {
                    Aresta arestaAdjacente = it.next();
                    if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                        dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                        dijkstraListaEstimativas.replace(
                                arestaAdjacente.getDestino().getId(),
                                arestaAdjacente.getValor()
                                + dijkstraListaEstimativas.get(noSelecionado));
                    }
                    if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                        if (menorEstimativa > dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId())) {
                            dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                            menorEstimativa = dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId());
                        }
                    }
                }
                noSelecionado = this.menorEstimativa(dijkstraListaEstimativas, listaVarridos);
            }
        }
        return dijkstraListaPrecedentes;
    }

    public String menorEstimativa(Map<String, Integer> listaEstimativas, List<String> listaVarridos) {
        String verticeMenor = null;
        int menorEstimativa = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> mapa : listaEstimativas.entrySet()) {
            String origem = mapa.getKey();
            int estimativa = mapa.getValue();
            if ((menorEstimativa > estimativa) && !listaVarridos.contains(origem)) {
                menorEstimativa = estimativa;
                verticeMenor = origem;
            }
        }
        return verticeMenor;
    }

    public List<No> calcularDijkstra(No inicio, No destino) {
        Map<String, String> listaPrecedentes = this.calcularDijkstra(inicio);
        List<No> caminho = new ArrayList<>();
        String noAnterior = destino.getId();
        do {
            caminho.add(grafo.getNo(noAnterior));
            noAnterior = listaPrecedentes.get(noAnterior);
        } while (!noAnterior.equals(inicio.getId()));
        caminho.add(inicio);
        Collections.reverse(caminho);
        return caminho;
    }

    public List<Aresta> arestasSaemNoAtual(No no, List<String> listaVarridos) {
        List<Aresta> arestasDoNoAtual = new ArrayList();
        grafo.getArestas().stream().filter((aresta) -> ((aresta.getOrigem().getId().equals(no.getId())) && !listaVarridos.contains(aresta.getDestino().getId()))).forEachOrdered((aresta) -> {
            arestasDoNoAtual.add(aresta);
        });
        return arestasDoNoAtual;
    }
}
