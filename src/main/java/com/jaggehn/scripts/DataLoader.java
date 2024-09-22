package com.jaggehn.scripts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.jaggehn.entity.Product;
import com.jaggehn.repository.ProductRepository;

@Configuration
public class DataLoader {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Environment env;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            String seed = env.getProperty("app.seedDatabase");
            System.out.println("app.seedDatabase property is: " + seed);

            if ("true".equals(seed)) {
                for (int i = 1; i <= 30; i++) {
                    Product product = new Product();
                    product.setName("Product " + i);
                    product.setQuantity(i * 10);
                    product.setPrice(i * 100.00);
                    product.setDescription("Description for Product " + i);
                    productRepository.save(product);
                }
                System.out.println("Inserted 30 products into the database.");
            }
        };
    }
}

