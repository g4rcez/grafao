package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkerXml {

    private static XStream workerXml = new XStream(new DomDriver());
    
    /**
     * Constrói o XML de acordo com o padrão do GraphML
     *
     */
    public static void constructXml() {
        workerXml.alias("graph", Grafo.class);
        workerXml.alias("node", No.class);
        workerXml.alias("edge", Aresta.class);
        workerXml.registerLocalConverter(Aresta.class, "origem", new ConversorNo());
        workerXml.registerLocalConverter(Aresta.class, "destino", new ConversorNo());
        workerXml.aliasField("source", Aresta.class, "origem");
        workerXml.aliasField("target", Aresta.class, "destino");
        workerXml.useAttributeFor(Grafo.class, "tipo");
        workerXml.useAttributeFor(Grafo.class, "tipoAresta");
        workerXml.useAttributeFor(Grafo.class, "id");
        workerXml.aliasField("edgedefault", Grafo.class, "tipo");
        workerXml.addImplicitCollection(Grafo.class, "nos", No.class);
        workerXml.addImplicitCollection(Grafo.class, "arestas", Aresta.class);
        workerXml.useAttributeFor(No.class, "id");
        workerXml.useAttributeFor(Aresta.class, "id");
        workerXml.useAttributeFor(Aresta.class, "origem");
        workerXml.useAttributeFor(Aresta.class, "destino");
        workerXml.useAttributeFor(Aresta.class, "valor");
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
            String arquivoString = pathDoArquivo + "/" + grafo.getId() + ".xml";
            arquivoString = arquivoString.replace("//", "/").replace("\\\\","\\");
            File arquivo = new File(arquivoString);
            if (!arquivo.exists()){
                arquivo.createNewFile();
                FileWriter writer = new FileWriter(arquivo, true);
                writer.write(WorkerXml.writeGrafoInXmlString(grafo));
                writer.flush();
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
            return workerXml.toXML(grafo);
        } catch (Exception error) {
            return error.getMessage();
        }
    }
    
    /**
     * Esta função irá limpar todos os dados do WorkerXML para que possam ser 
     * criados novos grafos
     */
    public static void clearWorkerXml(){
        workerXml = new XStream(new DomDriver());
    }
    
    /**
     *
     * @param arquivo
     * @return
     * @throws java.io.IOException
     */
    public static Grafo grafoFromFile(File arquivo) throws IOException{
        constructXml();
        boolean created = arquivo.createNewFile();
        if(created){
            return (Grafo) workerXml.fromXML(arquivo);
        }
        return null;
    }
}
