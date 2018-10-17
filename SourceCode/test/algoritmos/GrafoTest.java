/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.Assert;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;
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
public class GrafoTest {
    
    public GrafoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void MultiGrafoTest() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        INo no4 = new No("4");
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, true);
        Aresta aresta3 = new Aresta("a3", no3, no4, 30, true);
        Aresta aresta4 = new Aresta("a4", no4, no1, 100, true);
        Aresta aresta5 = new Aresta("a5", no1, no3, 10, true);
        Aresta aresta6 = new Aresta("a6", no3, no1, 50, true);
        Aresta aresta7 = new Aresta("a7", no1, no3, 5, true);
        Aresta aresta8 = new Aresta("a8", no4, no1, 20, true);
        
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
        
        Grafo grafo = new Grafo("grafo", "teste", true, nos, arestas);
        Assert.assertTrue(grafo.isMultigrafo());
        
    }
    
    @Test
    public void getMapaArestasAdjacentesTest() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        INo no4 = new No("4");
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, true);
        Aresta aresta3 = new Aresta("a3", no3, no4, 30, true);
        Aresta aresta4 = new Aresta("a4", no4, no1, 100, true);
        Aresta aresta5 = new Aresta("a5", no1, no3, 10, true);
        Aresta aresta6 = new Aresta("a6", no3, no1, 50, true);
        Aresta aresta7 = new Aresta("a7", no1, no3, 5, true);
        Aresta aresta8 = new Aresta("a8", no4, no1, 20, true);
        
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
        
        Grafo grafo = new Grafo("grafo", "teste", true, nos, arestas);

        /*
        a1 : [a2, a4, a5, a6, a7, a8]
        a2 : [a1, a3, a5, a6, a7]
        a3 : [a2, a4, a5, a6, a7, a8]
        a4 : [a1, a3, a5, a6, a7, a8]
        a5 : [a1, a2, a3, a4, a6, a7, a8]
        a6 : [a1, a2, a3, a4, a5, a7, a8]
        a7 : [a1, a2, a3, a4, a5, a6, a8]
        a8 : [a1, a3, a4, a5, a6, a7]
         */
        Map<String, List<String>> mapa = new HashMap();
        List<String> listaAuxiliar1 = new ArrayList();
        listaAuxiliar1.add("a2");
        listaAuxiliar1.add("a4");
        listaAuxiliar1.add("a5");
        listaAuxiliar1.add("a6");
        listaAuxiliar1.add("a7");
        listaAuxiliar1.add("a8");
        mapa.put("a1", listaAuxiliar1);
        
        List<String> listaAuxiliar2 = new ArrayList();
        listaAuxiliar2.add("a1");
        listaAuxiliar2.add("a3");
        listaAuxiliar2.add("a5");
        listaAuxiliar2.add("a6");
        listaAuxiliar2.add("a7");
        mapa.put("a2", listaAuxiliar2);
        
        List<String> listaAuxiliar3 = new ArrayList();
        listaAuxiliar3.add("a2");
        listaAuxiliar3.add("a4");
        listaAuxiliar3.add("a5");
        listaAuxiliar3.add("a6");
        listaAuxiliar3.add("a7");
        listaAuxiliar3.add("a8");
        mapa.put("a3", listaAuxiliar3);
        
        List<String> listaAuxiliar4 = new ArrayList();
        listaAuxiliar4.add("a1");
        listaAuxiliar4.add("a3");
        listaAuxiliar4.add("a5");
        listaAuxiliar4.add("a6");
        listaAuxiliar4.add("a7");
        listaAuxiliar4.add("a8");
        mapa.put("a4", listaAuxiliar4);
        
        List<String> listaAuxiliar5 = new ArrayList();
        listaAuxiliar5.add("a1");
        listaAuxiliar5.add("a2");
        listaAuxiliar5.add("a3");
        listaAuxiliar5.add("a4");
        listaAuxiliar5.add("a6");
        listaAuxiliar5.add("a7");
        listaAuxiliar5.add("a8");
        mapa.put("a5", listaAuxiliar5);
        
        List<String> listaAuxiliar6 = new ArrayList();
        listaAuxiliar6.add("a1");
        listaAuxiliar6.add("a2");
        listaAuxiliar6.add("a3");
        listaAuxiliar6.add("a4");
        listaAuxiliar6.add("a5");
        listaAuxiliar6.add("a7");
        listaAuxiliar6.add("a8");
        mapa.put("a6", listaAuxiliar6);
        
        List<String> listaAuxiliar7 = new ArrayList();
        listaAuxiliar7.add("a1");
        listaAuxiliar7.add("a2");
        listaAuxiliar7.add("a3");
        listaAuxiliar7.add("a4");
        listaAuxiliar7.add("a5");
        listaAuxiliar7.add("a6");
        listaAuxiliar7.add("a8");
        mapa.put("a7", listaAuxiliar7);
        
        List<String> listaAuxiliar8 = new ArrayList();
        listaAuxiliar8.add("a1");
        listaAuxiliar8.add("a3");
        listaAuxiliar8.add("a4");
        listaAuxiliar8.add("a5");
        listaAuxiliar8.add("a6");
        listaAuxiliar8.add("a7");
        mapa.put("a8", listaAuxiliar8);
        
        Assert.assertEquals(mapa, grafo.getMapaArestasAdjacentes());
        
    }
    
    @Test
    public void isCompletoTest() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, false);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, false);
        Aresta aresta3 = new Aresta("a3", no3, no1, 30, false);
        
        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);
        
        ArrayList<INo> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);
        
        Grafo grafo = new Grafo("grafo", "teste", false, nos, arestas);
        
        Assert.assertTrue(grafo.isCompleto());
    }
    
    @Test
    public void isAdjacenteTeste() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, false);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, false);
        Aresta aresta3 = new Aresta("a3", no3, no1, 30, false);
        
        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);
        
        ArrayList<INo> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);
        
        Grafo grafo = new Grafo("grafo", "teste", false, nos, arestas);
        
        Assert.assertTrue(grafo.isAdjacente(no1, no2));
    }
    
    @Test
    public void getGrauNo() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, false);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, false);
        Aresta aresta3 = new Aresta("a3", no3, no1, 30, false);
        
        ArrayList<Aresta> arestas = new ArrayList();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);
        
        ArrayList<INo> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);
        
        Grafo grafo = new Grafo("grafo", "teste", false, nos, arestas);
        Assert.assertEquals(2, grafo.getGrauNo(no3));
    }
    
    @Test
    public void mapeamentoVerticesADjacentes() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        INo no4 = new No("4");
        Aresta aresta1 = new Aresta("a1", no1, no2, 50, true);
        Aresta aresta2 = new Aresta("a2", no2, no3, 10, true);
        Aresta aresta3 = new Aresta("a3", no3, no4, 30, true);
        Aresta aresta4 = new Aresta("a4", no4, no1, 100, true);
        Aresta aresta5 = new Aresta("a5", no1, no3, 10, true);
        Aresta aresta6 = new Aresta("a6", no3, no1, 50, true);
        Aresta aresta7 = new Aresta("a7", no1, no3, 5, true);
        Aresta aresta8 = new Aresta("a8", no4, no1, 20, true);
        
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
        
        Grafo grafo = new Grafo("grafo", "teste", true, nos, arestas);

        /*
        1 [2, 3, 4]
        2 [1, 3]
        3 [1, 2, 4]
        4 [1, 3]
         */
        Map<String, List<String>> mapa = new HashMap();
        
        List<String> listaAuxiliar1 = new ArrayList();
        listaAuxiliar1.add("2");
        listaAuxiliar1.add("3");
        listaAuxiliar1.add("4");
        mapa.put("1", listaAuxiliar1);
        
        List<String> listaAuxiliar2 = new ArrayList();
        listaAuxiliar2.add("1");
        listaAuxiliar2.add("3");
        mapa.put("2", listaAuxiliar2);
        
        List<String> listaAuxiliar3 = new ArrayList();
        listaAuxiliar3.add("1");
        listaAuxiliar3.add("2");
        listaAuxiliar3.add("4");
        mapa.put("3", listaAuxiliar3);
        
        List<String> listaAuxiliar4 = new ArrayList();
        listaAuxiliar4.add("1");
        listaAuxiliar4.add("3");
        mapa.put("4", listaAuxiliar4);
        
        Assert.assertEquals(mapa, grafo.mapeamentoVerticesAdjacentes());
    }
    
    @Test
    public void linkExistTest() {
        INo no1 = new No("1");
        INo no2 = new No("2");
        INo no3 = new No("3");
        
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

        ArrayList<INo> nos = new ArrayList();
        nos.add(no1);
        nos.add(no2);
        nos.add(no3);

        no1.setEdgeList(mapa1);
        no2.setEdgeList(mapa2);
        no3.setEdgeList(mapa3);
        
        Grafo grafo = new Grafo("grafo", "teste", true, nos, arestas);
                
        Assert.assertTrue(grafo.linkExists("1", "2"));
        
    }
}
