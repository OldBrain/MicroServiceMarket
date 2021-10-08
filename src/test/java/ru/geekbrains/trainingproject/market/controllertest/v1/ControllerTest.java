package ru.geekbrains.trainingproject.market.controllertest.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@Profile("test")
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mvc;
//    @MockBean
//    ProductRepository productRepository;

    @Test
    public void GetAllProductsTest() throws Exception {
        mvc.
                perform(get("/api/v1/products")).
                andDo(print()).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void findByIdTest() throws Exception {
        mvc.perform(get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON)).

                andDo(print()).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

}
