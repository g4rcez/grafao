package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Grafo;
import model.WorkerXml;

@WebServlet(urlPatterns = "/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadController extends HttpServlet {

    private static final String saveDirectory = "uploadFiles";

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
        String nomeDoArquivo = null;
        String arquivo = null;
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
                part.write(nomeDoArquivo);
            } catch (IOException error) {
            }
        }
        arquivo = arquivo.trim().toLowerCase().replace("null","");
        Grafo grafo = null;
        File pathDoArquivo = new File(arquivo);
        grafo = WorkerXml.grafoFromFile(pathDoArquivo);
        output.println(fileSaveDir + "/" + arquivo);
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
