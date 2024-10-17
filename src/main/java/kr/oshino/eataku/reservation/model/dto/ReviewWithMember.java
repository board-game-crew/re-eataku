package kr.oshino.eataku.reservation.model.dto;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewWithMember {
    private Long memberNo;
    private int reviewStars;
}
