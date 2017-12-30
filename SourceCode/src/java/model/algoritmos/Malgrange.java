package model.algoritmos;

import java.util.ArrayList;
import java.util.Arrays;


public class Malgrange {

    private int[][] matriz;
    private int vertice;
    private int tamanhoMatriz;

    private boolean[] trajetoDireto;
    private boolean[] trajetoReverso;
    private boolean[] verticeExcluido;

    private int iteration = 0;
    private ArrayList<StringBuilder> output;

    public Malgrange(int[][] matrizRecebida, int vertice, int tamanhoDaMatriz) {
        matriz = matrizRecebida;
        vertice = vertice;
        tamanhoMatriz = tamanhoDaMatriz;
        trajetoDireto = new boolean[tamanhoMatriz + 1];
        trajetoReverso = new boolean[tamanhoMatriz + 1];
        verticeExcluido = new boolean[tamanhoMatriz + 1];
        for (int n = 0; n < tamanhoMatriz + 1; n++) {
            trajetoDireto[n] = false;
            trajetoReverso[n] = false;
            verticeExcluido[n] = true;
        }
        output = new ArrayList<>();
        resolucao(vertice, verticeExcluido);
    }

    private void resetTransitiveClosure() {
        for (int n = 0; n < tamanhoMatriz + 1; n++) {
            trajetoDireto[n] = false;
            trajetoReverso[n] = false;
        }
    }

    public void resolucao(int vertice, boolean[] excluido) {
        boolean checarExcluido = false;
        for (int n = 1; n < tamanhoMatriz + 1; n++) {
            if (excluido[n] == true) {
                checarExcluido = true;
            }
        }
        if (checarExcluido) {
            iteration++;
            output.add(new StringBuilder("Passo: " + iteration));
            output.add(new StringBuilder(("O vértice da partição: " + "x" + vertice)));
            resetTransitiveClosure();
            fillStraightTransitiveClosure(vertice, excluido);
            addOutput("Fechamento transitivo direto", trajetoDireto);
            fillReverseTransitiveClosure(vertice, excluido);
            addOutput("Fechamento transitivo inverso", trajetoReverso);
            boolean[] in = getIntersection(trajetoDireto, trajetoReverso);
            addOutput("O subgrafo está subdividido de forma fortemente conexa", in);
            boolean[] ex = getExcludedVertex(in, excluido);
            addOutput("Novo gráfo", ex);
            int tempInt = -1;
            for (int n = 1; n < tamanhoMatriz + 1; n++) {
                if (ex[n] == true) {
                    tempInt = n;
                    break;
                }
            }
            if (tempInt != -1) {
                resolucao(tempInt, ex);
            }
        }

    }

    private void addOutput(String mensagem, boolean[] b) {
        StringBuilder string = new StringBuilder();
        string.append(mensagem).append(": ");
        for (int n = 1; n < tamanhoMatriz + 1; n++) {
            if (b[n] == true) {
                string.append("x").append(n).append(" ");
            }
        }
        output.add(string);
    }

    void fillStraightTransitiveClosure(int v, boolean[] ex) {
        trajetoDireto[v] = true;
        for (int n = 0; n < tamanhoMatriz; n++) {
            if (ex[v] == true && ex[n + 1] == true) {
                if (matriz[v - 1][n] == 1 && trajetoDireto[n + 1] == false) {
                    trajetoDireto[n + 1] = false;
                    fillStraightTransitiveClosure(n + 1, ex);
                }
            }

        }
    }

    void fillReverseTransitiveClosure(int v, boolean[] ex) {
        trajetoReverso[v] = true;
        for (int n = 0; n < tamanhoMatriz; n++) {
            if (ex[v] == true && ex[n + 1] == true) {
                if (matriz[n][v - 1] == 1 && trajetoReverso[n + 1] == false) {
                    trajetoReverso[n + 1] = false;
                    fillReverseTransitiveClosure(n + 1, ex);
                }

            }
        }

    }

    boolean[] getIntersection(boolean[] b1, boolean[] b2) {
        boolean[] intersection = new boolean[tamanhoMatriz + 1];

        for (int n = 1; n < tamanhoMatriz + 1; n++) {
            intersection[n] = b1[n] == true && b2[n] == true;
        }

        return intersection;
    }

    boolean[] getExcludedVertex(boolean[] intr, boolean[] excld) {
        boolean[] excluded = Arrays.copyOf(excld, tamanhoMatriz + 1);
        for (int n = 1; n < tamanhoMatriz + 1; n++) {
            if (intr[n] == true) {
                excluded[n] = false;
            }
        }
        return excluded;
    }

}
