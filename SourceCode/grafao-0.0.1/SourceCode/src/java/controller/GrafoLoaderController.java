/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.WorkerXml;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "GrafoLoaderController", urlPatterns = "/upload")
public class GrafoLoaderController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String uploadDirectory = "upload";
    private static final int limiteDeMemória = 1024 * 1024 * 3; 	// 3MB
    private static final int tamanhoMaximoDoArquivo = 1024 * 1024 * 40; // 40MB
    private static final int tamanhoMaximoDaRequisicao = 1024 * 1024 * 50; // 50MB

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Carregando grafo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handler para o método POST do HTTP.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grafo grafo = null;
        PrintWriter output = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            output.println("Erro no formulário, reveja o envio");
            output.flush();
            return;
        }

        DiskFileItemFactory instanciaCliente = new DiskFileItemFactory();
        instanciaCliente.setSizeThreshold(limiteDeMemória);
        instanciaCliente.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(instanciaCliente);
        upload.setFileSizeMax(tamanhoMaximoDoArquivo);
        upload.setSizeMax(tamanhoMaximoDaRequisicao);
        String pathDoUpload = getServletContext().getRealPath("") + File.separator + uploadDirectory;
        File uploadDirectoryTest = new File(pathDoUpload);
        
        if (!uploadDirectoryTest.exists()) {
            uploadDirectoryTest.mkdir();
        }

        try {
            List<FileItem> itensFormulario = upload.parseRequest((RequestContext) request);

            if (itensFormulario.isEmpty() && itensFormulario.size() > 0) {
                for (FileItem item : itensFormulario) {
                    if (!item.isFormField()) {
                        String nomeDoArquivo = new File(item.getName()).getName();
                        String diretorio = pathDoUpload + File.pathSeparator + nomeDoArquivo;
                        File guardarArquivo = new File(diretorio);
                        item.write(guardarArquivo);
                        grafo = WorkerXml.grafoFromFile(guardarArquivo);
                    }
                }
            }
        } catch (FileUploadException ex) {
            System.out.println("Erro de upload. Deu mt ruim");
        } catch (Exception erro) {
            request.setAttribute("mensagem", "Ocorreu um erro:" + erro.getMessage());
        }
        if (grafo != null) {
            request.getSession().setAttribute("grafo", grafo);
            request.setAttribute("grafo", grafo);
            response.sendRedirect("/upload");
        } else {
            request.setAttribute("mensagem", "O Grafo está vazio, champz...");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        processRequest(request, response);
    }

    /**
     * Retorna uma descrição do Servlet
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
