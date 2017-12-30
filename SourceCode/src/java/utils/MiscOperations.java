/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author garcez
 */
public class MiscOperations {

    public static ArrayList<Integer> toInt(String valorArestasString[]) {
        ArrayList<Integer> lista = new ArrayList<>();
        int valorInt;
        for (String valor : valorArestasString) {
            valorInt = Integer.parseInt(valor);
            lista.add(valorInt);
        }
        return lista;
    }
    
    public static String newPathGenerator(HttpServletRequest request, String newPath) throws IOException {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + newPath;
    }
}
