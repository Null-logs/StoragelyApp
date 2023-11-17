package com.nervlabs.storagely.business.mappers.Interfaces;

import org.mapstruct.Mapper;

import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;
import com.nervlabs.storagely.domain.entites.ItemEntity;

@Mapper(componentModel = "spring")
public interface IItemMapper {
	ItemEntity UnregisteredItemDtoToItemEntity(UnregisteredItemDto unregisteredItemDto);
}
