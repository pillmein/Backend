package org.homerunball.pillmein.intake.controller.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record IntakeSummaryResponse(
        String weekStart,
        int takenDays,
        int percentage,
        String comment
) {
    public IntakeSummaryResponse(String weekStart, int takenDays, double percentage, String comment) {
        this(weekStart, takenDays, roundToInt(percentage), comment);
    }

    private static int roundToInt(double percentage) {
        return BigDecimal.valueOf(percentage)
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
    }
}