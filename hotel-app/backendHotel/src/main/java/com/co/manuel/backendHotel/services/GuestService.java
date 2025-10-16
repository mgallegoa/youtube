package com.co.manuel.backendHotel.services;

import org.springframework.data.domain.Pageable;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.dtos.GuestDTOResponse;

public interface GuestService {

  GuestDTOResponse getAll(Pageable pageable);

  GuestDTO getGuestById(Integer id);

  GuestDTO create(GuestDTO guestDTO);

  GuestDTO update(Integer id, GuestDTO guestDTO);

  void delete(Integer id);
}
