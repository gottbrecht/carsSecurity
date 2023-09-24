package dat3.car.dto;

import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

  int id;
  int car_Id;
  String brand;
  String model;
  //@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
  LocalDate reservationDate;

  public ReservationResponse(Reservation reservation) {
    this.id = reservation.getId();
    this.car_Id = reservation.getCar().getId();
    this.brand = reservation.getCar().getBrand();
    this.model = reservation.getCar().getModel();
    this.reservationDate = reservation.getRentalDate();
  }
  ReservationRepository reservationRepository;
  public boolean isCarAlreadyReserved(int carId, LocalDate rentalDate) {
    List existingReservations = (List) reservationRepository.findCarByIdAndRentalDate(carId,rentalDate);
    return !existingReservations.isList();
  }

  public ReservationResponse reserveCar(ReservationRequest body) {
    if (body.getDate().isBefore(LocalDate.now())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date in past not allowed");
    }
    if (isCarAlreadyReserved(body.getCarId(), body.getDate())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already reserved for this date");
    } else {

      return new ReservationResponse();
    }

  }

}
