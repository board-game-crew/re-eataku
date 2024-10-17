package kr.oshino.eataku.reservation.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantInfoDetails {
    private String restaurantName;
    private String restaurantAddress;
    private String imgUrl;
    private Long restaurantNo;
    private String contact;
    private String description;
}
