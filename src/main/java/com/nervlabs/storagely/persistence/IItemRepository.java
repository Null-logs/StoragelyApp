package com.nervlabs.storagely.persistence;


import java.util.List;
import java.util.Set;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lowagie.text.pdf.AcroFields.Item;
import com.nervlabs.storagely.domain.entites.ItemEntity;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, UUID> {

	@Query("SELECT ie.key FROM ItemEntity ie")
	Set<String> findAllKeys();

	@Query("SELECT ie.key, ie.description FROM ItemEntity ie")
	List<ItemEntity> findAllKeysAndDescriptions();
	
	@Query("SELECT ie FROM ItemEntity ie ORDER BY ie.quantity")
	List<ItemEntity> findAllOrderByQty();
	
	@Query("SELECT " +
	        "   i " +
	        "FROM " +
	        "   ItemEntity i " +
	        "WHERE " +
	        "   UPPER(i.description) LIKE UPPER(CONCAT('%', :searchTerm, '%')) AND i.type = 'ITEM' " +
	        "ORDER BY " +
	        "   i.description " +
	        "LIMIT :limit " +
			"   OFFSET :offset")
	List<ItemEntity> findByDescriptionAndTypeAscList(@Param("searchTerm") String searchTerm,
	                                                       @Param("offset") int offset,
	                                                       @Param("limit") int limit);
	
	@Query("SELECT " +
	        "   i " +
	        "FROM " +
	        "   ItemEntity i " +
	        "WHERE " +
	        "   UPPER(i.description) LIKE UPPER(CONCAT('%', :searchTerm, '%')) AND i.type = 'ITEM' " +
	        "ORDER BY " +
	        "   i.description DESC " +
	        "LIMIT :limit " +
			"   OFFSET :offset")
	List<ItemEntity> findByDescriptionAndTypeDescList(@Param("searchTerm") String searchTerm,
	                                                       @Param("offset") int offset,
	                                                       @Param("limit") int limit);
	
	@Query("SELECT " +
	        "   COUNT (i) " +
	        "FROM " +
	        "   ItemEntity i " +
	        "WHERE " +
	        "   UPPER(i.description) LIKE UPPER(CONCAT('%', :searchTerm, '%')) AND i.type = 'ITEM' ")
	Long countFindByDescriptionAndType(@Param("searchTerm") String searchTerm);
	



	Page<ItemEntity> findByDescriptionContainingIgnoreCase(String searchTerm, Pageable pageable);

	Page<ItemEntity> findByDescriptionContainingIgnoreCaseOrderByDescriptionAsc(String searchTerm, Pageable pageable);

	Page<ItemEntity> findByDescriptionContainingIgnoreCaseOrderByDescriptionDesc(String searchTerm, Pageable pageable);
	
	List<ItemEntity> findByDescription(String searchTerm, Pageable pageable);

}
