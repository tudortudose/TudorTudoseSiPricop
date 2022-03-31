package command;

import catalog.Catalog;
import exception.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * View Command implementation
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class ViewCommand implements Command {
    /**
     * It opens an item from the catalog using its native application
     *
     * @param catalog the catalog to execute the command on
     * @throws NullCatalogException if the catalog is null
     * @throws EmptyCatalogException if the catalog is empty
     */
    @Override
    public void execute(Catalog catalog) throws NullCatalogException {
        if (catalog == null) throw new NullCatalogException("Null catalog argument given in ViewCmd!");
        if (catalog.getItems().size() == 0) throw new NullCatalogException("Cant view item from empty catalog!");
        Random random = new Random();

        String path = catalog.getItems().get(random.nextInt(catalog.getItems().size())).getLocation();

        Desktop desktop = Desktop.getDesktop();
        File myFile = new File(path);
        try {
            desktop.open(myFile);
        } catch (IOException e) {
            System.out.println("IO expr: " + e.getMessage());
        }
    }
}
