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
public class Article extends Item {
    private int year;
    private String author;

    /**
     * The all args constructor which is annotated in order to be used for deserialization
     *
     * @param id article id
     * @param title article title
     * @param location article path file
     * @param year year of publication
     * @param author article author
     */
    @JsonCreator
    public Article(@JsonProperty("id") int id, @JsonProperty("concepts") List<Concept> concepts,
                   @JsonProperty("title") String title, @JsonProperty("location") String location,
                   @JsonProperty("year") int year, @JsonProperty("author") String author) {
        super(id, concepts, title, location);
        this.year = year;
        this.author = author;
    }
}
