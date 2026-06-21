package com.test.application.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.application.PriceApplicationTestConfig;
import com.test.domain.util.BrandIdMother;
import com.test.domain.util.DateMother;
import com.test.domain.util.ProductIdMother;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

@SpringBootTest(classes = PriceApplicationTestConfig.class)
public class PriceFinderCommandTest {
    @Test
    void testPriceFinderCommandShouldBeCreated() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime date = DateMother.randomLocalDateTime();

        // When
        PriceFinderCommand priceFinderCommand = new PriceFinderCommand(brandId, productId, date);

        // Then
        assertNotNull(priceFinderCommand);
        assertEquals(brandId.getValue(), priceFinderCommand.brandId().getValue());
        assertEquals(productId.getValue(), priceFinderCommand.productId().getValue());
        assertEquals(date, priceFinderCommand.date());
    }
}
