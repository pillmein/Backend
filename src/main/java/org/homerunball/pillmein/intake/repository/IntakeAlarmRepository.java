package org.homerunball.pillmein.intake.repository;

import org.homerunball.pillmein.intake.domain.IntakeAlarm;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IntakeAlarmRepository extends JpaRepository<IntakeAlarm, Long> {
    boolean existsByUserAndUserSupplementAndAlarmTime(User user, UserSupplement userSupplement, LocalTime alarmTime);
    List<IntakeAlarm> findByUserAndUserSupplement(User user, UserSupplement userSupplement);
    Optional<IntakeAlarm> findByIdAndUser(Long id, User user);
    void deleteByIdAndUser(Long id, User user);

    @Query("SELECT ia FROM IntakeAlarm ia JOIN FETCH ia.userSupplement WHERE ia.alarmTime = :alarmTime")
    List<IntakeAlarm> findByAlarmTime(@Param("alarmTime") LocalTime alarmTime);
}
