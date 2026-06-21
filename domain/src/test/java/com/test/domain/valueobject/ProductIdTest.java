package com.test.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.ProductIdMother;

@SpringBootTest(classes = DomainTestConfig.class)
public class ProductIdTest {
    @Test
    void testProductIdShouldNotBeNull() {
        // Given
        ProductId productId = ProductIdMother.random();

        // Then
        assertNotNull(productId);
        assertNotNull(productId.getValue());
    }

    @Test
    void testProductIdShouldThrowExceptionWhenValueIsNull() {
        // Given ~ ProductId value will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            ProductId.create(null);
        });

        // Then
        assertEquals("ProductId value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testProductIdShouldThrowExceptionWhenValueIsNegative() {
        // Given ~ ProductId value will be negative

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            ProductId.create(-1L);
        });

        // Then
        assertEquals("ProductId value cannot be negative", illegalArgumentException.getMessage());
    }
}
