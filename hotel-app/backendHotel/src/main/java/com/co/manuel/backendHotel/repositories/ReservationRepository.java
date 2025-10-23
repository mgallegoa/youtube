package com.co.manuel.backendHotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.co.manuel.backendHotel.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Modifying(clearAutomatically = true)
  @Query("update Reservation r set r.cost = :newCost where r.cost = :cost")
  public Integer updateCost(String cost, String newCost);

}
