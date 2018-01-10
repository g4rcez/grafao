package controller;

import br.com.davesmartins.graphviewlib.ViewGraph;
import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.WorkerXml;
import utils.MiscOperations;

/**
 *
 * @author garcez
 */
@WebServlet(name = "ViewGrafo", urlPatterns = {"/view"})
public class ViewGrafo extends HttpServlet {

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
        request.setAttribute("grafo", grafo);
        try {
            ViewGraph.generateViewGraphByFrame(MiscOperations.getGraph());
        } catch (EGraphViewExcpetion ex) {
            Logger.getLogger(ViewGrafo.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("grafoHTML", WorkerXml.grafoToHtmlReadable(grafo));
        request.getRequestDispatcher("/infografo.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    public void createXmlForDownload(Grafo webGrafo, String caminho, HttpServletRequest request) {
        if (WorkerXml.saveFileForGrafo(webGrafo, caminho)) {
            request.setAttribute("pathFile", caminho + webGrafo.getId() + ".xml");
        } else {
            request.setAttribute("naoCriado", "XML do Grafo não criado");
        }
    }

}
