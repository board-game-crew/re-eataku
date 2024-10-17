package kr.oshino.eataku.restaurant.model.repository;

import kr.oshino.eataku.restaurant.model.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {
}
