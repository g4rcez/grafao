package planaridade;

import model.Grafo;

/**
 *
 * @author garcez
 */
public class GrafoPlanar {

    /**
     *
     * @param first
     * @param second
     * @return
     */
    private static boolean isPlanar(boolean first, boolean second) {
        return ((first || second) == true);
    }

    public static boolean planar(Grafo grafo) {
        SubGrafoK5 k5 = new SubGrafoK5(grafo);
        SubGrafoK3 k3x3 = new SubGrafoK3(grafo);
        return isPlanar(k5.existeK5(), k3x3.existeK3x3());
    }
    
}
