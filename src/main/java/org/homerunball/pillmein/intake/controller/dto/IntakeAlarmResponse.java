package org.homerunball.pillmein.intake.controller.dto;

import java.util.List;

public record IntakeAlarmResponse(
        Long supplementId,
        String supplementName,
        String ingredients,
        List<String> alarmTimes
) {
}