package com.test.presentation.dto;

import java.time.LocalDateTime;

public record PriceFinderRequest(Long brandId, Long productId, LocalDateTime date) {
}
