package catalog;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Abstract class holding general information about an item
 *
 * @author Tudose Tudor-Cristian 2A2
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class),
        @JsonSubTypes.Type(value = Article.class)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item {
    protected int id;
    protected List<Concept> concepts;
    protected String title;
    protected String location;
}
