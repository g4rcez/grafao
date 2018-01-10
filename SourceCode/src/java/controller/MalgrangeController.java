package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.No;
import model.algoritmos.Malgrange;

/**
 *
 * @author garcez
 */
@WebServlet(name = "MalgrangeController", urlPatterns = {"/malgrange"})
public class MalgrangeController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        Grafo grafo = (Grafo) request.getSession().getAttribute("grafo");
        List<String> nosVisitadosTransitivoD = new ArrayList<>();
        List<String> nosVisitadosTransitivoI = new ArrayList<>();
        List<No> nos = grafo.getNos();

        Malgrange malgrange = new Malgrange(grafo);

        List<String> listaTransitivos = malgrange.malgrangeWithTransitivoDireto(0, nos.get(0).getId(), nosVisitadosTransitivoD, grafo.nodePositionInMatrix(), grafo.getMatrizAdjacencia());
        List<String> listaTransitivosInverso = malgrange.malgrangeWithTransitivoInverso(0, nos.get(0).getId(), nosVisitadosTransitivoI, grafo.nodePositionInMatrix(), grafo.getMatrizAdjacencia());
        Collections.sort(listaTransitivos);
        Collections.sort(listaTransitivosInverso);
        request.setAttribute("listaFConexa", listaTransitivos);
        request.setAttribute("listaTransitivaInversa", listaTransitivosInverso);
        request.getRequestDispatcher("/malgrange.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
