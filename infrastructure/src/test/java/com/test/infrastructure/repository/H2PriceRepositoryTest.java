package com.test.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.Price;
import com.test.domain.ports.output.PriceRepository;
import com.test.domain.util.BrandIdMother;
import com.test.domain.util.CurrencyMother;
import com.test.domain.util.MoneyMother;
import com.test.domain.util.PriceListMother;
import com.test.domain.util.PriorityMother;
import com.test.domain.util.ProductIdMother;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;
import com.test.infrastructure.InfrastructureTestConfig;
import com.test.infrastructure.entity.PriceEntity;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = InfrastructureTestConfig.class)
public class H2PriceRepositoryTest {
    @Autowired
    @Qualifier("priceRepositoryTest")
    private PriceRepository priceRepository;

    @Autowired
    @Qualifier("priceJpaRepositoryTest")
    private PriceJpaRepository priceJpaRepository;

    @Test
    void testFindPriceByBrandIdAndProductIdAndApplicationDate() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime applicationDate = LocalDateTime.now();
        PriceEntity expected = PriceEntity.builder()
                .brandId(brandId.getValue())
                .productId(productId.getValue())
                .startDate(applicationDate.minusDays(1))
                .endDate(applicationDate.plusDays(1))
                .price(MoneyMother.random().getValue())
                .curr(CurrencyMother.random().getValue())
                .priceList(PriceListMother.random().getValue())
                .priority(PriorityMother.random().getValue())
                .build();
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateAndEndDate(
                brandId.getValue(), productId.getValue(), applicationDate))
                .thenReturn(Optional.of(expected));

        // When
        Price actual = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
                brandId, productId, applicationDate);

        // Then
        assertNotNull(actual);
        assertEquals(expected.getBrandId(), actual.getBrandId().getValue());
        assertEquals(expected.getProductId(), actual.getProductId().getValue());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getPrice(), actual.getPrice().getValue());
        assertEquals(expected.getCurr(), actual.getCurrency().getValue());
        assertEquals(expected.getPriceList(), actual.getPriceList().getValue());
        assertEquals(expected.getPriority(), actual.getPriority().getValue());

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateAndEndDate(
                        brandId.getValue(), productId.getValue(), applicationDate);
        verifyNoMoreInteractions(priceJpaRepository);
    }

    @Test
    void testFindPriceByBrandIdAndProductIdAndApplicationDateWithNoResults() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime applicationDate = LocalDateTime.now();
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateAndEndDate(
                brandId.getValue(), productId.getValue(), applicationDate))
                .thenThrow(new PriceNotFoundException("Price not found"));

        // When
        PriceNotFoundException priceNotFoundException = assertThrows(
                PriceNotFoundException.class,
                () -> priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
                        brandId, productId, applicationDate));

        // Then
        assertNotNull(priceNotFoundException);
        assertEquals("Price not found", priceNotFoundException.getMessage());

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateAndEndDate(
                        brandId.getValue(), productId.getValue(), applicationDate);
        verifyNoMoreInteractions(priceJpaRepository);
    }
}
