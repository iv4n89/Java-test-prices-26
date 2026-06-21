package com.test.application;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.application.usecases.PriceFinder;
import com.test.domain.ports.output.PriceRepository;

@SpringBootApplication(scanBasePackages = "com.test")
public class PriceApplicationTestConfig {
    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return mock(PriceRepository.class);
    }

    @Bean(name = "priceFinderTest")
    public PriceFinder priceFinderTest() {
        return new PriceFinder(priceRepository());
    }
}
