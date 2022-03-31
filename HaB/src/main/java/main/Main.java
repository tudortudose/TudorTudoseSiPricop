package main;

import catalog.*;
import command.*;
import exception.EmptyCatalogException;
import exception.NullCatalogException;
import graph.BipartiteGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * In the Main class:
 * -a catalog is created;
 * -the commands are demonstrated on the catalog
 * -computing the maximum matching for the catalog graph
 * -computing the minimum edge cover for the catalog graph
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        try{
            Command command=new ListCommand();
            command.execute(catalog);

            command = new SaveCommand();
            command.execute(catalog);

            command = new LoadCommand();
            command.execute(null);

            command = new ReportCommand();
            command.execute(catalog);

            command = new ViewCommand();
            command.execute(null);

        } catch (EmptyCatalogException e) {
            System.out.println("Empty Catalog: " + e.getMessage());;
        } catch (NullCatalogException e) {
            System.out.println("Null Catalog: " + e.getMessage());;
        }


        BipartiteGraph graph = catalog.constructGraph();

        Set<DefaultEdge> d = graph.getMaxMatch();

        printSet(graph.getMaxMatch(), "Maximum matching:");
        printSet(graph.getMinCover(), "Minimum edge cover:");
    }

    /**
     * Helper method used to print the elements of a given set
     *
     * @param set         the given set
     * @param description a description a a set
     */
    private static void printSet(Set<?> set, String description) {
        System.out.println(description);
        for (Object o : set) {
            System.out.println(o);
        }
        System.out.println();
    }
}
