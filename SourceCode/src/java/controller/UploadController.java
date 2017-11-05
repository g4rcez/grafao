package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Grafo;
import model.WorkerXml;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadController extends HttpServlet {

    private static final String saveDirectory = "uploadFiles";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("grafo.jsp");
        System.out.println("Passou aqui");
        view.forward(request, response);
    }
    
    /**
     * handles file upload
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter output = response.getWriter();
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + saveDirectory;
        String nomeDoArquivo = "";
        String arquivo = "";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            arquivo += fileName;
            nomeDoArquivo = savePath + File.separator + fileName;
            try {
                part.write(nomeDoArquivo + arquivo);
                System.out.println("Arquivo + nome do arquivo: " + nomeDoArquivo +" "+ arquivo);
                System.out.println("Nome do arquivo" + nomeDoArquivo);
            } catch (IOException error) {
                System.out.println("Olha o erro: " + error.getMessage());
            }
        }
        arquivo = arquivo.trim().replace("null", "");
        System.out.println("Arquivo: " + arquivo);
        File pathDoArquivo = new File(fileSaveDir + "/" + arquivo+arquivo);
        Grafo grafo = WorkerXml.grafoFromFile(pathDoArquivo);
        output.println(fileSaveDir + "/" + arquivo + arquivo);
        request.setAttribute("tipo", "upload");
        request.setAttribute("pathFile", fileSaveDir + "/" + arquivo + arquivo);
        request.setAttribute("grafo", grafo.getId());
        request.setAttribute("sucesso", "Nome do Grafo: <strong>" + grafo.getId() + "</strong>");
        String grafoVisual = WorkerXml.writeGrafoInXmlString(grafo).replaceAll("<", "&lt;").replaceAll(">", "&gt;<br>");
        request.setAttribute("grafoVisual", grafoVisual);
        System.out.println(WorkerXml.writeGrafoInXmlString(grafo));
        processRequest(request, response);
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
