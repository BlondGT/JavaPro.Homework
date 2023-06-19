package bank.hm31;

import bank.hm31.response.CurrencyApiResponse;
import bank.hm31.response.CurrencyApiResponseMeta;
import bank.hm31.response.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CurrencyControllerIntegrationTest {

    protected WireMockServer wireMockServer;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @DynamicPropertySource
    public static void registerWiremockBaseUrl(DynamicPropertyRegistry registry) {

        registry.add("wiremock:baseurl", WiremockConfig.wireMockServer::baseUrl);
    }

    @Test
    void shouldGetCurrencyConverter() throws Exception {

        var body = CurrencyApiResponse.builder()
                .meta(CurrencyApiResponseMeta.builder()
                        .lastTimeUpdated(Instant.now())
                        .build())
                .data(Map.of("EUR", ResponseCode.builder()
                        .code("EUR")
                        .value(0.25)
                        .build()))
                .build();

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/apiCurrency"))
                .withQueryParam("apikey", equalTo("098765"))
                .withQueryParam("base_currency", equalTo("UAH"))
                .withQueryParam("currencies", equalTo("EUR"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(body))));

        var query = MockMvcRequestBuilders.get("/currencies/convert")
                .param("from", "UAH")
                .param("to", "EUR")
                .param("amount", "100");

        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var result = objectMapper.readValue(response, CurrencyApiResponse.class);

        assertEquals(25, result.getData().get("EUR").getValue());
    }
}