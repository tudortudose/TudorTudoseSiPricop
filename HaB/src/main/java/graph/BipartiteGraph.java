package graph;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

/**
 * This class is a representation of a bipartite graph
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class BipartiteGraph {
    private Graph<String, DefaultEdge> graph;
    private Set<String> partition1;
    private Set<String> partition2;

    /**
     * All args constructor
     *
     * @param graph the given graph (nodes and edges)
     * @param partition1 the first partition
     * @param partition2 the second partition
     */
    public BipartiteGraph(Graph<String, DefaultEdge> graph, Set<String> partition1, Set<String> partition2) {
        this.graph = graph;
        this.partition1 = partition1;
        this.partition2 = partition2;
    }

    /**
     * It computes the maximal set of edges such as every two of them are not adjacent
     * It uses the jgrapht specific algorithm for this task
     *
     * @return the maximal set of independent edges
     */
    public Set<DefaultEdge> getMaxMatch() {
        MatchingAlgorithm<String, DefaultEdge> matching = new MaximumWeightBipartiteMatching<>(this.graph, this.partition1, this.partition2);
        Set<DefaultEdge> edges = matching.getMatching().getEdges();
        return edges;
    }

    /**
     * It computes the minimal set of edges such as every node is adjacent with at least one edge from the set
     * It uses the getMaxMatch result and adds the edges between nodes that are not already adjacent with edges in the set
     *
     * @return the minimum cover set of edges
     */
    public Set<DefaultEdge> getMinCover() {
        MatchingAlgorithm<String, DefaultEdge> matching = new MaximumWeightBipartiteMatching<>(this.graph, this.partition1, this.partition2);
        Set<DefaultEdge> edges = matching.getMatching().getEdges();

        for (String vertex : this.graph.vertexSet()) {
            if (!matching.getMatching().isMatched(vertex)) {
                for (DefaultEdge edge : this.graph.edgeSet()) {
                    if (!edges.contains(edge) && (this.graph.getEdgeSource(edge)).equals(vertex) || this.graph.getEdgeTarget(edge).equals(vertex)) {
                        edges.add(edge);
                        break;
                    }
                }
            }
        }
        return edges;
    }
}
