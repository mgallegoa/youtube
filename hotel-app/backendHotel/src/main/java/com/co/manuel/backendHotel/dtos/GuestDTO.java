package com.co.manuel.backendHotel.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record GuestDTO(
    Integer id,
    @NotBlank String name,
    List<ReservationDTO> reservations) {
}
