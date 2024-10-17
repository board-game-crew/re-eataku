package kr.oshino.eataku.restaurant.model.repository;

import kr.oshino.eataku.restaurant.model.entity.Menu;
import kr.oshino.eataku.restaurant.model.entity.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByRestaurantNo(RestaurantInfo restaurantInfo);
}
