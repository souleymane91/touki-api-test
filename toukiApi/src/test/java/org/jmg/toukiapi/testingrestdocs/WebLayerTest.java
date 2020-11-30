package org.jmg.toukiapi.testingrestdocs;

import org.jmg.toukiapi.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class WebLayerTest {
    Logger log = LoggerFactory.getLogger(WebLayerTest.class);

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testGreeting() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andDo(log())
//                .andExpect(status().isOk())
//                .andExpect(content().json("{'message': 'Hello, From toukiAPI'}"))
//                .andDo(
//                        document(
//                                "{methodName}",
//                                responseFields(
//                                        fieldWithPath("message").description("The welcome message for the user")
//                                )
//                        )
//                );
//    }
}
