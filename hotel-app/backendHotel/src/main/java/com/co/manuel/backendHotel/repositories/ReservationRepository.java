package com.co.manuel.backendHotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.manuel.backendHotel.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
