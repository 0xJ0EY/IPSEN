package server.sources.models.stories;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates a Choice.
 * @author Richard Kerkvliet
 */
public class Choice implements Serializable {

    private String description;
    private ArrayList<Option> options = new ArrayList<Option>();

    /**
     * Creates a Choice
     * @param description
     * @param options
     * @author Richard Kerkvliet
     */
    public Choice(String description, ArrayList<Option> options) {
        this.description = description;
        this.options = options;
    }

    /**
     * returns the description of the Choice
     * @return String description
     * @author Richard Kerkvliet
     */
    public String getDescription() {
        return description;
    }

    /**
     * returns the Optinos of the Choice
     * @return Option option
     * @author Richard Kerkvliet
     */
    public ArrayList<Option> getOptions() {
        return options;
    }
}
