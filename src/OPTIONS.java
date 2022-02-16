import java.util.NoSuchElementException;

public enum OPTIONS {
    ADD_CONTACT (0, "Add contact"),
    SEARCH_BY_NAME (1, "Search by name"),
    SEARCH_BY_PHONE (2, "Search by phone no"),
    DELETE (3, "Delete"),
    CLOSE(4, "End");

    private final int shortcut;
    private final String description;

    OPTIONS(int shortcut, String description) {
        this.shortcut = shortcut;
        this.description = description;
    }

    public int getShortcut() {
        return shortcut;
    }

    static OPTIONS convertToOptions (int option) {
        if (option >= values().length || option < 0)
            throw new NoSuchElementException();
        return values()[option];
    }

    @Override
    public String toString() {
        return shortcut + " - " + description;
    }
}
