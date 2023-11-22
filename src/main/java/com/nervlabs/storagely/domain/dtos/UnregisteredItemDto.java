package com.nervlabs.storagely.domain.dtos;

import java.math.BigDecimal;
import java.util.stream.Stream;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UnregisteredItemDto {
	private String key;
	private String description;
	private String information;
	private BigDecimal price;
	private BigDecimal finalPrice;
	private BigDecimal profitPercent;
	private BigDecimal quantity;
	private String unit;

	public boolean hasNulls() {
		return Stream.of(key, description, information, price, finalPrice, profitPercent, quantity, unit)
	            .anyMatch(value -> value == null);
	}
}
