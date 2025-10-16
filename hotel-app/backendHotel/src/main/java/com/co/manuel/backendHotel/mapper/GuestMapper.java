package com.co.manuel.backendHotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springframework.data.domain.Page;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.dtos.GuestDTOResponse;
import com.co.manuel.backendHotel.models.Guest;

@Mapper(componentModel = ComponentModel.SPRING)
public interface GuestMapper {

  GuestDTOResponse mapperGuestDTOResponseFromPageGuest(Page<Guest> page);

  GuestDTO mapperGuestDTOFromGuest(Guest guest);

  Guest mapperGuestFromGuestDTO(GuestDTO guestDTO);

}
