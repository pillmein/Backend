package org.homerunball.pillmein.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // null 값은 JSON 응답에서 제외됨
public record UserSurveyRequestDto(
        // 생활습관 (7개)
        String outdoorActivity,
        String sedentaryHours,
        String sleepDuration,
        String screenTime,
        String caffeineIntake,
        String mealPattern,
        String alcoholFrequency,

        // 건강고민 (14개)
        String physicalFatigue,
        String mentalFatigue,
        String digestionIssues,
        String headacheDizziness,
        String infectionFrequency,
        String skinConcern,
        String weightChange,
        String dietMethod,
        String sleepDisruption,
        String seasonalDiscomfort,
        String eyeFatigue,
        String painFrequency,
        String focusMemoryIssues,
        String brittleNailsHair
) {}
