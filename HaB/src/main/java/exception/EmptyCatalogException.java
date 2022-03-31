package exception;

/**
 * Custom exception thrown where a catalog contains no items
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class EmptyCatalogException extends RuntimeException {
    public EmptyCatalogException(String message){
        super("Empty Catalog Exception: " + message);
    }
}
