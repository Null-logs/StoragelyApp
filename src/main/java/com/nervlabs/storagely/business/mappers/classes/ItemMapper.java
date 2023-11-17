package com.nervlabs.storagely.business.mappers.classes;

import org.springframework.stereotype.Component;

import com.nervlabs.storagely.business.mappers.Interfaces.IItemMapper;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;
import com.nervlabs.storagely.domain.entites.ItemEntity;

@Component
public class ItemMapper implements IItemMapper {

	@Override
	public ItemEntity UnregisteredItemDtoToItemEntity(UnregisteredItemDto unregisteredItemDto) {

		return (unregisteredItemDto == null) ? null
				: ItemEntity.builder().key(unregisteredItemDto.getKey()).price(unregisteredItemDto.getPrice())
						.profitPercent(unregisteredItemDto.getProfitPercent())
						.description(unregisteredItemDto.getDescription()).quantity(unregisteredItemDto.getQuantity())
						.information(unregisteredItemDto.getInformation())
						.finalPrice(unregisteredItemDto.getFinalPrice()).unit(unregisteredItemDto.getUnit())
						.profitPercent(unregisteredItemDto.getProfitPercent())
						.type("ITEM").build();
	}
}
