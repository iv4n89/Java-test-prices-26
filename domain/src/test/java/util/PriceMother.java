package util;

import java.time.LocalDateTime;

import com.test.model.Price;
import com.test.valueobject.BrandId;
import com.test.valueobject.Currency;
import com.test.valueobject.Money;
import com.test.valueobject.PriceList;
import com.test.valueobject.Priority;
import com.test.valueobject.ProductId;

public final class PriceMother {
    public static Price from(
            BrandId brandId,
            ProductId productId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            PriceList priceList,
            Priority priority,
            Money price,
            Currency currency) {
        return Price.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }

    public static Price random() {
        return Price.builder()
                .brandId(BrandIdMother.random())
                .productId(ProductIdMother.random())
                .startDate(DateMother.randomLocalDateTime())
                .endDate(DateMother.randomLocalDateTime())
                .priceList(PriceListMother.random())
                .priority(PriorityMother.random())
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();
    }

    public static Price randomPriorityOne() {
        return Price.builder()
                .brandId(BrandIdMother.random())
                .productId(ProductIdMother.random())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(PriceListMother.random())
                .priority(PriorityMother.create(1))
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();

    }

    public static Price randomPriorityTwo() {
        return Price.builder()
                .brandId(BrandIdMother.random())
                .productId(ProductIdMother.random())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(PriceListMother.random())
                .priority(PriorityMother.create(2))
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();

    }
}
