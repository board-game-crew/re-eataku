package kr.oshino.eataku.reservation.model.dto;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuDto {
    private String photoUrl;
    private String description;
    private String menuName;
}
