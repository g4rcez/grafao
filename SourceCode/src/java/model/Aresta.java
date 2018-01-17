package model;

import java.util.ArrayList;

/**
 *
 * @author garcez
 */
public class Aresta {

    private String id;
    private boolean directed;
    private No origem;
    private No destino;
    private Integer valor;

    /**
     *
     * @param id
     * @param origem
     * @param destino
     * @param valor
     */
    public Aresta(String id, No origem, No destino, Integer valor) {
        this.setId(id);
        this.setDestino(destino);
        this.setOrigem(origem);
        this.setValor(valor);
    }

    public Aresta(String id, No origem, No destino, Integer valor, boolean directed) {
        this.setId(id);
        this.setDestino(destino);
        this.setOrigem(origem);
        this.setValor(valor);
        this.setDirected(directed);
    }

    public No vizinhos(String id) {
        if (id == null ? this.getOrigem().getId() == null : id.equals(this.getOrigem().getId())) {
            return this.getDestino();
        } else if (id == null ? this.getDestino().getId() == null : id.equals(this.getDestino().getId())) {
            return this.getOrigem();
        } else {
            return null;
        }
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    /**
     *
     * @param id
     * @param origem
     * @param destino
     */
    public Aresta(String id, No origem, No destino) {
        this.setId(id);
        this.setDestino(destino);
        this.setOrigem(origem);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public No getOrigem() {
        return origem;
    }

    public void setOrigem(No origem) {
        this.origem = origem;
    }

    public No getDestino() {
        return destino;
    }

    public void setDestino(No destino) {
        this.destino = destino;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    /**
     *
     * @param id
     * @param arestas
     * @return
     */
    public static Aresta getArestaById(String id, ArrayList<Aresta> arestas) {
        for (Aresta aresta : arestas) {
            if (aresta.getId().equals(id)) {
                return aresta;
            }
        }
        return null;
    }

    public No getNoAdjacente(No no) {
        if (no.getId().equals(this.getDestino().getId())) {
            return this.getOrigem();
        }
        if (no.getId().equals(this.getOrigem().getId())) {
            return this.getDestino();
        }
        return null;
    }
}
