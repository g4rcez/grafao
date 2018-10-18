/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;
import model.algoritmos.Dijkistra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sothz
 */
public class DijkistraTeste {

    public DijkistraTeste() {
    }

    @Test
    public void testeCalcularDijkistra() {
        No no1 = new No("1");
        No no2 = new No("2");
        No no3 = new No("3");
        No no4 = new No("4");
        No no5 = new No("5");
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no1, no5, 10, true);
        Aresta aresta3 = new Aresta("a3", no1, no3, 30, true);
        Aresta aresta4 = new Aresta("a4", no1, no4, 100, true);
        Aresta aresta5 = new Aresta("a5", no5, no4, 10, true);
        Aresta aresta6 = new Aresta("a6", no3, no4, 50, true);
        Aresta aresta7 = new Aresta("a7", no3, no2, 5, true);
        Aresta aresta8 = new Aresta("a8", no4, no2, 20, true);

        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);
        arestas.add(aresta4);
        arestas.add(aresta5);
        arestas.add(aresta6);
        arestas.add(aresta7);
        arestas.add(aresta8);

        ArrayList<INo> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);
        nos.add(no4);
        nos.add(no5);
        
        Grafo grafo = new Grafo("grafo", "teste", true, nos, arestas);
        
        Dijkistra dijkistra = new Dijkistra(grafo);
        
        
        Map<String, String> respostaEsperada = new HashMap<String, String>();
        respostaEsperada.put("1", "1");
        respostaEsperada.put("2", "3");        
        respostaEsperada.put("3", "1");        
        respostaEsperada.put("4", "5");        
        respostaEsperada.put("5", "1");
        
        Assert.assertEquals(respostaEsperada, dijkistra.calcularDijkstra(no1));
    }

}
