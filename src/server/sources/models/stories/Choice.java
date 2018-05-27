package server.sources.models.stories;

import java.io.Serializable;
import java.util.ArrayList;

public class Choice implements Serializable {

    private String description;
    private ArrayList<Option> options = new ArrayList<Option>();

    public Choice(String description, ArrayList<Option> options) {
        this.description = description;
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }
}
