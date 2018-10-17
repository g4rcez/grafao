/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author sothz
 */
public interface INo {

    public void setEdgeList(TreeMap<String, Aresta> edgeList);

    public String getId();

    public void setId(String id);

    public static No getNoById(String id, List<No> nos) {
        for (No no : nos) {
            if (no.getId().equals(id)) {
                return no;
            }
        }
        return null;

    }

    public Aresta primeiraAresta();

    public Aresta nextEdge(String id);
}
