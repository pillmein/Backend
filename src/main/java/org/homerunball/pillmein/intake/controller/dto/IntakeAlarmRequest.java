package org.homerunball.pillmein.intake.controller.dto;

import org.homerunball.pillmein.intake.domain.enums.RepeatType;

public record IntakeAlarmRequest(
        Long supplementId,
        String alarmTime,
        RepeatType repeatType
) {
}
