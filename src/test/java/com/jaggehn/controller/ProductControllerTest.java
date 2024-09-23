package com.jaggehn.controller;

import com.jaggehn.dto.ProductDTO;
import com.jaggehn.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDTO productDTO;

    @BeforeEach
    public void setUp() {
        productDTO = new ProductDTO(1, "Product 1", 10, 100.0, "Test product");
    }

    @Test
    public void testAddProduct() throws Exception {
        Mockito.when(productService.saveProduct(any(ProductDTO.class))).thenReturn(productDTO);

        String productJson = "{ \"name\": \"Product 1\", \"quantity\": 10, \"price\": 100.0, \"description\": \"Test product\" }";

        mockMvc.perform(post("/api/product/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductDTO> products = Arrays.asList(productDTO);
        Mockito.when(productService.getProducts()).thenReturn(products);

        mockMvc.perform(get("/api/product/getProducts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Product 1"));
    }

    @Test
    public void testGetProductById() throws Exception {
        Mockito.when(productService.getProductById(anyInt())).thenReturn(productDTO);

        mockMvc.perform(get("/api/product/productById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Mockito.when(productService.updateProduct(any(ProductDTO.class))).thenReturn(productDTO);

        String productJson = "{ \"id\": 1, \"name\": \"Product 1\", \"quantity\": 10, \"price\": 100.0, \"description\": \"Test product\" }";

        mockMvc.perform(put("/api/product/updateProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Mockito.when(productService.deleteByProduct(anyInt())).thenReturn("Product Deleted !! 1");

        mockMvc.perform(delete("/api/product/delete/1"))
                .andExpect(status().isNoContent());
    }
}
