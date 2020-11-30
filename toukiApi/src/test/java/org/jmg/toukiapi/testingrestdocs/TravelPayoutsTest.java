package org.jmg.toukiapi.testingrestdocs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class TravelPayoutsTest {

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
    @DisplayName("Should get countries")
    void testGetPays() throws Exception {
        webTestClient.get().uri("http://localhost:60010/travel-payouts-gateway/api/v1/pays")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Object.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("[].name").description("Le nom du pays").optional(),
                                        PayloadDocumentation.fieldWithPath("[].currency").description("La devise du pays").optional(),
                                        PayloadDocumentation.fieldWithPath("[].code").description("Le code iso2 du pays"),
                                        PayloadDocumentation.fieldWithPath("[].name_translations").description("Les traductions du nom du pays"),
                                        PayloadDocumentation.fieldWithPath("[].name_translations.en").description("Traduction en anglais")
                                )
                        )
                );
    }

    @Test
    @DisplayName("Should get cities")
    void testGetVilles() throws Exception {
        webTestClient.get().uri("http://localhost:60010/travel-payouts-gateway/api/v1/villes")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Object.class)
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "{methodName}",
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("").description("")
                                )
                        )
                );
    }

}
