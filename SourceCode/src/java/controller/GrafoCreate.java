package controller;

import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.INo;
import model.No;
import model.WorkerXml;
import utils.MiscOperations;

/**
 *
 * @author garcez
 */
@WebServlet(name = "GrafoCreateController", urlPatterns = "/criar")
public class GrafoCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            processRequest(request, response);
        } catch (IOException | EGraphViewExcpetion ex) {
            Logger.getLogger(GrafoCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lib ViewGraph não funciona com este método, apenas em upload graph
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws EGraphViewExcpetion
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EGraphViewExcpetion {
        MiscOperations.setPathFiles(this.getServletContext().getRealPath("") + "../../grafos/");
        String nos[] = request.getParameterValues("nos");
        String tipo = request.getParameter("direcionado");
        String valorado = request.getParameter("valorado");
        String id = request.getParameter("nomeDoGrafo");
        String arestas[] = request.getParameterValues("arestas");
        ArrayList<INo> listaNos = new ArrayList();
        ArrayList<Aresta> listaArestas = new ArrayList();
        No ponto = null;
        String no1 = null;
        String no2 = null;
        Aresta aresta = null;
        Grafo grafo = null;
        boolean tipoAresta = false;
        int i = 1;

        if (tipo == null) {
            tipo = "undirected";
        }

        for (String no : nos) {
            ponto = new No(no.toUpperCase());
            listaNos.add(ponto);
        }
        if ("valorado".equals(valorado)) {
            tipoAresta = true;
            String valorArestasString[] = request.getParameterValues("valorAresta");
            ArrayList<Integer> valoresAresta = GrafoCreate.toInt(valorArestasString);
            for (String a : arestas) {
                for (int b : valoresAresta) {
                    a = a.toUpperCase();
                    a = a.replaceAll(" ", "");
                    String[] resultado = a.split(",", 2);
                    no1 = resultado[0];
                    no2 = resultado[1];
                    aresta = new Aresta("Aresta " + i, No.getNoById(no1, listaNos), No.getNoById(no2, listaNos), b);
                    listaArestas.add(aresta);
                    valoresAresta.remove((Object) b);
                    i++;
                    break;
                }
            }
        } else {
            for (String a : arestas) {
                a = a.toUpperCase();
                a = a.replaceAll(" ", "");
                String[] resultado = a.split(",", 2);
                no1 = resultado[0];
                no2 = resultado[1];
                aresta = new Aresta("A" + i, No.getNoById(no1, listaNos), No.getNoById(no2, listaNos));
                listaArestas.add(aresta);
                i++;
            }
        }
        grafo = new Grafo(id, tipo, tipoAresta, listaNos, listaArestas);
        System.out.println("Tipo do grafo" + grafo.getTipo());
        String caminho = MiscOperations.getPathFiles();
        caminho = caminho + grafo.getId().replaceAll(" ", "") + ".xml";
        createXmlForDownload(grafo, caminho, request);
        WorkerXml.setGrafo(grafo);
        String grafoHTML = WorkerXml.grafoToHtmlReadable(grafo);
        request.getSession().setAttribute("grafo", grafo);
        request.getSession().setAttribute("grafoHTML", grafoHTML);
        response.sendRedirect(MiscOperations.newPathGenerator(request, "/view"));
    }

    private static ArrayList<Integer> toInt(String valorArestasString[]) {
        ArrayList<Integer> lista = new ArrayList<>();
        int valorInt;
        for (String valor : valorArestasString) {
            valorInt = Integer.parseInt(valor);
            lista.add(valorInt);
        }
        return lista;
    }

    public void createXmlForDownload(Grafo webGrafo, String caminho, HttpServletRequest request) {
        if (WorkerXml.saveFileForGrafo(webGrafo, caminho)) {
            request.setAttribute("pathFile", caminho + webGrafo.getId() + ".xml");
        } else {
            request.setAttribute("naoCriado", "XML do Grafo não criado");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (EGraphViewExcpetion ex) {
            Logger.getLogger(GrafoCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
