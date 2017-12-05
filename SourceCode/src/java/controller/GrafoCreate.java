package controller;

import br.com.davesmartins.graphviewlib.ViewGraph;
import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.No;
import model.WorkerXml;

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EGraphViewExcpetion {
        RequestDispatcher view = request.getRequestDispatcher("/infografo.jsp");
        String caminho = this.getServletContext().getRealPath("");
        request.setAttribute("mensagem", "Grafo salvo com Sucesso!");
        String nos[] = request.getParameterValues("nos");
        String tipo = request.getParameter("direcionado");
        String valorado = request.getParameter("valorado");
        String id = request.getParameter("nomeDoGrafo");
        String arestas[] = request.getParameterValues("arestas");
        ArrayList<No> listaNos = new ArrayList();
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
        caminho = caminho + grafo.getId().replaceAll(" ", "") + ".xml";
        System.out.println(caminho);
        createXmlForDownload(grafo, caminho, request);
        ViewGraph.generateViewGraphByInage(caminho);
        String grafoHTML = WorkerXml.grafoToHtmlReadable(grafo);
        request.setAttribute("grafoVisual", grafoHTML);
        request.setAttribute("sucesso", "Nome do Grafo: <strong>" + grafo.getId() + "</strong>");
        view.forward(request, response);
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
