package ru.geekbrains.trainingproject.market.controllertest.v1;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.geekbrains.trainingproject.market.model.Category;
import ru.geekbrains.trainingproject.market.model.Product;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ControllerTestFullServerRunTestProfile {
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

    @Test
    public void DeleteById() throws Exception {
        mvc.perform(get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON)).

                andDo(print()).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    //TODO Пока не разобрался с тестом запросов с параметром @RequestBody

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void save() throws Exception {
        Product product = new Product();
        product.setId(10000L);
        product.setTitle("testProduct");
        product.setPrice(100);
        Category category = new Category();
        category.setId(2L);
        category.setTitle("TestCategory");
        product.setCategory(category);
        Gson gson = new Gson();
        String json = gson.toJson(product);

        mvc.perform(post("/api/v1/products/product").content(json).contentType(MediaType.APPLICATION_JSON)).

                andDo(print())
                .andExpect(jsonPath("$",is(gson)))
                ;
    }
}
