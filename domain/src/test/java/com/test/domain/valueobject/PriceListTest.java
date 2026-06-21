package com.test.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.PriceListMother;

@SpringBootTest(classes = DomainTestConfig.class)
public class PriceListTest {
    @Test
    void testPriceListShouldNotBeNull() {
        // Given
        PriceList priceList = PriceListMother.random();

        // Then
        assertNotNull(priceList);
        assertNotNull(priceList.value);
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNull() {
        // Given ~ PriceList will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            PriceList.create(null);
        });

        // Then
        assertEquals("PriceList value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNegative() {
        // Given ~ PriceList will be negative

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            PriceList.create(-1L);
        });

        // Then
        assertEquals("PriceList value cannot be negative", illegalArgumentException.getMessage());
    }
}
