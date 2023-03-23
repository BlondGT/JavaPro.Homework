package hm9;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hm9.FileLoggerConfigurationLoader.load;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoggerConfigurationLoaderTest {

    @Test
    void shouldLoad() throws IOException {


        assertEquals(new FileLoggerConfiguration("logs9", LoggingLevel.DEBUG, 100),
                load("/logs-config.properties"));
    }

}