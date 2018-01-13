package controller;

import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.No;
import model.algoritmos.Dijkistra;

/**
 *
 * @author garcez
 */
@WebServlet(name = "DijkstraController", urlPatterns = {"/dijkstra"})
public class DijkstraController extends HttpServlet {

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
        String nomeNoOrigem = request.getParameter("noOrigem");
        String nomeNoDestino = request.getParameter("noDestino");
        No noOrigem = grafo.getNo(nomeNoOrigem);
        Dijkistra dijkistra = new Dijkistra(grafo);
        if ("todosNos".equals(nomeNoDestino)) {
            List<List<No>> caminhosDaOrigem = new ArrayList<>();
            grafo.getNos().stream().map((no) -> dijkistra.calcularDijkstra(noOrigem, no)).forEachOrdered((caminho) -> {
                caminhosDaOrigem.add(caminho);
            });
            request.setAttribute("caminhosDaOrigem", caminhosDaOrigem);
        } else {
            No noDestino = grafo.getNo(nomeNoDestino);
            List<No> caminho = dijkistra.calcularDijkstra(noOrigem, noDestino);
            request.setAttribute("caminho", caminho);
            request.setAttribute("noDestino", nomeNoDestino);
        }
        request.setAttribute("nomeNoOrigem", nomeNoOrigem);
        request.getRequestDispatcher("/dijkistra.jsp").forward(request, response);
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
            Logger.getLogger(DijkstraController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DijkstraController.class.getName()).log(Level.SEVERE, null, ex);
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
