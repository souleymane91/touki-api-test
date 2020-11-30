package org.jmg.toukiapi.testingrestdocs;

import org.jmg.toukiapi.config.AppParam;
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
public class SkyscannerTest {
    Logger log = LoggerFactory.getLogger(WebLayerTest.class);

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
    @DisplayName("Should get devises")
    void testGetDevises() throws Exception {
        webTestClient.get().uri(AppParam.APP_BASE_URL + "skyscanner-gateway/api/v1/devises/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Object.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("Currencies").description("Liste de toutes les devises"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].Code").description("Le code de la devise (sur trois lettres)"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].Symbol").description("Le symbole de la devise"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].ThousandsSeparator").description("Séparteur millier"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].DecimalSeparator").description("Séparateur décimal"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].SymbolOnLeft").description("Le symbole à gauche"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].SpaceBetweenAmountAndSymbol").description("Espace entre symbole et montant (true/false)"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].RoundingCoefficient").description("Coefficient d'arrondi"),
                                        PayloadDocumentation.fieldWithPath("Currencies[].DecimalDigits").description("Nombre de chiffres décimaux")
                                )
                        )
                );
    }

    @Test
    @DisplayName("Should get places")
    void testGetVilles() throws Exception {
        webTestClient.get().uri(AppParam.APP_BASE_URL + "skyscanner-gateway/apiservices/autosuggest/v1.0/SN/XOF/fr-FR/?query=Dakar")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Object.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("Places").description("Liste de tous les lieux"),
                                        PayloadDocumentation.fieldWithPath("Places[].PlaceId").description("L'id du lieux"),
                                        PayloadDocumentation.fieldWithPath("Places[].PlaceName").description("Le nom du lieux"),
                                        PayloadDocumentation.fieldWithPath("Places[].CountryId").description("L'id du pays correspondant"),
                                        PayloadDocumentation.fieldWithPath("Places[].RegionId").description("L'id de la région"),
                                        PayloadDocumentation.fieldWithPath("Places[].CityId").description("L'id de la ville"),
                                        PayloadDocumentation.fieldWithPath("Places[].CountryName").description("Le nom du pays")
                                )
                        )
                );
    }

}
