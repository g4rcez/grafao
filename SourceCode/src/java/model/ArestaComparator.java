package model;

import java.util.Comparator;

/**
 *
 * @author garcez
 */
public class ArestaComparator implements Comparator<Aresta> {

    @Override
    public int compare(Aresta o1, Aresta o2) {
        if (o1.getValor() > o2.getValor()) {
            return 1;
        }
        if (o1.getValor() < o2.getValor()) {
            return -1;
        }
        return 0;
    }
}
