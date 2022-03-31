package catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * An extension of the Catalog.Item class, adding extra data members
 *
 * @author Tudose Tudor-Cristian 2A2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Book extends Item {
    private int year;
    private String author;
    private String type;

    /**
     * The all args constructor which is annotated in order to be used for deserialization
     *
     * @param id book id
     * @param title book title
     * @param location book path file
     * @param year year of publication
     * @param author book author
     * @param type book type
     */
    @JsonCreator
    public Book(@JsonProperty("id") int id, @JsonProperty("concepts") List<Concept> concepts,
                @JsonProperty("title") String title, @JsonProperty("location") String location,
                @JsonProperty("year") int year,@JsonProperty("author") String author,
                @JsonProperty("type") String type) {
        super(id, concepts, title, location);
        this.year = year;
        this.author = author;
        this.type = type;
    }
}
