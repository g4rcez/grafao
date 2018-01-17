package model;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author garcez
 */
public class No {

    private TreeMap<String, Aresta> edgeList;

    private String id;

    public No(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static No getNoById(String id, List<No> nos) {
        for (No no : nos) {
            if (no.getId().equals(id)) {
                return no;
            }
        }
        return null;
    }

    public Aresta primeiraAresta() {
        if (edgeList.isEmpty()) {
            return null;
        }
        return edgeList.get(edgeList.firstKey());
    }

    public Aresta nextEdge(String id) {
        if (!edgeList.containsKey(id) || (edgeList.lastKey() == null ? id == null : edgeList.lastKey().equals(id))) {
            return null;
        }
        SortedMap<String, Aresta> map = edgeList.tailMap(id);
        return edgeList.get(map.firstKey());
    }
}
