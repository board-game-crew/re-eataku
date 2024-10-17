package kr.oshino.eataku.review.model.entity;


import jakarta.persistence.*;

import kr.oshino.eataku.restaurant.model.entity.RestaurantInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_average_rating")
public class AverageRating {

    @Id
    @Column(name = "rating_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingNo;      // 별점 고유 번호

    @OneToOne()
    @JoinColumn(name = "restaurant_no")
    private RestaurantInfo restaurantNo;        // 식당 고유 번호

    @Column(name = "rating")
    private double averageRating;      // 별점 평균

}
