package model;

import java.util.ArrayList;

/**
 *
 * @author garcez
 */
public class Aresta {
    
    private Boolean conexo;

    private String id;
    private boolean directed;
    private INo origem;
    private INo destino;
    private Integer valor;

    /**
     *
     * @param id
     * @param origem
     * @param destino
     * @param valor
     */
    public Aresta(String id, INo origem, INo destino, Integer valor) {
        this.setId(id);
        this.setDestino(destino);
        this.setOrigem(origem);
        this.setValor(valor);
    }
   
    
    public Aresta(String id, INo origem, INo destino, Integer valor, boolean directed) {
        this.setId(id);
        this.setDestino(destino);
        this.setOrigem(origem);
        this.setValor(valor);
        this.setDirected(directed);
    }

    public INo vizinhos(String id) {
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
    public Aresta(String id, INo origem, INo destino) {
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

    public INo getOrigem() {
        return origem;
    }

    public void setOrigem(INo origem) {
        this.origem = origem;
    }

    public INo getDestino() {
        return destino;
    }

    public void setDestino(INo destino) {
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

    public INo getNoAdjacente(INo no) {
        if (no.getId().equals(this.getDestino().getId())) {
            return this.getOrigem();
        }
        if (no.getId().equals(this.getOrigem().getId())) {
            return this.getDestino();
        }
        return null;
    }
}
