package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.No;
import model.WorkerXml;

@WebServlet(name = "AdicionarAresta", urlPatterns = {"/novaAresta"})
public class AdicionarAresta extends HttpServlet {

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
        RequestDispatcher view = request.getRequestDispatcher("/grafo.jsp");
        view.forward(request, response);
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
        String origem = request.getParameter("origem").trim();
        String destino = request.getParameter("destino").trim();
        Grafo grafo = WorkerXml.getGrafo();
        No noOrigem = new No(origem);
        No noDestino = new No(destino);
        int valor = 0;
        try {
            valor = Integer.parseInt(request.getParameter("valor"));
            grafo.adicionarAresta(noOrigem, noDestino, valor);
        } catch (NullPointerException exception) {
            grafo.adicionarAresta(noOrigem, noDestino);
        }   
        request.setAttribute("grafoVisual", WorkerXml.writeGrafoInXmlString(grafo).replaceAll("<", "&lt;").replaceAll(">", "&gt;<br>"));
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

}
