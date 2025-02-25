package org.homerunball.pillmein.intake.service;

import org.homerunball.pillmein.intake.controller.dto.IntakeSummaryResponse;
import org.homerunball.pillmein.intake.domain.IntakeSummary;
import org.homerunball.pillmein.intake.repository.IntakeLogRepository;
import org.homerunball.pillmein.intake.repository.IntakeSummaryRepository;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IntakeSummaryService {
    private final IntakeSummaryRepository intakeSummaryRepository;
    private final IntakeLogRepository intakeLogRepository;
    private final UserRepository userRepository;

    public IntakeSummaryService(IntakeSummaryRepository intakeSummaryRepository,
                                IntakeLogRepository intakeLogRepository,
                                UserRepository userRepository) {
        this.intakeSummaryRepository = intakeSummaryRepository;
        this.intakeLogRepository = intakeLogRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public IntakeSummaryResponse getLastWeekIntakeSummary(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate lastWeekStart = LocalDate.now()
                .minusWeeks(1)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        Optional<IntakeSummary> lastWeekData = intakeSummaryRepository.findByUserAndWeekStart(user, lastWeekStart);
        int lastWeekTakenDays = lastWeekData.map(IntakeSummary::getTakenDays).orElse(0);
        double lastWeekPercentage = (lastWeekTakenDays/7.0)*100;

        LocalDate thisWeekStart = lastWeekStart.plusWeeks(1);

        int thisWeekTakenDays = intakeLogRepository.countByUserAndWeekStart(user, thisWeekStart);

        String comment = generateComment(user, thisWeekTakenDays, lastWeekTakenDays);

        return new IntakeSummaryResponse(
                lastWeekStart.toString(),
                lastWeekTakenDays,
                lastWeekPercentage,
                comment
        );
    }

    public String generateComment(User user, int thisWeekPercentage, int lastWeekPercentage) {
        if (thisWeekPercentage > lastWeekPercentage) {
            return "지난주보다 꾸준히 복용하고 있어요!";
        } else if (thisWeekPercentage < lastWeekPercentage) {
            return "지난주에 비해 복용을 하지 않았어요.";
        } else {
            boolean isMoreConsistent = isMoreConsistentPattern(user);
            return isMoreConsistent ? "지난주보다 꾸준히 복용하고 있어요!" : "지난주와 비슷한 복용 패턴을 유지하고 있어요.";
        }
    }

    public boolean isMoreConsistentPattern(User user) {
        LocalDate thisWeekStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate lastWeekStart = thisWeekStart.minusWeeks(1);

        List<LocalDate> thisWeekDates = intakeLogRepository.findIntakeDatesByUserAndWeekStart(user, thisWeekStart);
        List<LocalDate> lastWeekDates = intakeLogRepository.findIntakeDatesByUserAndWeekStart(user, lastWeekStart);

        int thisWeekStreak = getMaxConsecutiveDays(thisWeekDates);
        int lastWeekStreak = getMaxConsecutiveDays(lastWeekDates);

        return thisWeekStreak > lastWeekStreak;
    }

    public int getMaxConsecutiveDays(List<LocalDate> intakeDates) {
        if (intakeDates.isEmpty()) return 0;

        Collections.sort(intakeDates);
        int maxStreak = 1, currentStreak = 1;

        for (int i = 1; i < intakeDates.size(); i++) {
            if (intakeDates.get(i).equals(intakeDates.get(i-1).plusDays(1))) {
                currentStreak++;
            } else {
                maxStreak = Math.max(maxStreak, currentStreak);
                currentStreak = 1;
            }
        }
        return Math.max(maxStreak, currentStreak);
    }

    @Transactional
    public void saveLastWeekIntakeSummary() {
        LocalDate lastWeekStart = LocalDate.now()
                .minusWeeks(1)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        boolean exists = intakeSummaryRepository.existsByWeekStart(lastWeekStart);
        if (exists) {
            System.out.println("지난 주 복용률이 이미 저장되어 있음. 저장하지 않음.");
            return;
        }

        List<User> users = userRepository.findAll();

        for (User user : users) {
            int takenDays = intakeLogRepository.countByUserAndWeekStart(user, lastWeekStart);
            IntakeSummary summary = new IntakeSummary();
            summary.setUser(user);
            summary.setWeekStart(lastWeekStart);
            summary.setTakenDays(takenDays);
            intakeSummaryRepository.save(summary);
        }

        System.out.println("지난 주 복용률 저장 완료");
    }
}
