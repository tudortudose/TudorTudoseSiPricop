package command;

import catalog.Catalog;
import catalog.Item;
import exception.EmptyCatalogException;
import org.jetbrains.annotations.NotNull;

/**
 * List Command implementation
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class ListCommand implements Command {

    /**
     * It prints the items of a given catalog
     *
     * @param catalog the catalog to execute the command on
     * @throws EmptyCatalogException if the catalog has no items
     */
    @Override
    public void execute(@NotNull Catalog catalog) throws EmptyCatalogException {
        if (catalog.getItems().size() == 0) {
            throw new EmptyCatalogException("There are no items to print!");
        }
        for (Item i : catalog.getItems()) {
            System.out.println(i);
        }
        System.out.println();
    }
}
