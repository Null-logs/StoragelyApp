package com.nervlabs.storagely.business.commons.classes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nervlabs.storagely.domain.entites.ItemEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DataTableResponse {
	private Integer draw;
	private Integer recordsTotal;
	private Long recordsFiltered;
	private List<ItemEntity> data;
}
