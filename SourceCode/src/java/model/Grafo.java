package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void adicionarAresta(No origem, No destino, int valor) {
        Aresta aresta = new Aresta(
                "A" + this.arestas.size(),
                origem, destino, valor
        );
        System.out.println("Olha isso:" + this.arestas.indexOf(origem));
        this.arestas.add(aresta);
        this.nos.stream().distinct().collect(Collectors.toList());
    }

    public void adicionarAresta(No origem, No destino) {
        Aresta aresta = new Aresta("A" + this.arestas.size(), origem, destino);
        this.arestas.add(aresta);
        this.nos.stream().distinct().collect(Collectors.toList());
    }

    public void adicionarAresta(Aresta aresta) {
        List<Aresta> arestasLocal = this.getArestas();
        arestasLocal.add(aresta);
        this.setArestas((ArrayList<Aresta>) arestasLocal);
    }

    public void adicionaNos(No no) {
        this.nos.add(no);
    }

    public int getOrdem() {
        return this.getArestas().size();
    }

    public Map<No, Integer> getGraus() {
        Map<No, Integer> grausDosNos = new HashMap<>();
        this.nos.forEach((no) -> {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getDestino().getId().equals(no.getId())) {
                    grau++;
                }
                if (aresta.getOrigem().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        });
        return grausDosNos;
    }

    public Map<No, Integer> getGrausDeEmissao() {
        Map<No, Integer> grausDosNos = new HashMap<>();
        this.nos.forEach((no) -> {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getOrigem().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        });
        return grausDosNos;
    }

    public Map<No, Integer> getGrausDeRecepcao() {
        Map<No, Integer> grausDosNos = new HashMap<>();
        this.nos.forEach((no) -> {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getDestino().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        });
        return grausDosNos;
    }

    public List<Aresta> getArestasDoNoAtual(No no) {
        List<Aresta> arestasDoNoAtual = new ArrayList<>();
        this.getArestas().stream().filter((aresta) -> (aresta.getOrigem().getId().equals(no.getId()) || aresta.getDestino().getId().equals(no.getId()))).forEachOrdered((aresta) -> {
            arestasDoNoAtual.add(aresta);
        });
        return arestasDoNoAtual;
    }

    public int[][] getMatrizAdjacencia() {
        int matriz[][] = new int[this.nos.size()][this.nos.size()];
        Map<String, Integer> nosDoGrafo = new HashMap<>();
        int i = 0;
        for (No no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            i++;
        }
        this.arestas.stream().map((aresta) -> {
            matriz[nosDoGrafo.get(aresta.getOrigem().getId())][nosDoGrafo.get(aresta.getDestino().getId())] = 1;
            return aresta;
        }).forEachOrdered((aresta) -> {
            matriz[nosDoGrafo.get(aresta.getDestino().getId())][nosDoGrafo.get(aresta.getOrigem().getId())] = 1;
        });
        return matriz;
    }

    public int[][] getMatrizIncidencia() {
        int matriz[][] = new int[this.nos.size()][this.arestas.size()];
        Map<String, Integer> nosDoGrafo = new HashMap<>();
        Map<String, Integer> arestasDoGrafo = new HashMap<>();
        int i = 0;
        for (No no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            i++;
        }
        i = 0;
        for (Aresta aresta : this.arestas) {
            arestasDoGrafo.put(aresta.getId(), i);
            i++;
        }
        this.arestas.stream().map((aresta) -> {
            matriz[nosDoGrafo.get(aresta.getOrigem().getId())][arestasDoGrafo.get(aresta.getId())] = 1;
            return aresta;
        }).forEachOrdered((aresta) -> {
            matriz[nosDoGrafo.get(aresta.getDestino().getId())][arestasDoGrafo.get(aresta.getId())] = 1;
        });
        return matriz;
    }

    public Map<String, List<String>> getMapaArestasAdjacentes() {
        int[][] matrizIncidencia = this.getMatrizIncidencia();
        Map<Integer, String> posicaoArestasDoGrafo = new HashMap<>();
        Map<String, List<String>> arestasAdjacentes = new HashMap<>();

        List<Integer> nosAdjacentes = new ArrayList();
        List<String> listaArestasAdjacentes = new ArrayList();
        Set<String> listaSemRepeticoes = new HashSet<>();
        int numeroTotalArestas = this.getArestas().size();
        int i = 0;
        int j = 0;
        int k = 0;

        for (Aresta aresta : this.getArestas()) {
            posicaoArestasDoGrafo.put(i, aresta.getId());
            i++;
        }
        for (j = 0; j < numeroTotalArestas; j++) {
            listaArestasAdjacentes = new ArrayList<>();
            nosAdjacentes = new ArrayList<>();
            listaSemRepeticoes = new HashSet<>();
            for (i = 0; i < this.getNos().size(); i++) {
                if (matrizIncidencia[i][j] == 1) { //guardo a posição da linha da matriz
                    nosAdjacentes.add(i);
                }

            }
            for (int linhaAtual : nosAdjacentes) {
                for (k = 0; k < numeroTotalArestas; k++) {
                    if (matrizIncidencia[linhaAtual][k] == 1) {
                        listaArestasAdjacentes.add(posicaoArestasDoGrafo.get(k));
                    }
                }
            }
            listaSemRepeticoes.addAll(listaArestasAdjacentes);
            listaArestasAdjacentes.clear();
            listaArestasAdjacentes.addAll(listaSemRepeticoes);
            listaArestasAdjacentes.remove(posicaoArestasDoGrafo.get(j));
            arestasAdjacentes.put(posicaoArestasDoGrafo.get(j), listaArestasAdjacentes);
        }
        return arestasAdjacentes;
    }

    public No getNo(String id) {
        return No.getNoById(id, this.getNos());
    }

    public Map<String, List<String>> getNosSucessores() {
        Map<String, List<String>> listaNosSucessores = new HashMap<>();
        this.getNos().forEach((no) -> {
            listaNosSucessores.put(no.getId(), this.getNosSucessores(no));
        });
        return listaNosSucessores;
    }

    public List<String> getNosSucessores(No no) {
        List<String> nosSucessores = new ArrayList<>();
        this.getArestas().stream().filter((aresta) -> (aresta.getOrigem().getId().equals(no.getId()))).forEachOrdered((aresta) -> {
            nosSucessores.add(aresta.getDestino().getId());
        });
        return nosSucessores;
    }

    public List<String> getNosAntecessores(No no) {
        List<String> nosAntecessores = new ArrayList<>();
        this.getArestas().stream().filter((aresta) -> (aresta.getDestino().getId().equals(no.getId()))).forEachOrdered((aresta) -> {
            nosAntecessores.add(aresta.getOrigem().getId());
        });
        return nosAntecessores;
    }

    public Map<String, List<String>> getNosAntecessores() {
        Map<String, List<String>> listaNosAntecessores = new HashMap<>();
        this.getNos().forEach((no) -> {
            listaNosAntecessores.put(no.getId(), this.getNosAntecessores(no));
        });
        return listaNosAntecessores;
    }

    public boolean getTipoAresta() {
        return tipoAresta;
    }
    
    public Map<String, List<String>> mapeamentoVerticesAdjacentes() {
        Map<Integer, String> posicaoNosDoGrafo = new HashMap<Integer, String>();
        int[][] matrizAdj = this.getMatrizAdjacencia();
        int i = 0, j;
        String nomeNo = null;
        Map<String, List<String>> mapaVerticesAdj = new HashMap<String, List<String>>();
        List<String> nosAdj = null;

        for (No no : this.getNos()) {
            posicaoNosDoGrafo.put(i, no.getId());
            i++;
        }
        i = 0;
        while (i < this.getNos().size()) {
            nosAdj = new ArrayList();
            for (j = 0; j < this.getNos().size(); j++) {
                if (matrizAdj[j][i] == 1) {
                    nomeNo = posicaoNosDoGrafo.get(j);
                    nosAdj.add(nomeNo);
                }
            }
            if (!nosAdj.isEmpty()) {
                mapaVerticesAdj.put(posicaoNosDoGrafo.get(i), nosAdj);
            }
            i++;
        }
        return mapaVerticesAdj;
    }
    
    public Map<String, List<No>> verticesIndependentes() {
        Set<No> gerarNosIndependentes = null;
        Map<String, List<No>> nosIndependentes = new HashMap<String, List<No>>();
        List<No> nosAdjacentes = null;
        List<No> listaNosIndependentes = null;
        Map<String, List<String>> verticesAdjacentes = this.mapeamentoVerticesAdjacentes();

        for (Map.Entry<String, List<String>> entry : verticesAdjacentes.entrySet()) {
            String verticeAtual = entry.getKey();
            List<String> listaVerticesAdjacentes = entry.getValue();
            gerarNosIndependentes = new HashSet<No>();
            listaNosIndependentes = new ArrayList();
            nosAdjacentes = new ArrayList();

            for (String vertice : listaVerticesAdjacentes) {
                nosAdjacentes.add(this.getNo(vertice));
            }
            nosAdjacentes.add(this.getNo(verticeAtual));

            for (No noDoGrafo : this.getNos()) {
                if (!nosAdjacentes.contains(noDoGrafo)) {
                    listaNosIndependentes.add(noDoGrafo);
                }
            }

            nosIndependentes.put(verticeAtual, listaNosIndependentes);
        }
        return nosIndependentes;
    }


}
