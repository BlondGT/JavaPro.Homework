package hm9;

public record FileLoggerConfiguration(
        String path,
        LoggingLevel level,
        int maxSize
) {
}

