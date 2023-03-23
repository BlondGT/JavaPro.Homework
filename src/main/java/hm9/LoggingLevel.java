package hm9;

public enum LoggingLevel {
    INFO("INFO"),
    DEBUG("DEBUG");

    private final String value;

    LoggingLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


