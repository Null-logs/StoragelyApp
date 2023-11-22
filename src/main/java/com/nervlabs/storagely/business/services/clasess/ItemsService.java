package com.nervlabs.storagely.business.services.clasess;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.commons.classes.DataTableResponse;
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
	public Set<ItemEntity> unregisteredItemDtoSetToItemEntitySet(Set<UnregisteredItemDto> unregisteredItems) {
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
	
	@Override
	public Page<ItemEntity> searchItems(String searchTerm, int pageNumber, int pageSize, String sortDirection) {
        Pageable pageable;

        if ("asc".equalsIgnoreCase(sortDirection)) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by("description").ascending());
            
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by("description").descending());
            
        }
        
        var y = searchTerm;

        var x = repository.findByDescriptionContainingIgnoreCase(y, pageable);
//        @SuppressWarnings("unused")
//		var y = searchItemsList(searchTerm, pageNumber, pageSize, sortDirection);
        return x;
    }

	@Override
	public DataTableResponse searchItemsList(String searchTerm, int pageNumber, int pageSize, String sortDirection, int draw) {
		List<ItemEntity> data;
		
		if ("asc".equalsIgnoreCase(sortDirection)) {
            data = repository.findByDescriptionAndTypeAscList(searchTerm,pageNumber,pageSize);	
        } else {
        	data = repository.findByDescriptionAndTypeDescList(searchTerm,pageNumber,pageSize);
        }
		
		var x = DataTableResponse.builder().data(data).draw(draw).recordsFiltered(repository.countFindByDescriptionAndType(searchTerm)).recordsTotal(data.size()).build();
		return x;
	}
	
	
}
