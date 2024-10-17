package kr.oshino.eataku.restaurant.model.repository;

import kr.oshino.eataku.restaurant.model.entity.TemporarySave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporarySaveRepository extends JpaRepository<TemporarySave, Long> {

    TemporarySave save(TemporarySave temporarySave);

    TemporarySave findByAccount(String account);
}
