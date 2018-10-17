package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.INo;
import model.No;
import model.algoritmos.Dijkistra;
import utils.MiscOperations;

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Grafo grafo = (Grafo) request.getSession().getAttribute("grafo");
            String nomeNoOrigem = request.getParameter("noOrigem");
            String nomeNoDestino = request.getParameter("noDestino");
            INo noOrigem = grafo.getNo(nomeNoOrigem);
            Dijkistra dijkistra = new Dijkistra(grafo);
            if ("todosNos".equals(nomeNoDestino)) {
                List<List<INo>> caminhosDaOrigem = new ArrayList<>();
                grafo.getNos().stream().map((no) -> dijkistra.calcularDijkstra(noOrigem, no)).forEachOrdered((caminho) -> {
                    caminhosDaOrigem.add(caminho);
                });
                request.setAttribute("caminhosDaOrigem", caminhosDaOrigem);
            } else {
                INo noDestino = grafo.getNo(nomeNoDestino);
                List<INo> caminho = dijkistra.calcularDijkstra(noOrigem, noDestino);
                request.setAttribute("caminho", caminho);
                request.setAttribute("noDestino", nomeNoDestino);
            }
            request.setAttribute("nomeNoOrigem", nomeNoOrigem);
            request.getRequestDispatcher("/dijkistra.jsp").forward(request, response);
        } catch (NullPointerException exception) {
            response.sendRedirect(MiscOperations.newPathGenerator(request, "view"));
        }
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
    }// </editor-fold>

}
