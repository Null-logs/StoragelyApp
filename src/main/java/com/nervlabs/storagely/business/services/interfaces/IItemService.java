package com.nervlabs.storagely.business.services.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.nervlabs.storagely.business.commons.classes.DataTableResponse;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;
import com.nervlabs.storagely.domain.entites.ItemEntity;

public interface IItemService {
	Set<ItemEntity> unregisteredItemDtoSetToItemEntitySet(Set<UnregisteredItemDto> unregisteredItems);

	boolean areRepeatedItems(Map<String,String> ItemKeysAndDescriptions, Set<UnregisteredItemDto> unregisteredItems);
	
	Set<UnregisteredItemDto> getRepeatedItemsFrom(Map<String, String> ItemKeysAndDescriptions, Set<UnregisteredItemDto> unregisteredItems);

	Set<UnregisteredItemDto> getNonRepeatedItemsFrom(Map<String, String> ItemKeysAndDescriptions, Set<UnregisteredItemDto> unregisteredItems);
	
	void save(Set<ItemEntity> itemEntities);
	
	Set<String> getAllItemsKeysFromRepo();
	
	Map<String, String> getAllItemKeysAndDescriptions();
	
	boolean hasAttNulls(Set<UnregisteredItemDto> unregisteredItems);
	
	Set<UnregisteredItemDto> getItemsThathaveAttNulls(Set<UnregisteredItemDto> unregisteredItems);
	
	Set<UnregisteredItemDto> getItemsThatNothaveAttNulls(Set<UnregisteredItemDto> unregisteredItems);
	
	Page<ItemEntity> searchItems(String searchTerm, int pageNumber, int pageSize, String sortDirection);
	
	DataTableResponse searchItemsList(String searchTerm, int pageNumber, int pageSize, String sortDirection, int draw);
	
}
