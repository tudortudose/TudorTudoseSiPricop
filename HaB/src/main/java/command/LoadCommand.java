package command;

import catalog.Catalog;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.NullCatalogException;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Load Command implementation
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class LoadCommand implements Command {
    /**
     * It loads a catalog from a json file
     *
     * @param catalog the catalog to execute the command on
     * @throws NullCatalogException if the catalog is null
     */
    @Override
    public void execute(Catalog catalog) throws NullCatalogException {
        if (catalog == null) throw new NullCatalogException("Null catalog argument given in LoadCmd!");

        ObjectMapper mapper = new ObjectMapper();
        String path = "./src/main/resources/json/catalogIn.json";

        try {
            Catalog loadedCatalog = mapper.readValue(Paths.get(path).toFile(), Catalog.class);
            catalog.setItems(loadedCatalog.getItems());
        } catch (StreamReadException e) {
            System.out.println("Stream expr: " + e.getMessage());
        } catch (DatabindException e) {
            System.out.println("Databind expr: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO expr: " + e.getMessage());
        }
    }
}
