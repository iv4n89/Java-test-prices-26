package com.test.infrastructure;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.domain.ports.output.PriceRepository;
import com.test.infrastructure.repository.H2PriceRepository;
import com.test.infrastructure.repository.PriceJpaRepository;

@SpringBootApplication(scanBasePackages = "com.test")
public class InfrastructureTestConfig {
    @Bean(name = "priceJpaRepositoryTest")
    public PriceJpaRepository priceJpaRepository() {
        return mock(PriceJpaRepository.class);
    }

    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return new H2PriceRepository(priceJpaRepository());
    }
}
