package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author garcez
 */
@WebServlet(urlPatterns = "/criar")
public class ParserController extends HttpServlet {

    private String nomeDoGrafo;
    private int quantidadeDeNos;
    private String notacoes;

    public String getNomeDoGrafo() {
        return nomeDoGrafo;
    }

    public void setNomeDoGrafo(HttpServletRequest requisicao) {
        this.nomeDoGrafo = requisicao.getParameter("nome");
    }

    public int getQuantidadeDeNos() {
        return quantidadeDeNos;
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

    public String setLigacoesEntreNos() {
        String ligacoes = "<ul>";
        String[] ligacoesInformadas = this.getNotacoes().split(",");
        try {
            ligacoes += ligacoesInformadasIterator(ligacoesInformadas, ligacoes) + "</ul>";
            return ligacoes;
        } catch (java.lang.ArrayIndexOutOfBoundsException erro) {
            return "<h2 class='text-center' style='color:#980606'>Formato não padrão</h2>" + "<h3 class='text-center'>"
                    + erro + "</h3>";
        }
    }

    private String ligacoesInformadasIterator(String[] ligacoesInformadas, String ligacoes) {
        for (String ligacao : ligacoesInformadas) {
            String elementoEsquerdo = ligacao.split("-")[0].trim();
            String elementoDireito = ligacao.split("-")[1].trim();
            if (!elementoDireito.isEmpty() || !elementoEsquerdo.isEmpty()) {
                ligacoes += "<li>" + elementoEsquerdo + "->" + elementoDireito + "</li>";
            }
        }
        return ligacoes;
    }

    public boolean isValidQuantidadeDeNos() {
        String[] elementos = this.getNotacoes().replaceAll(",", " ").split("-");
        ArrayList jaExistente = new ArrayList<>();
        for (String elemento : elementos) {
            if (!jaExistente.contains(elemento)) {
                jaExistente.add(elemento);
            }
        }
        return jaExistente.size() < this.getQuantidadeDeNos();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param requisicao servlet request
     * @param resposta servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter output = resposta.getWriter()) {
            output.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "<link href=\"assets/bootstrap/beautify.min.css\" rel=\"stylesheet\">\n"
                    + "<link href=\"assets/bootstrap/font-awesome.min.css\" rel=\"stylesheet\">\n"
                    + "<script src=\"assets/bootstrap/beautify.min.js\"></script>\n"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Nunito\" rel=\"stylesheet\"> \n"
                    + "<title>Grafão</title>\n"
                    + "<style>body{font-family: 'Nunito', sans-serif;}</style></head><body><div class='container'>"
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        processRequest(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        PrintWriter output = resposta.getWriter();
        this.setNomeDoGrafo(requisicao);
        this.setNotacoes(requisicao);
        this.setQuantidadeDeNos(requisicao);

        if (this.getNomeDoGrafo().isEmpty() && this.getNotacoes().isEmpty()) {
            output.println("<h2>Nome do Grafo Vazio não pode</h2>");
        } else {
            output.println(
                    "<h2>Nome do Grafo: " + this.nomeDoGrafo + "</h2>"
            );
            if (this.isValidQuantidadeDeNos()) {
                output.println(setLigacoesEntreNos());
            }else{
                output.println("Incoerência nos nós");
            }
        }

        processRequest(requisicao, resposta);
    }

    @Override
    public String getServletInfo() {
        return "Esse aqui cria um grafo boladão com os dados passados";
    }
}
