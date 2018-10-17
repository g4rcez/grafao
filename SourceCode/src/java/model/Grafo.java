package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author garcez
 */
public class Grafo {

    private String id;
    private String tipo;
    private boolean tipoAresta;
    private ArrayList<INo> nos;
    private ArrayList<Aresta> arestas;

    /**
     *
     * @param id
     * @param tipo
     * @param tipoAresta
     * @param nos
     * @param arestas
     */
    public Grafo(String id, String tipo, boolean tipoAresta, ArrayList<INo> nos, ArrayList<Aresta> arestas) {
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

    public ArrayList<INo> getNos() {
        return nos;
    }

    public void setNos(ArrayList<INo> nos) {
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

    public Map<INo, Integer> getGraus() {
        Map<INo, Integer> grausDosNos = new HashMap<>();
        this.nos.forEach((no) -> {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getDestino().getId().equals(no.getId()) || aresta.getOrigem().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        });
        return grausDosNos;
    }

    public boolean noExiste(String id) {
        
        for (Aresta aresta : this.arestas) {

            if (aresta.getOrigem().getId().equals(id)
                    || aresta.getDestino().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Map<INo, Integer> getGrausDeEmissao() {
        Map<INo, Integer> grausDosNos = new HashMap<>();
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

    public Map<INo, Integer> getGrausDeRecepcao() {
        Map<INo, Integer> grausDosNos = new HashMap<>();
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

    public boolean isAdjacente(INo no1, INo no2) {
        int i;
        for (i = 0; i < this.getArestas().size(); i++) {
            if (this.getArestas().get(i).isDirected()) {
                if (this.getArestas().get(i).getOrigem().getId().equals(no1.getId()) && this.getArestas().get(i).getDestino().getId().equals(no2.getId())) {
                    return true;
                }
            } else {
                if (this.getArestas().get(i).getOrigem().getId().equals(no1.getId()) && this.getArestas().get(i).getDestino().getId().equals(no2.getId())
                        || this.getArestas().get(i).getOrigem().getId().equals(no2.getId()) && this.getArestas().get(i).getDestino().getId().equals(no1.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Aresta> getArestasDoNoAtual(String no) {
        List<Aresta> arestasDoNoAtual = new ArrayList();
        if (this.getTipo().equals("undirected")) {
            this.getArestas().stream().filter((aresta) -> (aresta.getOrigem().getId().equals(no) || aresta.getDestino().getId().equals(no))).forEachOrdered((aresta) -> {
                arestasDoNoAtual.add(aresta);
            });
        } else {
            this.getArestas().stream().filter((aresta) -> (aresta.getOrigem().getId().equals(no))).forEachOrdered((aresta) -> {
                arestasDoNoAtual.add(aresta);
            });
        }
        return arestasDoNoAtual;
    }

    public int[][] getMatrizAdjacencia() {
        int matriz[][] = new int[this.nos.size()][this.nos.size()];
        Map<String, Integer> nosDoGrafo = new HashMap<>();
        int i = 0;
        for (INo no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            i++;
        }
        this.arestas.stream().map((Aresta aresta) -> {
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
        for (INo no : this.nos) {
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

    public Map<Integer, String> nodePositionInMatrix() {
        Map<Integer, String> nosDoGrafo = new HashMap<>();
        int i = 0;
        for (INo no : this.nos) {
            nosDoGrafo.put(i, no.getId());
            i++;
        }
        return nosDoGrafo;
    }

    public boolean isMultigrafo() {
        int i, j;
        for (i = 0; i < this.getArestas().size(); i++) {
            for (j = 0; j < this.getArestas().size(); j++) {
                if (this.getArestas().get(i).getDestino().getId().equals(this.getArestas().get(j).getDestino().getId())) {
                    if (this.getArestas().get(i).getOrigem().getId().equals(this.getNos().get(j).getId())) {
                        return true;
                    }
                }
            }
            for (j = 0; j < this.getArestas().size(); j++) {
                if (this.getArestas().get(i).getDestino().getId().equals(this.getArestas().get(j).getOrigem().getId())) {
                    if (this.getArestas().get(i).getDestino().getId().equals(this.getArestas().get(j).getDestino().getId())) {
                        if (!this.getArestas().get(i).getId().equals(this.getArestas().get(j).getId())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean isCompleto() {
        if (this.isRegular()) {
            for (int i = 0; i < this.getNos().size(); i++) {
                for (int j = 0; j < this.getNos().size(); j++) {
                    if (!(this.getNos().get(i).getId().equals(this.getNos().get(j).getId()))) {
                        if (!this.isAdjacente(this.getNos().get(i), this.getNos().get(j))) {
                            return false;
                        }
                    }
                }

            }
            return true;
        }
        return false;
    }

    public INo getNo(String id) {
        for (Aresta aresta : this.arestas) {
            if (aresta.getDestino().getId() == id) {
                return aresta.getDestino();
            }
            if (aresta.getOrigem().getId() == id) {
                return aresta.getOrigem();
            }
        }
        return null;
    }

    public Map<String, List<String>> getNosSucessores() {
        Map<String, List<String>> listaNosSucessores = new HashMap<>();
        this.getNos().forEach((no) -> {
            listaNosSucessores.put(no.getId(), this.getNosSucessores(no));
        });
        return listaNosSucessores;
    }

    public List<String> getNosSucessores(INo no) {
        List<String> nosSucessores = new ArrayList<>();
        this.getArestas().stream().filter((aresta) -> (aresta.getOrigem().getId().equals(no.getId()))).forEachOrdered((aresta) -> {
            nosSucessores.add(aresta.getDestino().getId());
        });
        return nosSucessores;
    }

    public List<String> getNosAntecessores(INo no) {
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

    public boolean linkExists(String idA, String idB) {
        INo v = this.getNo(idA);
        if (v != null && this.getNo(idB) != null) {

            for (Aresta a = v.primeiraAresta(); a != null; a = v.nextEdge(a.getId())) {
                return (a.vizinhos(idA).getId() == null ? idB == null : a.vizinhos(idA).getId().equals(idB));
            }
        }
        return false;
    }

    public Map<String, List<String>> mapeamentoVerticesAdjacentes() {
        Map<Integer, String> posicaoNosDoGrafo = new HashMap<>();
        int[][] matrizAdj = this.getMatrizAdjacencia();
        int i = 0, j;
        String nomeNo = null;
        Map<String, List<String>> mapaVerticesAdj = new HashMap<>();
        List<String> nosAdj = null;
        for (INo no : this.getNos()) {
            posicaoNosDoGrafo.put(i, no.getId());
            i += 1;
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
            i += 1;
        }
        return mapaVerticesAdj;
    }

    public int getGrauNo(INo no) {
        int firstGrau = 0;
        int secondGrau = 0;
        int i;
        for (i = 0; i < this.arestas.size(); i++) {
            if (this.arestas.get(i).getDestino() == no) {
                firstGrau++;
            }
        }
        for (i = 0; i < this.arestas.size(); i++) {
            if (this.arestas.get(i).getOrigem().getId().equals(no.getId())) {
                secondGrau++;
            }
        }
        return firstGrau + secondGrau;
    }

    public Boolean isRegular() {
        int counter;
        int grauTotal = this.getGrauNo(this.getNos().get(0));
        for (counter = 0; counter < this.getNos().size(); counter++) {
            if (this.getGrauNo(this.getNos().get(counter)) != grauTotal) {
                return false;
            }
        }
        return true;
    }

    public boolean isFonte(INo no) {
        return this.getGrauNo(no) == 0;
    }

    public Map<String, List<INo>> verticesIndependentes() {
        Set<INo> gerarNosIndependentes = null;
        Map<String, List<INo>> nosIndependentes = new HashMap<>();
        List<INo> nosAdjacentes = null;
        List<INo> listaNosIndependentes = null;
        Map<String, List<String>> verticesAdjacentes = this.mapeamentoVerticesAdjacentes();

        for (Map.Entry<String, List<String>> entry : verticesAdjacentes.entrySet()) {
            String verticeAtual = entry.getKey();
            List<String> listaVerticesAdjacentes = entry.getValue();
            gerarNosIndependentes = new HashSet<>();
            listaNosIndependentes = new ArrayList();
            nosAdjacentes = new ArrayList();

            for (String vertice : listaVerticesAdjacentes) {
                nosAdjacentes.add(this.getNo(vertice));
            }
            nosAdjacentes.add(this.getNo(verticeAtual));

            for (INo noDoGrafo : this.getNos()) {
                if (!nosAdjacentes.contains(noDoGrafo)) {
                    listaNosIndependentes.add(noDoGrafo);
                }
            }

            nosIndependentes.put(verticeAtual, listaNosIndependentes);
        }
        return nosIndependentes;
    }

    public boolean possuiElementoRepetido(List<String> nosVisitados) {
        List<String> novaLista = new ArrayList(new HashSet(nosVisitados));
        return novaLista.size() < nosVisitados.size();
    }

    public List<String> gerarVerticesAdjacentes(String no) {
        List<Aresta> arestasDoNo = this.getArestasDoNoAtual(no);
        List<INo> nosAdjacentes = new ArrayList();
        List<String> nomeNosAdjacentes = new ArrayList();

        if (this.getTipo().equals("directed")) {
            arestasDoNo.forEach(new Consumer<Aresta>() {
                @Override
                public void accept(Aresta arestaAtual) {
                    nosAdjacentes.add(arestaAtual.getDestino());
                }
            });
        } else {
            arestasDoNo.stream().map((arestaAtual) -> {
                nosAdjacentes.add(arestaAtual.getDestino());
                return arestaAtual;
            }).filter((arestaAtual) -> (no.equals(arestaAtual.getOrigem().getId()) || no.equals(arestaAtual.getDestino().getId()))).forEachOrdered((arestaAtual) -> {
                nosAdjacentes.add(arestaAtual.getOrigem());
            });
        }
        Set<INo> semRepeticoes = new TreeSet(new NosComparator());
        semRepeticoes.addAll(nosAdjacentes);
        nosAdjacentes.clear();
        semRepeticoes.stream().filter((noAtual) -> (!noAtual.getId().equals(no))).forEachOrdered((noAtual) -> {
            nosAdjacentes.add(noAtual);
        });
        nosAdjacentes.forEach((noAtual) -> {
            nomeNosAdjacentes.add(noAtual.getId());
        });
        return nomeNosAdjacentes;
    }
}
