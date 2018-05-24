package server.sources.models.stories;

import java.util.ArrayList;

public class Choice {

    private ArrayList<Option> options = new ArrayList<Option>();

    public Choice(ArrayList<Option> options) {
        this.options = options;
    }
}
