/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.No;
import model.WorkerXml;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "GrafoUpdateController", urlPatterns = {"/GrafoUpdateController"})
public class GrafoUpdateController extends HttpServlet {

    //variaveis para editar o grafo 
    private Grafo grafo;
    private int quantidadeDeNos;
    private String nomeDoGrafo;
    private String notacoes;
    private String download;
    private ArrayList<No> nos = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    //varaveis para pegar o grafo
    private static final String uploadDirectory = "upload";
    private static final int limiteDeMemória = 1024 * 1024 * 3; 	// 3MB
    private static final int tamanhoMaximoDoArquivo = 1024 * 1024 * 40; // 40MB
    private static final int tamanhoMaximoDaRequisicao = 1024 * 1024 * 50; // 50MB

    //getters e setters
    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public String getNomeDoGrafo() {
        return nomeDoGrafo;
    }

    public void setNomeDoGrafo(HttpServletRequest requisicao) {
        this.nomeDoGrafo = requisicao.getParameter("nome");
    }

    public int getQuantidadeDeNos() {
        return quantidadeDeNos;
    }

    public void setPathDownload(HttpServletRequest requisicao) {
        if (requisicao.getParameter("path-arquivo").isEmpty()) {
            this.download = System.getProperty("user.home");
        } else {
            this.download = requisicao.getParameter("path-arquivo");
        }
    }

    public String getPathDownload() {
        return this.download;
    }

    public void setQuantidadeDeNos(HttpServletRequest requisicao) {
        this.quantidadeDeNos = Integer.valueOf(requisicao.getParameter("nos"));
    }

    public String getNotacoes() {
        return notacoes;
    }

    public void setNotacoes(HttpServletRequest requisicao) {
        this.notacoes = requisicao.getParameter("grafo");
    }

    public ArrayList<No> getNos() {
        return this.nos;
    }

    public void setNos(No no) {
        this.nos.add(no);
    }

    public ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    public void setArestas(Aresta arestas) {
        this.arestas.add(arestas);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse resposta)
            throws ServletException, IOException {
        resposta.setContentType("text/html;charset=UTF-8");
        PrintWriter output = resposta.getWriter();
        output.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n"
                + "<link href='assets/bootstrap/beautify.min.css' rel='stylesheet'>"
                + "<link href='assets/bootstrap/font-awesome.min.css' rel='stylesheet'>"
                + "<script src='assets/bootstrap/beautify.min.js'></script>"
                + "<link href='https://fonts.googleapis.com/css?family=Nunito' rel='stylesheet'>"
                + "<title>Grafão</title>\n"
                + "<style>body{font-family: 'Nunito', sans-serif;}</style></head><body><div class=\"container\">"
        );
    }

    public void createXmlForDownload(Grafo webGrafo, String caminho, PrintWriter output) {
        if (WorkerXml.saveFileForGrafo(webGrafo, caminho)) {
            output.println("<a class='button button-blue'"
                    + "href=file:///" + caminho + webGrafo.getId()
                    + ".xml target='_blank'>Download XML</a>");
            output.println("<br><br>Caso não consiga abrir, acesse o link: file://" + caminho + webGrafo.getId() + ".xml");
        } else {
            output.println("XML do Grafo " + this.getNomeDoGrafo() + " não criado");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param requisicao
     * @param resposta
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        Grafo grafo = null;
        PrintWriter output = resposta.getWriter();
        if (!ServletFileUpload.isMultipartContent(requisicao)) {
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
            List<FileItem> itensFormulario = upload.parseRequest((RequestContext) requisicao);

            if (itensFormulario.isEmpty() && itensFormulario.size() > 0) {
                for (FileItem item : itensFormulario) {
                    if (!item.isFormField()) {
                        String nomeDoArquivo = new File(item.getName()).getName();
                        String diretorio = pathDoUpload + File.pathSeparator + nomeDoArquivo;
                        File guardarArquivo = new File(diretorio);
                        item.write(guardarArquivo);
                        this.setGrafo(WorkerXml.grafoFromFile(guardarArquivo));
                        this.setNotacoes(requisicao);
                        this.setQuantidadeDeNos(requisicao);
                        this.setPathDownload(requisicao);
                        if (this.getNomeDoGrafo().isEmpty() && this.getNotacoes().isEmpty()) {
                            output.println("<h2>Nome do Grafo Vazio não pode</h2>");
                        } else {
                            output.println("<h2>Nome do Grafo: " + this.nomeDoGrafo + "</h2>");
                            if (this.isValidQuantidadeDeNos()) {
                                this.defineAndReturnArestas();
                                if (!requisicao.getParameter("incluir").isEmpty()) {
                                   grafo.adicionaNos(nos);
                                   grafo.adicionaAresta(arestas);
                                } else {
                                   grafo.apagaNos(nos);
                                   grafo.apagaArestas(arestas);
                                }
                                try {
                                    String caminho = this.getPathDownload() + "/";
                                    Grafo webGrafo = new Grafo(this.getNomeDoGrafo(), "Tipo", true, this.getNos(), this.getArestas());
                                    output.println("O grafo " + this.getNomeDoGrafo() + " foi criado com sucesso");
                                    createXmlForDownload(webGrafo, caminho, output);
                                } catch (Exception error) {
                                    output.println(error);
                                }
                            } else {
                                output.println("Incoerência nos nós");
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException ex) {
            System.out.println("Erro de upload. Deu mt ruim");
        } catch (Exception erro) {
            requisicao.setAttribute("mensagem", "Ocorreu um erro:" + erro.getMessage());
        }

        processRequest(requisicao, resposta);
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

    public String defineAndReturnArestas() {
        String ligacoes = "<ul>";
        String[] ligacoesInformadas = this.getNotacoes().split(",");
        try {
            ligacoes += iteratorDeArestas(ligacoesInformadas, ligacoes) + "</ul>";
            return ligacoes;
        } catch (java.lang.ArrayIndexOutOfBoundsException erro) {
            return "<h2 class='text-center' style='color:#980606'>Formato não padrão</h2>"
                    + "<h3 class='text-center'>" + erro + "</h3>";
        }
    }

    /**
     * Cria os nós origem e destino para um futuro grafo gerado
     *
     * @return
     */
    private String iteratorDeArestas(String[] ligacoesInformadas, String ligacoes) {
        for (String ligacao : ligacoesInformadas) {
            No origem = new No(ligacao.split("-")[0].trim());
            No destino = new No(ligacao.split("-")[1].trim());
            Aresta aresta = new Aresta(origem.getId() + "---" + destino.getId(), origem, destino);
            this.setArestas(aresta);
            if (!origem.getId().isEmpty() || !destino.getId().isEmpty()) {
                ligacoes += "<li>" + origem.getId() + "->" + destino.getId() + "</li>";
            }
        }
        return ligacoes;
    }

    public boolean isValidQuantidadeDeNos() {
        char[] string = this.getNotacoes().replace(",", "").replace("-", "").replace(" ", "").toCharArray();
        ArrayList jaExistente = new ArrayList<>();
        for (char letra : string) {
            if (!jaExistente.contains(letra)) {
                jaExistente.add(letra);
                No no = new No(Character.toString(letra));
                this.setNos(no);
            }
        }
        return jaExistente.size() == this.getQuantidadeDeNos();
    }

}
