package com.co.manuel.backendHotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.manuel.backendHotel.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
