package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkerXml {

    private static XStream workerXml = new XStream(new DomDriver("UTF-8", new XmlFriendlyReplacer("__", "_")));
    private static Grafo grafo = null;

    /**
     * Constrói o XML de acordo com o padrão do GraphML
     *
     * @return
     */
    public static Grafo getGrafo() {
        return WorkerXml.grafo;
    }

    public static void setGrafo(Grafo graph) {
        WorkerXml.grafo = graph;
    }

    public static void constructXml() {
        workerXml.alias("graph", Grafo.class);
        workerXml.useAttributeFor(Grafo.class, "tipo");
        workerXml.useAttributeFor(Grafo.class, "tipoAresta");
        workerXml.useAttributeFor(Grafo.class, "id");
        workerXml.aliasField("edgedefault", Grafo.class, "tipo");
        workerXml.alias("node", No.class);
        workerXml.alias("edge", Aresta.class);
        workerXml.addImplicitCollection(Grafo.class, "nos", No.class);
        workerXml.addImplicitCollection(Grafo.class, "arestas", Aresta.class);
        workerXml.useAttributeFor(No.class, "id");
        workerXml.useAttributeFor(Aresta.class, "id");
        workerXml.useAttributeFor(Aresta.class, "origem");
        workerXml.useAttributeFor(Aresta.class, "destino");
        workerXml.useAttributeFor(Aresta.class, "valor");
        workerXml.registerLocalConverter(Aresta.class, "origem", new ConversorNo());
        workerXml.registerLocalConverter(Aresta.class, "destino", new ConversorNo());
        workerXml.aliasField("source", Aresta.class, "origem");
        workerXml.aliasField("target", Aresta.class, "destino");
        workerXml.aliasField("value", Aresta.class, "valor");
    }

    /**
     * Salvar o grafo em um arquivo determinado pelo usuário
     *
     * @param grafo
     * @param pathDoArquivo
     * @return
     */
    public static boolean saveFileForGrafo(Grafo grafo, String pathDoArquivo) {
        constructXml();
        try {
            File arquivo = new File(pathDoArquivo);
            if (!arquivo.exists()) {
                System.out.println(arquivo.getCanonicalPath());
                arquivo.createNewFile();
                FileWriter writer = new FileWriter(arquivo, true);
                writer.write(WorkerXml.writeGrafoInXmlString(grafo) + "\n\r\n\r");
                writer.flush();
                setGrafo(grafo);
                return true;
            }
        } catch (IOException error) {
            System.err.println("Erro ao abrir arquivo!" + error);
        }
        return false;
    }

    /**
     *
     * @param grafo -> Grafo
     * @return xml -> String
     */
    public static String writeGrafoInXmlString(Grafo grafo) {
        try {
            constructXml();
            setGrafo(grafo);
            return workerXml.toXML(grafo);
        } catch (Exception error) {
            return error.getMessage();
        }
    }

    /**
     * Esta função irá limpar todos os dados do WorkerXML para que possam ser
     * criados novos grafos
     */
    public static void clearWorkerXml() {
        workerXml = new XStream(new DomDriver());
    }

    public static Grafo grafoFromFile(File arquivo) throws IOException {
        constructXml();
        System.out.println("Path do Arquivo: " + arquivo.getAbsoluteFile());
        Grafo grafo = null;
        String grafoString = "";
        try {
            try (FileReader arq = new FileReader(arquivo.getAbsoluteFile())) {
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine();
                while (linha != null) {
                    grafoString += linha;
                    System.out.println(linha);
                    linha = lerArq.readLine();
                }
                arq.close();
                return (Grafo) workerXml.fromXML(grafoString);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        setGrafo(grafo);
        return grafo;
    }
    
    public static String grafoToHtmlReadable(Grafo grafo){
        return writeGrafoInXmlString(grafo).replaceAll("<", "&lt;").replaceAll(">", "&gt;<br>");
    }
}
