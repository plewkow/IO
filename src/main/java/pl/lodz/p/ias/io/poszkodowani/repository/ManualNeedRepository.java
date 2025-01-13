package pl.lodz.p.ias.io.poszkodowani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.ias.io.poszkodowani.model.FinancialNeed;
import pl.lodz.p.ias.io.poszkodowani.model.ManualNeed;

import java.util.List;

@Repository
public interface ManualNeedRepository extends JpaRepository<ManualNeed, Long> {
    List<ManualNeed> findByUserId(Long userId);
}
