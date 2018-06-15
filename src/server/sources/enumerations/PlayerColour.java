package server.sources.enumerations;

public enum PlayerColour {
    YELLOW(0, "#f4f442"),
    RED(1, "#d62828"),
    BLUE(2, "#478dff"),
    GREEN(3, "#13c100");

    private int index;
    private String hex;

    private PlayerColour(int index, String hex) {
        this.index = index;
        this.hex = hex;
    }

    public static PlayerColour getColourByIndex(int index) {

        for (PlayerColour playerColour : PlayerColour.values()) {
            if (playerColour.index == index) return playerColour;
        }

        return null;
    }

    public String getHex() {
        return hex;
    }
}
