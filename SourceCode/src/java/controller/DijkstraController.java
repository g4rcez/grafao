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
import model.No;
import model.WorkerXml;
import model.algoritmos.Dijkistra;

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
        response.setContentType("text/html;charset=UTF-8");
        Grafo grafo = WorkerXml.getGrafo();
        Dijkistra dijkstra = new Dijkistra(grafo);
        String nomeNoOrigem = request.getParameter("noOrigem");
        String nomeNoDestino = request.getParameter("noDestino");
        No noOrigem = grafo.getNo(nomeNoOrigem);
        if ("todosNos".equals(nomeNoDestino)) {
            List<List<No>> caminhosDaOrigem = new ArrayList<>();
            grafo.getNos().stream().map((no) -> dijkstra.calcularDijkstra(noOrigem, no)).forEachOrdered((caminho) -> {
                caminhosDaOrigem.add(caminho);
            });
            request.setAttribute("caminhosDaOrigem", caminhosDaOrigem);
        } else {
            No noDestino = grafo.getNo(nomeNoDestino);
            List<No> caminho = dijkstra.calcularDijkstra(noOrigem, noDestino);
            request.setAttribute("caminho", caminho);
            request.setAttribute("noDestino", nomeNoDestino);
        }
        request.setAttribute("nomeNoOrigem", nomeNoOrigem);
        getServletContext().getRequestDispatcher("/dijkistra.jsp").forward(request, response);

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
