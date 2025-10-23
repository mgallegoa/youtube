package com.co.manuel.backendHotel.services;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.mapper.GuestMapper;
import com.co.manuel.backendHotel.models.Guest;
import com.co.manuel.backendHotel.repositories.GuestRepository;

@ExtendWith(MockitoExtension.class)
public class GuestServiceImpTest {

  @Mock
  private GuestRepository guestRepository;

  @Mock
  private GuestMapper guestMapper;

  private GuestServiceImp guestServiceImp;

  @BeforeEach
  public void setupBeforEach() {
    guestServiceImp = new GuestServiceImp(guestRepository, guestMapper);
  }

  @Test
  public void testGuestServiceImpCreateOk() {

    // Given
    String name = "Manuel Test";
    GuestDTO guestDTO = new GuestDTO(null, name, List.of());
    Guest guest = Guest.builder().name(name).reservations(List.of()).build();
    Guest guestSaved = Guest.builder().id(1).name(name).reservations(List.of()).build();
    GuestDTO guestDTOSaved = new GuestDTO(1, name, List.of());
    // when
    Mockito.when(guestMapper.mapperGuestFromGuestDTO(guestDTO)).thenReturn(guest);
    Mockito.when(guestRepository.save(guest)).thenReturn(guestSaved);
    Mockito.when(guestMapper.mapperGuestDTOFromGuest(guestSaved)).thenReturn(guestDTOSaved);

    GuestDTO guestDTOresponse = guestServiceImp.create(guestDTO);
    // then
    Assertions.assertThat(guestDTOresponse).isNotNull();
    Assertions.assertThat(guestDTOresponse.name()).isNotNull();
    Assertions.assertThat(guestDTOresponse.name()).as("The name should be only the first one.").isEqualTo("Manuel");

  }

}
