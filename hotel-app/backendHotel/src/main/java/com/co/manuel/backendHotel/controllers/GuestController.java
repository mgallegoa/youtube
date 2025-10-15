package com.co.manuel.backendHotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.manuel.backendHotel.models.Guest;
import com.co.manuel.backendHotel.repositories.GuestRepository;

@RestController
@RequestMapping("/")
public class GuestController {

  @Autowired
  private GuestRepository guestRepository;

  @GetMapping
  public List<com.co.manuel.backendHotel.models.Guest> getAll() {
    return guestRepository.findAll();
  }

  @PostMapping
  public Guest create(@RequestBody Guest guest) {
    return guestRepository.save(guest);
  }

}
