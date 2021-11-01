package com.elsevier.parking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elsevier.parking.model.Parking;

public interface ParkingRepo  extends JpaRepository<Parking, Integer>{

	public static final String FIND_PARKING = "SELECT * FROM parking where vehicle_no like %?1% AND is_active = 'Active'";
	public static final String FIND_OPEN_PARKING = "SELECT * FROM parking where is_active = 'Active'";

	@Query(value = FIND_PARKING, nativeQuery = true)
	List<Parking> findDocumentsForListing(String filterValue);
	
	/*
	 * @Modifying
	 * 
	 * @Query(value = UPDATE_PARKING, nativeQuery = true) int
	 * findDocumentsForUpdate(int parkingId, String totalHours, String closingTime,
	 * String isActive);
	 */ 
	
	@Query(value = FIND_OPEN_PARKING, nativeQuery = true)
	List<Parking> findOpenParking();

} 
