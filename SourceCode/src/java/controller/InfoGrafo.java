package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.NosComparator;
import model.WorkerXml;

/**
 *
 * @author garcez
 */
@WebServlet(name = "InfoGrafo", urlPatterns = {"/infografo"})
public class InfoGrafo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grafo grafo = (Grafo) request.getSession().getAttribute("grafo");
        NosComparator comparator = new NosComparator();
        Collections.sort(grafo.getNos(), comparator);
        Map mapaOrganizadoGraus = new TreeMap(comparator);
        request.setAttribute("tipoGrafoArestas", grafo.getTipoAresta());
        if (grafo.getTipo().equals("directed")) {
            Map mapaOrganizadoGrauEmissao = new TreeMap(comparator);
            Map mapaOrganizadoGrauRecepcao = new TreeMap(comparator);

            mapaOrganizadoGrauEmissao.putAll(grafo.getGrausDeEmissao());
            request.setAttribute("grauDeEmissao", mapaOrganizadoGrauEmissao);

            mapaOrganizadoGrauRecepcao.putAll(grafo.getGrausDeRecepcao());
            request.setAttribute("grauDeRecepcao", mapaOrganizadoGrauRecepcao);
            request.setAttribute("nosAntecessores", grafo.getNosAntecessores());
            request.setAttribute("nosSucessores", grafo.getNosSucessores());

        }
        request.setAttribute("mapaVerticesAdj", grafo.mapeamentoVerticesAdjacentes());
        request.setAttribute("mapaArestasAdj", grafo.getMapaArestasAdjacentes());
        mapaOrganizadoGraus.putAll(grafo.getGraus());
        request.setAttribute("nosComGrau", mapaOrganizadoGraus);
        request.setAttribute("nosComGrauSize", mapaOrganizadoGraus.size());
        request.setAttribute("ordemGrafo", grafo.getOrdem());
        request.setAttribute("mapaVerticesIndependentes", grafo.verticesIndependentes());
        request.setAttribute("mapaArestasIndependentes", grafo.verticesIndependentes());
        request.getRequestDispatcher("/infos-grafo.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grafo grafo = WorkerXml.getGrafo();
        request.setAttribute("ordem", grafo.getOrdem());
        request.setAttribute("tipo", grafo.getTipo());
        request.setAttribute("nome", grafo.getId());
        request.setAttribute("adjacencia", grafo.getMatrizAdjacencia());
        request.setAttribute("incidencia", grafo.getMatrizIncidencia());
        request.setAttribute("grafoVisual", WorkerXml.grafoToHtmlReadable(grafo));

        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
