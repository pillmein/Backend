package org.homerunball.pillmein.intake.controller.dto;

public record IntakeSummaryResponse(
   String weekStart,
   int takenDays,
   double percentage,
   String comment
) {}
