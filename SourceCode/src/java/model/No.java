package model;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author garcez
 */
public class No implements INo {

    private TreeMap<String, Aresta> edgeList;
    private String id;

    public No(String id) {
        this.id = id;
    }

    public void setEdgeList(TreeMap<String, Aresta> edgeList) {
        this.edgeList = edgeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static INo getNoById(String id, List<INo> nos) {
        for (INo no : nos) {
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
