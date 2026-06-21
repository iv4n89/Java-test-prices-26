package com.test.application.usecases;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.test.application.command.PriceFinderCommand;
import com.test.domain.model.Price;
import com.test.domain.ports.output.PriceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("priceFinder")
@Primary
public final class PriceFinder {
    private final PriceRepository priceRepository;

    public Price findPrice(PriceFinderCommand command) {
        return priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(command.brandId(), command.productId(),
                command.date());
    }
}
