package com.test.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.test.domain.AggregateRoot;
import com.test.domain.exceptions.PriceDomainException;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.Currency;
import com.test.domain.valueobject.Money;
import com.test.domain.valueobject.PriceList;
import com.test.domain.valueobject.Priority;
import com.test.domain.valueobject.ProductId;

public final class Price implements AggregateRoot {
    private final ProductId productId;
    private final BrandId brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final PriceList priceList;
    private final Priority priority;
    private final Money price;
    private final Currency currency;

    private Price(Builder builder) {
        if (builder.startDate.isAfter(builder.endDate)) {
            throw new PriceDomainException("Start date cannot be after end date");
        }

        this.productId = builder.productId;
        this.brandId = builder.brandId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.priceList = builder.priceList;
        this.priority = builder.priority;
        this.price = builder.price;
        this.currency = builder.currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer comparePriority(Price price) {
        return this.priority.getValue() - price.priority.getValue();
    }

    public ProductId getProductId() {
        return this.productId;
    }

    public BrandId getBrandId() {
        return this.brandId;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public PriceList getPriceList() {
        return this.priceList;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Money getPrice() {
        return this.price;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public static final class Builder {
        private BrandId brandId;
        private ProductId productId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private PriceList priceList;
        private Priority priority;
        private Money price;
        private Currency currency;

        private Builder() {
        }

        public Builder brandId(BrandId brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder priceList(PriceList priceList) {
            this.priceList = priceList;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Price that = (Price) obj;
        return Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(priceList, that.priceList)
                && Objects.equals(productId, that.productId)
                && Objects.equals(priority, that.priority);
    }
}
