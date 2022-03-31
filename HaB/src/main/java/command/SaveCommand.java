package command;

import catalog.Catalog;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.NullCatalogException;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Save Command implementation
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class SaveCommand implements Command {
    /**
     * It saves a given catalog in a json file
     *
     * @param catalog the catalog to execute the command on
     * @throws NullCatalogException if the catalog is null
     */
    @Override
    public void execute(Catalog catalog) throws NullCatalogException {
        if (catalog == null) throw new NullCatalogException("Null catalog argument given in SaveCmd!");

        ObjectMapper mapper = new ObjectMapper();
        String path = "./src/main/resources/json/catalogOut.json";

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(path).toFile(), catalog);
        } catch (StreamWriteException e) {
            System.out.println("Stream Write expr: " + e.getMessage());
        } catch (DatabindException e) {
            System.out.println("Databind expr: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO expr: " + e.getMessage());
        }
    }
}
