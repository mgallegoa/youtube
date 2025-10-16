package com.co.manuel.backendHotel.dtos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class GuestDTOResponse {

  List<GuestDTO> content;
  Pageable pageable;
  Sort sort;
  Long totalElements;
  boolean empty;
  boolean first;
  boolean last;
  int size;
  int number;
  int numberOfElements;
  int totalPages;
}
