package com.co.manuel.backendHotel.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.manuel.backendHotel.dtos.GuestDTO;
import com.co.manuel.backendHotel.dtos.GuestDTOResponse;
import com.co.manuel.backendHotel.services.GuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {

  private GuestService guestService;

  public GuestController(GuestService guestService) {
    this.guestService = guestService;
  }

  @GetMapping
  public GuestDTOResponse getAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {
    return guestService.getAll(pageable);
  }

  @GetMapping("/{id}")
  public GuestDTO getGuestById(@PathVariable("id") Integer id) {
    return guestService.getGuestById(id);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public GuestDTO create(@Valid @RequestBody GuestDTO guestDto) {
    return guestService.create(guestDto);
  }

  @PutMapping("/edit/{id}")
  public GuestDTO update(@Valid @PathVariable("id") Integer id, @RequestBody GuestDTO guestDto) {
    return guestService.update(id, guestDto);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable("id") Integer id) {
    guestService.delete(id);
  }
}
