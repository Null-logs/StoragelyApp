package com.nervlabs.storagely.business.commons.classes;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DataTableRequest {
	private Integer draw;
	private Integer star;
	private Integer length;
	private String searchValue;
	private Integer OrderColumn;
	private String OrderDir;
}
