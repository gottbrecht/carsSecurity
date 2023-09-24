package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//Lombok above
@Entity
public class Reservation extends AdminDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String username;
  int car_Id;
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate rentalDate;

  @ManyToOne
  Member member;

  @ManyToOne
  Car car;

  public Reservation(int car_Id, String userName, LocalDate date) {
    super();
  }

  public Reservation(LocalDate date1, Car car1, Member m1) {
    super();
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public int getCar_Id() {
    return car_Id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCar_Id(int carId) {
    this.car_Id = carId;
  }

  public void setRentalDate(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Reservation(int id, String username, int car_Id, LocalDate rentalDate, Car car, Member member) {
    this.rentalDate = rentalDate;
    this.username = username;
    this.car = car;
    car.addReservation(this);
    member.addReservation(this);



  }

}
