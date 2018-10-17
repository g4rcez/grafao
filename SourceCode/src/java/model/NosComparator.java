package model;


import java.util.Comparator;

/**
 *
 * @author garcez
 */

public class NosComparator implements Comparator<INo> {

    @Override
    public int compare(INo no1, INo no2) {
        return no1.getId().compareToIgnoreCase(no2.getId());
    }
}
