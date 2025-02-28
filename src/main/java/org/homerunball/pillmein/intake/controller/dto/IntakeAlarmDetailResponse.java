package org.homerunball.pillmein.intake.controller.dto;

import java.util.List;

public record IntakeAlarmDetailResponse(
        Long supplementId,
        String supplementName,
        List<IntakeAlarmTimeResponse> alarmTimes
) {
}