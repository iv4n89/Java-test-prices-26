package com.test.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.test.application.PriceApplicationTestConfig;
import com.test.application.command.PriceFinderCommand;
import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.Price;
import com.test.domain.ports.output.PriceRepository;
import com.test.domain.util.BrandIdMother;
import com.test.domain.util.DateMother;
import com.test.domain.util.PriceMother;
import com.test.domain.util.ProductIdMother;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = PriceApplicationTestConfig.class)
public class PriceFinderTest {
    @Autowired
    @Qualifier("priceFinderTest")
    private PriceFinder priceFinder;

    @Autowired
    @Qualifier("priceRepositoryTest")
    private PriceRepository priceRepository;

    @Test
    void testFindAPrice() {
        // Given
        Price expected = PriceMother.random();
        when(priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any()))
                .thenReturn(expected);
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime date = DateMother.randomLocalDateTime();
        PriceFinderCommand command = new PriceFinderCommand(brandId, productId, date);

        // When
        Price actual = priceFinder.findPrice(command);

        // Then
        verify(priceRepository, times(1)).findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindAPriceAndThrowExceptionWhenNotFound() {
        // Given
        LocalDateTime date = DateMother.randomLocalDateTime();
        when(priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any()))
                .thenThrow(new PriceNotFoundException("Price not found"));

        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        PriceFinderCommand command = new PriceFinderCommand(brandId, productId, date);

        // When
        PriceNotFoundException priceNotFoundException = assertThrows(PriceNotFoundException.class, () -> {
            priceFinder.findPrice(command);
        });

        // Then
        verify(priceRepository, times(1)).findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(priceNotFoundException);
        assertEquals("Price not found", priceNotFoundException.getMessage());
    }
}
