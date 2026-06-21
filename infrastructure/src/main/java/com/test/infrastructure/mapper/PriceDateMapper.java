package com.test.infrastructure.mapper;

import com.test.domain.model.Price;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.Currency;
import com.test.domain.valueobject.Money;
import com.test.domain.valueobject.Priority;
import com.test.domain.valueobject.ProductId;
import com.test.infrastructure.entity.PriceEntity;

public final class PriceDateMapper {
    public static Price toDomainModel(PriceEntity priceEntity) {
        return Price.builder()
                .brandId(BrandId.create(priceEntity.getBrandId()))
                .productId(ProductId.create(priceEntity.getProductId()))
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .price(Money.create(priceEntity.getPrice()))
                .currency(Currency.create(priceEntity.getCurr()))
                .priority(Priority.create(priceEntity.getPriority()))
                .build();
    }

    public static PriceEntity toEntity(Price price) {
        return PriceEntity.builder()
                .brandId(price.getBrandId().getValue())
                .productId(price.getProductId().getValue())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice().getValue())
                .curr(price.getCurrency().getValue())
                .priority(price.getPriority().getValue())
                .build();
    }
}
