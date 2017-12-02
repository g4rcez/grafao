package controller;

import br.com.davesmartins.graphviewlib.ViewGraph;
import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.No;
import model.WorkerXml;

@WebServlet(name = "GrafoCreateController", urlPatterns = "/criar")
public class GrafoCreate extends HttpServlet {

    private int quantidadeDeNos;
    private String nomeDoGrafo;
    private String notacoes;
    private String download;
    private String direcionado;
    private ArrayList<No> nos = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    public String getNomeDoGrafo() {
        return nomeDoGrafo;
    }

    public String getDirecionado() {
        return direcionado;
    }

    public void setDirecionado(String direcionado) {
        this.direcionado = direcionado;
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
        int cont=0;
        for (String ligacao : ligacoesInformadas) {
            No origem = new No(ligacao.split("-")[0].trim());
            No destino = new No(ligacao.split("-")[1].trim());
            Aresta aresta = new Aresta("A"+cont, origem, destino);
            this.setArestas(aresta);
            if (!origem.getId().isEmpty() || !destino.getId().isEmpty()) {
                ligacoes += "<li>" + origem.getId() + "->" + destino.getId() + "</li>";
            }
            cont+=1;
        }
        return ligacoes;
    }

    /**
     * Verifica a quantidade de nós através dos caracteres
     *
     * @return
     */
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
        RequestDispatcher view = request.getRequestDispatcher("grafo.jsp");
        System.out.println("Passou aqui");
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.setNomeDoGrafo(request);
        this.setNotacoes(request);
        this.setQuantidadeDeNos(request);
        this.setDirecionado(request.getParameter("direcionado"));
        this.setPathDownload(request);
        if (this.getNomeDoGrafo().isEmpty() && this.getNotacoes().isEmpty()) {
            WorkerXml.clearWorkerXml();
            request.setAttribute("grafo-vazio", "<h2>Nome do Grafo Vazio não pode</h2>");
        } else {
            if (this.isValidQuantidadeDeNos()) {
                try {
                    this.defineAndReturnArestas();
                    String caminho = this.getPathDownload() + "\\";
                    Grafo webGrafo = new Grafo(this.getNomeDoGrafo(), this.getDirecionado(), true, this.getNos(), this.getArestas());
                    createXmlForDownload(webGrafo, caminho, request);
                    caminho = caminho + webGrafo.getId() + ".xml";
                    ViewGraph.generateViewGraphByInage(caminho);
                    String grafo = WorkerXml.grafoToHtmlReadable(webGrafo);
                    request.setAttribute("grafoVisual", grafo);
                    request.setAttribute("sucesso", "Nome do Grafo: <strong>" + this.nomeDoGrafo + "</strong>");
                } catch (EGraphViewExcpetion error) {
                    System.out.println(error.getMessage());
                    request.setAttribute("erroCriado", error);
                }
            } else {
                WorkerXml.clearWorkerXml();
                request.setAttribute("incoerencia", "Incoerência nos nós");
            }
        }
        processRequest(request, response);
    }

    public void createXmlForDownload(Grafo webGrafo, String caminho, HttpServletRequest request) {
        if (WorkerXml.saveFileForGrafo(webGrafo, caminho)) {
            request.setAttribute("pathFile", caminho + webGrafo.getId() + ".xml");
        } else {
            request.setAttribute("naoCriado", "XML do Grafo " + this.getNomeDoGrafo() + " não criado");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controller para criação de XML";
    }
}
