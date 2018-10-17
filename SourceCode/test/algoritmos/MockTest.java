/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;
import org.easymock.EasyMock;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sothz
 */
public class MockTest {
    
    @Test

    public void noExisteTeste() {
        INo no = EasyMock.createMock(INo.class);
        EasyMock.expect(no.getId()).andReturn("n1");

        EasyMock.replay(no);

        Aresta aresta = new Aresta("a1", no, null, 50);
        ArrayList<Aresta> arestas = new ArrayList();
        ArrayList<INo> nos = new ArrayList();
        arestas.add(aresta);
        nos.add(no);
        Grafo grafo = new Grafo("1", "teste", true, nos, arestas);
       
        Assert.assertTrue(grafo.noExiste("n1"));
        EasyMock.verify(no);

    }
    
    
    @Test
    
    public void getNoByIdTest(){
        INo no = EasyMock.createMock(INo.class);
        EasyMock.expect(no.getId()).andReturn("n1");
        
        EasyMock.replay(no);
        
        List<INo> nos = new ArrayList();
        nos.add(no);

        Assert.assertEquals(no, No.getNoById("n1", nos) );
        EasyMock.verify(no);
        
    }
}
