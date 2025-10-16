package com.co.manuel.backendHotel.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.dtos.GuestDTOResponse;
import com.co.manuel.backendHotel.mapper.GuestMapper;
import com.co.manuel.backendHotel.models.Guest;
import com.co.manuel.backendHotel.repositories.GuestRepository;

@Service
public class GuestServiceImp implements GuestService {

  private GuestRepository guestRepository;

  private GuestMapper guestMapper;

  public GuestServiceImp(GuestRepository guestRepository, GuestMapper guestMapper) {
    this.guestRepository = guestRepository;
    this.guestMapper = guestMapper;
  }

  @Override
  public GuestDTOResponse getAll(Pageable pageable) {
    Page<Guest> page = guestRepository.findAll(pageable);
    GuestDTOResponse gResponse = guestMapper.mapperGuestDTOResponseFromPageGuest(page);
    return gResponse;
  }

  @Override
  public GuestDTO getGuestById(Integer id) {
    Optional<Guest> guest = guestRepository.findById(id);
    GuestDTO guestDTO = null;
    if (guest.isPresent()) {
      guestDTO = guestMapper.mapperGuestDTOFromGuest(guest.get());
    }
    return guestDTO;

  }

  @Override
  public GuestDTO create(GuestDTO guestDTO) {
    Guest guest = guestMapper.mapperGuestFromGuestDTO(guestDTO);
    guest = guestRepository.save(guest);
    GuestDTO guestDTO2 = guestMapper.mapperGuestDTOFromGuest(guest);
    return guestDTO2;
  }

  @Override
  public GuestDTO update(Integer id, GuestDTO guestDTO) {
    Guest guest = guestMapper.mapperGuestFromGuestDTO(guestDTO);
    guest.setId(id);
    guest = guestRepository.save(guest);
    GuestDTO guestDTO2 = guestMapper.mapperGuestDTOFromGuest(guest);
    return guestDTO2;
  }

  @Override
  public void delete(Integer id) {
    guestRepository.deleteById(id);
  }

}
