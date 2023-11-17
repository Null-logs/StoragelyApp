package com.nervlabs.storagely.business.services.clasess;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.mappers.Interfaces.IItemMapper;
import com.nervlabs.storagely.business.services.interfaces.IItemService;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;
import com.nervlabs.storagely.domain.entites.ItemEntity;
import com.nervlabs.storagely.persistence.IItemRepository;

@Service
public class ItemsService implements IItemService {

	private final IItemRepository repository;
	private final IItemMapper maper;

	ItemsService(IItemMapper maper, IItemRepository repository) {
		this.maper = maper;
		this.repository = repository;
	}

	@Override
	public Set<ItemEntity> parseThe(Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().map(i -> maper.UnregisteredItemDtoToItemEntity(i))
				.collect(Collectors.toSet());
	}

	@Override
	public boolean areRepeatedItems(Map<String, String> allItemsKeys, Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream()
				.anyMatch(i -> isRepeatedKeys(allItemsKeys, i) || isRepeatedDescription(allItemsKeys,i));
	}
	
	private boolean isRepeatedKeys(Map<String, String> allItemsKeys, UnregisteredItemDto unregisteredItem) {
		return allItemsKeys.containsKey(unregisteredItem.getKey());
	}
	
	private boolean isRepeatedDescription(Map<String, String> allItemsKeys, UnregisteredItemDto unregisteredItem ) {
		return allItemsKeys.containsValue(unregisteredItem.getKey());
	}

	@Override
	public Set<UnregisteredItemDto> getRepeatedItemsFrom(Map<String, String> allItemsKeys, Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().filter(item -> isRepeatedKeys(allItemsKeys, item) || isRepeatedDescription(allItemsKeys,item))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<UnregisteredItemDto> getNonRepeatedItemsFrom(Map<String, String> allItemsKeys, Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().filter(item -> !isRepeatedKeys(allItemsKeys, item) && !isRepeatedDescription(allItemsKeys,item))
				.collect(Collectors.toSet());
	}

	@Override
	public void save(Set<ItemEntity> itemEntities) {
		// TODO Auto-generated method stub
		repository.saveAll(itemEntities);
	}

	@Override
	public Set<String> getAllItemsKeysFromRepo() {
		// TODO Auto-generated method stub
		return repository.findAllKeys();
	}

	@Override
	public boolean hasAttNulls(Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().anyMatch(i -> i.hasNulls());
	}

	@Override
	public Set<UnregisteredItemDto> getItemsThathaveAttNulls(Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().filter(i -> i.hasNulls()).collect(Collectors.toSet());
	}

	@Override
	public Set<UnregisteredItemDto> getItemsThatNothaveAttNulls(Set<UnregisteredItemDto> unregisteredItems) {
		// TODO Auto-generated method stub
		return unregisteredItems.stream().filter(i -> !(i.hasNulls())).collect(Collectors.toSet());
	}

	@Override
	public Map<String, String> getAllItemKeysAndDescriptions() {
		// TODO Auto-generated method stub
		var c = repository.findAll();
		var y =  c.stream().collect(Collectors.toMap(i -> i.getKey(), i -> i.getDescription()));
		return y;
	}

}
