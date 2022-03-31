package exception;

/**
 * Custom exception thrown where a catalog is not initialized
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class NullCatalogException extends RuntimeException {
    public NullCatalogException(String message) {
        super("Null Catalog Exception: " + message);
    }
}
