package com.co.manuel.backendHotel.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.co.manuel.backendHotel.models.Reservation;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ReservationRepositoryTest {

  @Autowired
  private ReservationRepository repository;
  Reservation reservation;

  @BeforeEach
  public void setupBeforeEach() {
    reservation = Reservation.builder().dateIn("05062025").dateOut("05082025").cost("200").build();

  }

  @Test
  @DisplayName("Test reservacion repository to create method to return the ok status")
  public void testReservationRepositoryCreateOk() {
    // Given AAA Assets
    // When Act
    Reservation reservationSaved = repository.save(reservation);

    // Then Asserts
    Assertions.assertThat(reservationSaved).isNotNull();
    Assertions.assertThat(reservationSaved.getId()).isNotNull();
    Assertions.assertThat(reservationSaved.getDateIn()).isEqualTo(reservation.getDateIn());
  }

  @Test
  public void testReservationRepositoryUpdateCostOk() {
    // Given
    Reservation reservation2 = Reservation.builder().dateIn("05062026").dateOut("05082026").cost("200").build();
    // When

    repository.saveAll(List.of(reservation, reservation2));
    int numbeOfUpdated = repository.updateCost("200", "300");
    List<Reservation> listReservations = repository.findAll();

    // Then
    Assertions.assertThat(numbeOfUpdated).isEqualTo(2);
    Assertions.assertThat(listReservations).isNotNull();
    Assertions.assertThat(listReservations.size()).isEqualTo(2);
    Assertions.assertThat(listReservations.get(0).getCost()).isEqualTo("300");

  }

}
