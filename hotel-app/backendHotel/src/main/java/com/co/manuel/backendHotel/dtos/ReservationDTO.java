package com.co.manuel.backendHotel.dtos;

import jakarta.validation.constraints.NotBlank;

public record ReservationDTO(
    Long id,
    @NotBlank String dateIn,
    @NotBlank String dateOut,
    @NotBlank String cost) {
}
