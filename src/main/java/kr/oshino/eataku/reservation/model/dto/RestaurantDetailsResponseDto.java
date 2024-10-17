package kr.oshino.eataku.reservation.model.dto;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetailsResponseDto {


    private Long restaurantNo;
    private LocalDateTime reservationTime;
    private int reservationPeople;

}
