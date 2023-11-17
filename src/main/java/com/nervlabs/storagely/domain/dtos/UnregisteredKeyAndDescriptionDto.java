package com.nervlabs.storagely.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnregisteredKeyAndDescriptionDto {
	private String key;
	private String description;
}
