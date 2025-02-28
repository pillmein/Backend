package org.homerunball.pillmein.intake.repository;

import org.homerunball.pillmein.intake.domain.IntakeAlarm;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface IntakeAlarmRepository extends JpaRepository<IntakeAlarm, Long> {
    boolean existsByUserAndUserSupplementAndAlarmTime(User user, UserSupplement userSupplement, LocalTime alarmTime);
    List<IntakeAlarm> findByUserAndUserSupplement(User user, UserSupplement userSupplement);
}
