package org.homerunball.pillmein.intake.repository;

import org.homerunball.pillmein.intake.domain.IntakeSummary;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IntakeSummaryRepository extends JpaRepository<IntakeSummary, Long> {
    Optional<IntakeSummary> findByUserAndWeekStart(User user, LocalDate weekStart);
    boolean existsByWeekStart(LocalDate weekStart);
}
