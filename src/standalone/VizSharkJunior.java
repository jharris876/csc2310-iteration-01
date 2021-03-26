package standalone;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class VizSharkJunior {

    private Graph graph;
    private CSVJunior csv;

    public VizSharkJunior(String filename) {
        // Set the program to run using javafx GUI system
        System.setProperty("org.graphstream.ui", "javafx");

        // Create a new multigraph
        this.graph = new MultiGraph("g");

        // Create a stylesheet for nicely displaying the graph nodes and edges
        this.graph.setAttribute("ui.stylesheet", "url('file://graph.css')");

        // Configure the graph

        this.csv = new CSVJunior(graph, filename);
        this.graph.display();

        // Begin reading and displaying data on the graph
        this.csv.begin();
        this.csv.nextEvents();
        this.csv.end();
    }

    public static void main(String[] args) {
        String filename = "data/data2.csv";
        VizSharkJunior client = new VizSharkJunior(filename);
    }
}
