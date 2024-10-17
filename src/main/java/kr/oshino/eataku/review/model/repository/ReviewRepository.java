package kr.oshino.eataku.review.model.repository;

import kr.oshino.eataku.member.model.entity.Member;
import kr.oshino.eataku.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    void deleteByReviewNo(Integer reviewNo);

    int countByMember(Member toMember);

    List<Review> findAllByMember_MemberNo(Long memberNo);

    Review findByRestaurantInfo_RestaurantNoAndTypeAndReferenceNumber(Long restaurantNo, String serviceType, Long serviceNo);

    Review findByReviewNo(int reviewNo);
}
