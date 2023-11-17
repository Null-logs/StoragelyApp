package com.nervlabs.storagely.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nervlabs.storagely.domain.entites.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, UUID>{
	 Optional<EmployeeEntity> findByUsername(String username);
}
