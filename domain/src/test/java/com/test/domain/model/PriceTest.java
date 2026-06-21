package com.test.domain.model;

import com.test.domain.DomainTestConfig;
import com.test.domain.exceptions.PriceDomainException;
import com.test.domain.util.*;
import com.test.domain.valueobject.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DomainTestConfig.class)
class PriceTest {
    @Test
    void testPriceShouldBeCreated() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        Price expected = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertEquals(brandId, expected.getBrandId());
        assertEquals(startDate, expected.getStartDate());
        assertEquals(endDate, expected.getEndDate());
        assertEquals(priceList, expected.getPriceList());
        assertEquals(productId, expected.getProductId());
        assertEquals(priority, expected.getPriority());
        assertEquals(price, expected.getPrice());
        assertEquals(currency, expected.getCurrency());
    }

    @Test
    void testPriceShouldThrowDomainExceptionWhenStartDateIsAfterEndDate() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        PriceDomainException expected = assertThrows(
                PriceDomainException.class,
                () -> PriceMother.from(
                        brandId, productId, startDate, endDate, priceList, priority, price, currency));

        // Then
        assertNotNull(expected);
        assertEquals("Start date cannot be after end date", expected.getMessage());
    }

    @Test
    void testPriceShouldBeEqualWhenBrandIdProductIdAndStartDateAreEqual() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        Price expected = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority, price, currency);
        Price actual = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testPriceHashCode() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        Price expected = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority, price, currency);
        Price actual = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertNotNull(actual);
    }

    @Test
    void testPriceShouldHaveMorePriorityThanASecondOne() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority1 = Priority.create(1);
        Priority priority2 = Priority.create(2);
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        Price priceModel1 = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority1, price, currency);

        Price priceModel2 = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority2, price, currency);

        Integer expected = 1;

        // When
        Integer actual = priceModel2.comparePriority(priceModel1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testPriceShouldHaveLessPriorityThanASecondOne() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority1 = Priority.create(1);
        Priority priority2 = Priority.create(2);
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        Price priceModel1 = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority1, price, currency);

        Price priceModel2 = PriceMother.from(
                brandId, productId, startDate, endDate, priceList, priority2, price, currency);

        Integer expected = -1;

        // When
        Integer actual = priceModel1.comparePriority(priceModel2);

        // Then
        assertEquals(expected, actual);
    }
}