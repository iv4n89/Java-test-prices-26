package com.test.infrastructure.repository;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.Price;
import com.test.domain.ports.output.PriceRepository;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;
import com.test.infrastructure.mapper.PriceDateMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Primary
@Component("priceRepository")
public final class H2PriceRepository implements PriceRepository {
    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Price findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId,
            LocalDateTime applicationDate) {
        return priceJpaRepository.findByBrandidAndProductIdAndStartDateAndEndDate(brandId.getValue(),
                productId.getValue(), applicationDate)
                .map(PriceDateMapper::toDomainModel)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}
