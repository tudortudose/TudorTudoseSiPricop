package catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import graph.BipartiteGraph;
import lombok.Data;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.nio.file.Paths;
import java.util.*;

/**
 * The Catalog.Catalog holds a list of items (books and articles) and offers methods to manipulated it
 *
 * @author Tudose Tudor-Cristian 2A2
 */
@Data
public class Catalog {
    private List<Item> items;

    /**
     * The constructor initialises the list of items
     */
    public Catalog() {
        items = new ArrayList<>();
        populate();
    }

    /**
     * Helper method used to manually populate the item list in the catalog
     */
    private void populate(){
        this.items.add(new Book(0, Arrays.asList(Concept.C0, Concept.C1, Concept.C2), "BTitle1", "C:/book1", 2001, "Author1", "type1"));
        this.items.add(new Book(1, Arrays.asList(Concept.C0, Concept.C3), "BTitle2", "C:/book2", 2002, "Author2", "type2"));
        this.items.add(new Book(2, Arrays.asList(Concept.C1, Concept.C2, Concept.C3), "BTitle3", "C:/book3", 2003, "Author3", "type1"));
        this.items.add(new Book(3, Arrays.asList(Concept.C0, Concept.C3, Concept.C5), "BTitle4", "C:/book4", 2004, "Author4", "type2"));
        this.items.add(new Book(4, Arrays.asList(Concept.C5, Concept.C6), "BTitle5", "C:/book5", 2004, "Author1", "type2"));
        this.items.add(new Book(5, Arrays.asList(Concept.C1, Concept.C7), "BTitle6", "C:/book6", 2003, "Author3", "type3"));
        this.items.add(new Article(6, Arrays.asList(Concept.C1, Concept.C5), "Article1", "C:/article1", 2004, "Author4"));
        this.items.add(new Article(7, Arrays.asList(Concept.C1, Concept.C3), "Article2", "C:/article2", 2004, "Author5"));
        this.items.add(new Article(8, Arrays.asList(Concept.C4, Concept.C6, Concept.C7), "Article3", "C:/article3", 2004, "Author2"));
        this.items.add(new Article(9, Arrays.asList(Concept.C1, Concept.C3), "Article4", "C:/article4", 2004, "Author2"));
        this.items.add(new Article(10, Arrays.asList(Concept.C1, Concept.C3), "Article5", "C:/article5", 2004, "Author5"));
    }

    /**
     * It adds an item into the list
     *
     * @param item the item to be added
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * In constructs a bipartite graph representing the relationships between
     * the items in the catalog and their afferent concepts
     *
     * @return a bipartite graph
     */
    public BipartiteGraph constructGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Set<String> partition1=this.constructItemsPartition();
        Set<String> partition2=this.constructConceptsPartition();

        for (String v : partition1) graph.addVertex(v);
        for (String v : partition2) graph.addVertex(v);

        for (Integer i = 0; i < this.getItems().size(); i++) {
            for (Concept concept : this.getItems().get(i).getConcepts()) {
                graph.addEdge("item " + i.toString(), "concept " + concept.name());
            }
        }
        return new BipartiteGraph(graph, partition1, partition2);
    }

    /**
     * Helper function to construct the items partition of the bipartite graph
     *
     * @return the nodes of the items partition
     */
    private Set<String> constructItemsPartition() {
        Set<String> partition = new HashSet<>();
        for (Integer i = 0; i < this.getItems().size(); i++) {
            partition.add("item " + i.toString());
        }
        return partition;
    }

    /**
     * Helper function to construct the concepts partition of the bipartite graph
     *
     * @return the nodes of the concepts partition
     */
    private Set<String> constructConceptsPartition() {
        Set<String> partition = new HashSet<>();
        for (int i = 0; i < this.getItems().size(); i++) {
            for (Concept concept : this.getItems().get(i).getConcepts()) {
                partition.add("concept " + concept.name());
            }
        }
        return partition;
    }
}
