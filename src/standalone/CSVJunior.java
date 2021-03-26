package standalone;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.Sink;
import server.TSharkData;

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
    }

    /**
     * instantiates the CSV processing by opening the file and instantiating the Scanner object 
     * to be used by the nextEvents method
     */
    @Override
    public void begin() {

    }

    /**
     * processes the data by using the scanner object (e.g., while (scan.hasNext())). 
     * @return returns true if at least one entity (a Node or an Edge) is read and added to a graph.
     */
    @Override
    public boolean nextEvents() {
        return false;
    }

    /**
     * ends processing by closing the Scanner object by calling the close() method
     */
    @Override
    public void end() {
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
}
