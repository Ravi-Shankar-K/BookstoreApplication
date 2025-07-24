package com.rsk.bookstoreapp.catalog.domain;

import com.rsk.bookstoreapp.catalog.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
//                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        })
@Import(TestcontainersConfiguration.class)
@Sql("/reset-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

}