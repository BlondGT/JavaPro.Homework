package hm9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.createDirectories;

public class FileLogger {

    private final FileLoggerConfiguration configuration;

    public FileLogger(FileLoggerConfiguration configuration) {
        this.configuration = configuration;
        try {
            createDirectories(Path.of(configuration.path()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws IOException {

        var config = new FileLoggerConfiguration("logs9", LoggingLevel.INFO, 100);
        var logger = new FileLogger(config);

        logger.info("message");
        logger.debug("message");
    }

    public void info(String message) throws IOException {

        log(LoggingLevel.INFO, message);
    }

    public void debug(String message) throws IOException {

        if (configuration.level() == LoggingLevel.INFO) {
            return;
        }

        log(LoggingLevel.DEBUG, message);
    }

    private void log(LoggingLevel level, String message) throws IOException {

        File file = new File(configuration.path() + "/output.log");

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd-hh.mm.ss"));
        String outputMessage = String.format("%s [%s] Message: %s", time, level, message);

        var fileSize = file.exists() ? Files.size(file.toPath()) : 0;
        if (fileSize + outputMessage.getBytes(UTF_8).length > configuration.maxSize()) {
            throw new RuntimeException("FileMaxSizeReachException");

        }

        try (var writer = new FileWriter(file, true)) {
            writer.write(outputMessage);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
