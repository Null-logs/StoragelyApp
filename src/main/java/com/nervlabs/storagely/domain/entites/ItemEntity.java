package com.nervlabs.storagely.domain.entites;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "item")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String key;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(columnDefinition = "TEXT")
	private String information;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private BigDecimal finalPrice;

	@Column(nullable = false)
	private BigDecimal profitPercent;

	@Column(nullable = false)
	private BigDecimal quantity;

	@Column(nullable = false)
	private String unit;

	@Column(nullable = false)
	private String type;
}
