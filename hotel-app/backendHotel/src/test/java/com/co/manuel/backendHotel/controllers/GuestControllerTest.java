package com.co.manuel.backendHotel.controllers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.services.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class GuestControllerTest {

  @Mock
  private GuestService guestService;

  private GuestController guestController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setupBeforeEach() {
    guestController = new GuestController(guestService);
    mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testGuestControllerCreateOk() throws Exception {

    GuestDTO guestDTO = new GuestDTO(null, "Manuel Test", List.of());
    GuestDTO guestDTOresponse = new GuestDTO(1, "Manuel Test", List.of());

    Mockito.when(guestService.create(ArgumentMatchers.any())).thenReturn(guestDTOresponse);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/guests")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(guestDTO)));

    resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

  }

}
