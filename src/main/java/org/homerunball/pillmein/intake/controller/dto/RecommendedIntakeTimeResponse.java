package org.homerunball.pillmein.intake.controller.dto;

public record RecommendedIntakeTimeResponse(
        Long supplementId,
        String recommendedTime,
        String advice
) {
}