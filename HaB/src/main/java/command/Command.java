package command;

import catalog.*;
import exception.EmptyCatalogException;
import exception.NullCatalogException;

/**
 * Interface for a generic command
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public interface Command {
    /**
     * Method to be implemented by the classes that accepts this contract
     *
     * @param catalog the catalog to execute the command on
     * @throws EmptyCatalogException if the catalog is null
     * @throws NullCatalogException if the catalog is empty
     */
    public void execute(Catalog catalog) throws EmptyCatalogException, NullCatalogException;
}
