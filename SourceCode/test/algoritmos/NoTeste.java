/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.Assert;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.mock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sothz
 */
public class NoTeste {

    public NoTeste() {
    }

    @Test

    public void nextEdgeTest() {
        No no1 = new No("1");
        No no2 = new No("2");
        No no3 = new No("3");

        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, true);
        Aresta aresta3 = new Aresta("a3", no3, no1, 30, true);

        TreeMap<String, Aresta> mapa1 = new TreeMap();
        mapa1.put("1", aresta1);
        mapa1.put("2", aresta3);

        TreeMap<String, Aresta> mapa2 = new TreeMap();
        mapa2.put("1", aresta1);
        mapa2.put("2", aresta2);

        TreeMap<String, Aresta> mapa3 = new TreeMap();
        mapa3.put("1", aresta2);
        mapa3.put("2", aresta3);

        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);

        ArrayList<No> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);

        no1.setEdgeList(mapa1);
        no2.setEdgeList(mapa2);
        no3.setEdgeList(mapa3);

        Assert.assertEquals(aresta1, no1.nextEdge("1"));
    }

    @Test
    public void nextPrimeiraArestaTest() {
        No no1 = new No("1");
        No no2 = new No("2");
        No no3 = new No("3");

        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, true);
        Aresta aresta3 = new Aresta("a3", no3, no1, 30, true);

        TreeMap<String, Aresta> mapa1 = new TreeMap();
        mapa1.put("1", aresta1);
        mapa1.put("2", aresta3);

        TreeMap<String, Aresta> mapa2 = new TreeMap();
        mapa2.put("1", aresta1);
        mapa2.put("2", aresta2);

        TreeMap<String, Aresta> mapa3 = new TreeMap();
        mapa3.put("1", aresta2);
        mapa3.put("2", aresta3);

        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);

        ArrayList<No> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);

        no1.setEdgeList(mapa1);
        no2.setEdgeList(mapa2);
        no3.setEdgeList(mapa3);

        Assert.assertEquals(aresta1, no1.primeiraAresta());

    }

    //TESTE DE MOCK
    

}
