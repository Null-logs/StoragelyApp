package com.nervlabs.storagely.business.facades.classes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.business.commons.classes.ItemConstant;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.commons.enums.Types;
import com.nervlabs.storagely.business.facades.IItemFacade;
import com.nervlabs.storagely.business.services.interfaces.IItemService;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;

@Service
public class ItemFacade implements IItemFacade {

	@Autowired
	IItemService service;

	@Override
	public Response register(Set<UnregisteredItemDto> unregisteredItems) {

		Set<UnregisteredItemDto> unregisteredItemDtos =  new HashSet<>();
		
		var rs = Response.builder().httpStatus(HttpStatus.OK).type(Types.SUCCESS)
				.message("").build();

		if (service.hasAttNulls(unregisteredItems)) {
			unregisteredItemDtos = service.getItemsThathaveAttNulls(unregisteredItems);
			unregisteredItems = service.getItemsThatNothaveAttNulls(unregisteredItems);
			rs.setData(unregisteredItemDtos);
			rs.setType(Types.WARNING);
			rs.setMessage(ItemConstant.PRODUCTS_WITH_NULL_DATA);
		}

		

		try {
			
			var allItemsAndKeys = service.getAllItemKeysAndDescriptions();

			if (service.areRepeatedItems(allItemsAndKeys, unregisteredItems)) {
				var unregisteredItemsRepeated = service.getRepeatedItemsFrom(allItemsAndKeys, unregisteredItems);
				unregisteredItems = service.getNonRepeatedItemsFrom(allItemsAndKeys, unregisteredItems);

				unregisteredItemDtos.addAll(unregisteredItemsRepeated);
				rs.setData(unregisteredItemDtos);
				rs.setType(Types.WARNING);
				
				if(rs.getMessage().isBlank()) {
					rs.setMessage(ItemConstant.PRODUCTS_WITH_REPEATED_DATA);
				}else {
					rs.setMessage(rs.getMessage() + ItemConstant.PRODUCTS_WITH_REPEATED_DATA);
				}
			}
			
			if(unregisteredItems.isEmpty()) {
				return rs;
			}
			
			rs.setQty(unregisteredItems.size());

			var unregisteredItemEntites = service.parseThe(unregisteredItems);

			service.save(unregisteredItemEntites);
			
			rs.setMessage("Los productos se registraron correctamente.");
			
			return rs;

		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();

			return Response.builder().httpStatus(HttpStatus.SERVICE_UNAVAILABLE).type(Types.WARNING)
					.message(ItemConstant.FATAL_ERRROR).data(null).build();

		} catch (Exception e) {
			 e.printStackTrace();
			 
			return Response.builder().httpStatus(HttpStatus.SERVICE_UNAVAILABLE).type(Types.ERROR)
					.message(ItemConstant.FATAL_ERRROR).data(e).build();
		}

	}
}
