package com.co.manuel.backendHotel.dtos;

import java.net.URI;

public record ProblemDetailsDTO(
    URI type,
    int status,
    String title,
    String detail,
    String instance) {
}
