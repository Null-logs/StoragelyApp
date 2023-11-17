package com.nervlabs.storagely.persistence;

import java.util.List;
import java.util.Set;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nervlabs.storagely.domain.entites.ItemEntity;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, UUID> {
	
	@Query("SELECT ie.key FROM ItemEntity ie")
	Set<String> findAllKeys();
	
	@Query("SELECT ie.key, ie.description FROM ItemEntity ie")
	List<ItemEntity> findAllKeysAndDescriptions();
}
