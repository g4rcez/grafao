package model;

import java.util.ArrayList;

public class Grafo {

    private String id;
    private String tipo;
    private boolean tipoAresta;
    private ArrayList<No> nos;
    private ArrayList<Aresta> arestas;

    /**
     *
     * @param id
     * @param tipo
     * @param tipoAresta
     * @param nos
     * @param arestas
     */
    public Grafo(String id, String tipo, boolean tipoAresta, ArrayList<No> nos, ArrayList<Aresta> arestas) {
        this.setId(id);
        this.setNos(nos);
        this.setTipo(tipo);
        this.setTipoAresta(tipoAresta);
        this.setArestas(arestas);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isTipoAresta() {
        return tipoAresta;
    }

    public void setTipoAresta(boolean tipoAresta) {
        this.tipoAresta = tipoAresta;
    }

    public ArrayList<No> getNos() {
        return nos;
    }

    public void setNos(ArrayList<No> nos) {
        this.nos = nos;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }
}
