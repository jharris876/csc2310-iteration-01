package standalone;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.Sink;
import standalone.TSharkData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Basic CSV Generator
 */
public class CSVJunior implements Generator {

    private Graph graph;
    private Scanner scan;
    private String filename;

    /**
     * Initializes the class
     * @param graph Used to display network data
     * @param fn Filename for the CSV input data
     */
    public CSVJunior(Graph graph, String fn) {

        this.graph = graph;
        this.filename = fn;

    }

    /**
     * instantiates the CSV processing by opening the file and instantiating the Scanner object 
     * to be used by the nextEvents method
     */
    @Override
    public void begin() {
        File file = new File(filename);

        try {
             scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * processes the data by using the scanner object (e.g., while (scan.hasNext())).
     * @return returns true if at least one entity (a Node or an Edge) is read and added to a graph.
     */
    @Override
    public boolean nextEvents() {

            boolean returnValue = false;

            while (scan.hasNextLine()) {
                TSharkData data = new TSharkData(scan.nextLine());
                String srcNode = data.getSrcIP();
                String dstNode = data.getDstIP();
                if (!this.checkId(srcNode) && !this.checkId(dstNode)) {
                    Edge edge = graph.getEdge(srcNode + ":" + dstNode);
                    if (edge == null) {
                        Node source = graph.getNode(srcNode);
                        Node destination = graph.getNode(dstNode);

                        if (source == null) {

                            source = this.graph.addNode(srcNode);
                            source.setAttribute("label", srcNode);
                        }
                        if (destination == null) {

                            destination = this.graph.addNode(dstNode);
                            destination.setAttribute("label", dstNode);
                        }
                        edge = this.graph.addEdge(srcNode + ":" + dstNode, source, destination, true);
                        edge.setAttribute("label", 1);
                    } else {
                        Integer weight = (Integer) edge.getAttribute("label");
                        edge.setAttribute("label", weight + 1);
                    }
                }
                returnValue = true;
            }
        return returnValue;
    }
    /** processing by closing the Scanner object by calling the close() method
     */
    @Override
    public void end() {
        scan.close();
    }

    @Override
    public void addSink(Sink sink) {
        this.graph = (Graph) sink;
    }

    @Override
    public void removeSink(Sink sink) {
        this.graph = null;
    }

    @Override
    public void addAttributeSink(AttributeSink attributeSink) {
        this.graph = (Graph) attributeSink;
    }

    @Override
    public void removeAttributeSink(AttributeSink attributeSink) {
        this.graph = null;
    }

    @Override
    public void addElementSink(ElementSink elementSink) {
        this.graph = (Graph) elementSink;
    }

    @Override
    public void removeElementSink(ElementSink elementSink) {
        this.graph = null;
    }

    @Override
    public void clearElementSinks() {
        this.graph = null;
    }

    @Override
    public void clearAttributeSinks() {
        this.graph = null;
    }

    @Override
    public void clearSinks() {
        this.graph = null;
    }

    public boolean checkId(String id){
        return (id.contentEquals("") || id.contentEquals("id.dst") || id.contentEquals("ip.src") || id.contentEquals("255.255.255.255") || id.contentEquals("0.0.0.0"));
    }

}

