package com.co.manuel.backendHotel;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.co.manuel.backendHotel.models.Reservation;
import com.co.manuel.backendHotel.repositories.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ReservationRepositoryTest {

  @Autowired
  private ReservationRepository repository;

  @Test
  public void ReservationRepository_save_ok() {
    Reservation reservation = Reservation.builder().dateIn("05092025").dateOut("05102025").cost("500").build();

    Reservation reservation2 = repository.save(reservation);

    Assertions.assertThat(reservation2).isNotNull();
    Assertions.assertThat(reservation2.getId()).isNotNull();
    Assertions.assertThat(reservation2.getCost()).isEqualTo(reservation.getCost());
  }

  @Test
  public void ReservationRepository_delete_ok() {
    Reservation reservation = Reservation.builder().dateIn("05092025").dateOut("05102025").cost("500").build();

    Reservation savedReservation = repository.save(reservation);

    assertAll(() -> repository.deleteById(savedReservation.getId()));

  }

}
