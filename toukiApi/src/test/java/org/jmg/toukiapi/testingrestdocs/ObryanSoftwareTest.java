package org.jmg.toukiapi.testingrestdocs;

import org.jmg.toukiapi.config.AppParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ObryanSoftwareTest {
    Logger log = LoggerFactory.getLogger(ObryanSoftwareTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;


    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToApplicationContext(this.context)
                .configureClient()
                .filter(WebTestClientRestDocumentation.documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    @DisplayName("Should init search flight")
    void testInitSearchFlight() throws Exception {
        webTestClient.get().uri(AppParam.APP_BASE_URL + "obryan-software-gateway/GetPricesAPI/StartFlightSearch.aspx?lapinfant=0&child=0&city2=NYC&date1=2021-01-01&youth=0&flightType=1&adults=1&cabin=1&infant=0&city1=LAX&seniors=0&date2=2021-01-02&islive=true")
                .exchange()
                .expectStatus().isOk()
                .expectBody(JSONObject.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("SearchID").description("L'id de la recherche à renseigner pour avoir les détails sur les prix de vol")
                                )
                        )
                );
    }

    @Test
    @DisplayName("Should get prices")
    void testGetPrices() throws Exception {
        webTestClient.get().uri(AppParam.APP_BASE_URL + "obryan-software-gateway/GetPricesAPI/GetPrices.aspx?SearchID=ap-K3ishzWpPx8EQGua")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Object.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                        PayloadDocumentation.responseFields(
                                            PayloadDocumentation.fieldWithPath("[].site").description("La compagnie correspond au vol"),
                                            PayloadDocumentation.fieldWithPath("[].lowestPrice").description("Le prix le plus bas proposé par la compagnie"),
                                            PayloadDocumentation.fieldWithPath("[].url").description("Lien sur le site de la compagnie"),
                                            PayloadDocumentation.fieldWithPath("[].pricePer").description("Prix par (ex. Price per passenger)")
                                )
                        )
                );
    }

}
