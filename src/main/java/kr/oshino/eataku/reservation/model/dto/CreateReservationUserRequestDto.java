package kr.oshino.eataku.reservation.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateReservationUserRequestDto {

    private int memberNo ;
    private int restaurantNo ;
    private int partySize;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

}
