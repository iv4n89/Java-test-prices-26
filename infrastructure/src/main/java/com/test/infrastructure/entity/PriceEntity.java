package com.test.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
@IdClass(PriceEntityId.class)
public class PriceEntity {
    @Id
    private Long brandId;

    @Id
    private Long productId;

    @Id
    private LocalDateTime startDate;

    @Id
    private LocalDateTime endDate;

    @Id
    private Integer priority;

    private BigDecimal price;

    private Long priceList;

    private String curr;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((priceList == null) ? 0 : priceList.hashCode());
        result = prime * result + ((curr == null) ? 0 : curr.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PriceEntity other = (PriceEntity) obj;
        if (brandId == null) {
            if (other.brandId != null)
                return false;
        } else if (!brandId.equals(other.brandId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (priority == null) {
            if (other.priority != null)
                return false;
        } else if (!priority.equals(other.priority))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (priceList == null) {
            if (other.priceList != null)
                return false;
        } else if (!priceList.equals(other.priceList))
            return false;
        if (curr == null) {
            if (other.curr != null)
                return false;
        } else if (!curr.equals(other.curr))
            return false;
        return true;
    }
    
    
}
