package ru.geekbrains.trainingproject.market.controllertest.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.model.Category;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class MockBean {
    @Autowired
    private MockMvc mockMvc;

    @org.springframework.boot.test.mock.mockito.MockBean
    private ProductRepository productRepository;




    @Test
    public void test() throws Exception {
        Product product = new Product();
        product.setId(1l);
        product.setTitle("Milk");
        product.setPrice(10);
        Category category = new Category();
        category.setId(1l);
        category.setTitle("Food");
        category.setProducts(new ArrayList<>(Arrays.asList(product)));

        List<ProductDto> productList = new ArrayList<>(Arrays.asList(new ProductDto(product)));

        System.out.println(productList);
        given(productRepository.findAll()).willReturn(productList);
        mockMvc
                .perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)).
//                andDo(print());
    }
}
