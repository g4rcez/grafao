/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.algoritmos.Kruskal;

/**
 *
 * @author garcez
 */
@WebServlet(name = "KruskalController", urlPatterns = {"/kruskal"})
public class KruskalController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EGraphViewExcpetion {
        response.setContentType("text/html;charset=UTF-8");
        Grafo grafo = (Grafo) request.getSession().getAttribute("grafo");
        Kruskal krustal = new Kruskal();
        Grafo subGrafo = krustal.kruskal(grafo);
        request.setAttribute("grafo", subGrafo);
        getServletContext().getRequestDispatcher("/grafoco.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (EGraphViewExcpetion ex) {
            Logger.getLogger(KruskalController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(KruskalController.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
