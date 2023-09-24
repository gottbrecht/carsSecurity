package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;

import jakarta.validation.constraints.NotNull;


import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ----Lombok anotations above --------- //
@NotNull
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles { //automatisk får email, password og username
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private boolean approved;
  private int ranking;

  @OneToMany(mappedBy = "member")
  List<Reservation> reservations;//; = new ArrayList<>();

  public void addReservation(Reservation reservation){
    if (reservations == null){
      reservations = new ArrayList<>();
    }
    reservations.add(reservation);
  }


  public Member(String user, String password, String email,
                String firstName, String lastName, String street, String city, String zip) {
    super(user, password, email);
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
  }
}