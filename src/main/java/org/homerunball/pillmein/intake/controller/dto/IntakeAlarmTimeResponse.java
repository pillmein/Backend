package org.homerunball.pillmein.intake.controller.dto;

import org.homerunball.pillmein.intake.domain.enums.RepeatType;

public record IntakeAlarmTimeResponse(
        Long alarmId,
        String time,
        RepeatType repeatType
) {
}