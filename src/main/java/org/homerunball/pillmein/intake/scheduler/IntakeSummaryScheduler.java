package org.homerunball.pillmein.intake.scheduler;

import org.homerunball.pillmein.intake.service.IntakeSummaryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IntakeSummaryScheduler {
    private final IntakeSummaryService intakeSummaryService;

    public IntakeSummaryScheduler(IntakeSummaryService intakeSummaryService) {
        this.intakeSummaryService = intakeSummaryService;
    }

    @Scheduled(cron = "0 0 0 * * SUN", zone = "Asia/Seoul")
    public void saveLastWeekIntakeSummary() {
        System.out.println("지난 주 복용률 저장 시작...");
        intakeSummaryService.saveLastWeekIntakeSummary();
        System.out.println("지난 주 복용률 저장 완료");
    }
}
