package hm9;

import java.io.IOException;
import java.util.Properties;

public class FileLoggerConfigurationLoader {

    public static FileLoggerConfiguration load(String configPath) throws IOException {

        var props = new Properties();
        props.load(FileLoggerConfiguration.class.getResourceAsStream(configPath));

        var path = (String) props.get("path");
        var level = LoggingLevel.valueOf(props.get("level").toString());
        var maxSize = Integer.parseInt(props.get("max-size").toString());


        return new FileLoggerConfiguration(path, level, maxSize);
    }

    public static void main(String[] args) throws IOException {

       var config =  load("/logs-config.properties");

        System.out.println(config);
    }
}
