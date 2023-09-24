package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  @Autowired
  ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping("/makeReservation")
  public ResponseEntity<ReservationResponse> makeReservation(@RequestBody ReservationRequest request){
    ReservationResponse response = reservationService.reserveCar(request);
    return ResponseEntity.ok(response);
  }//We annotate the class with @RestController to indicate that this class will handle HTTP requests and
  // return the results directly as JSON.
}
