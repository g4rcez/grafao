/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planaridade;

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
    public boolean isPlanar(boolean first, boolean second){
        return ((first||second) == true); 
    }
    
}
