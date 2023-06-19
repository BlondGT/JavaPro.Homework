package bank.hm31.dummy;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class WiremockConfig {

        public final static WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());

        @Bean
        public WireMockServer wireMockServer() {
            return wireMockServer;
        }

        @PostConstruct
        public void startWireMockServer() {
            wireMockServer.start();
        }

        @PreDestroy
        public void stopWireMockServer() {
            wireMockServer.stop();
        }
}
