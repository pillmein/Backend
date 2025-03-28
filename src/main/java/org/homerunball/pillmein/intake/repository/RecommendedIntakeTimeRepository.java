package org.homerunball.pillmein.intake.repository;

import org.homerunball.pillmein.intake.domain.RecommendedIntakeTime;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendedIntakeTimeRepository extends JpaRepository<RecommendedIntakeTime, Long> {
    Optional<RecommendedIntakeTime> findByUserAndUserSupplement(User user, UserSupplement userSupplement);
}