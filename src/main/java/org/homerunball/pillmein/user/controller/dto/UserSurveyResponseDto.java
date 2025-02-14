package org.homerunball.pillmein.user.controller.dto;

import org.homerunball.pillmein.user.domain.UserSurvey;

public record UserSurveyResponseDto(
        String outdoorActivity,
        String sedentaryHours,
        String sleepDuration,
        String screenTime,
        String caffeineIntake,
        String mealPattern,
        String alcoholFrequency,
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
) {
    public static UserSurveyResponseDto from(UserSurvey userSurvey) {
        return new UserSurveyResponseDto(
                userSurvey.getOutdoorActivity(),
                userSurvey.getSedentaryHours(),
                userSurvey.getSleepDuration(),
                userSurvey.getScreenTime(),
                userSurvey.getCaffeineIntake(),
                userSurvey.getMealPattern(),
                userSurvey.getAlcoholFrequency(),
                userSurvey.getPhysicalFatigue(),
                userSurvey.getMentalFatigue(),
                userSurvey.getDigestionIssues(),
                userSurvey.getHeadacheDizziness(),
                userSurvey.getInfectionFrequency(),
                userSurvey.getSkinConcern(),
                userSurvey.getWeightChange(),
                userSurvey.getDietMethod(),
                userSurvey.getSleepDisruption(),
                userSurvey.getSeasonalDiscomfort(),
                userSurvey.getEyeFatigue(),
                userSurvey.getPainFrequency(),
                userSurvey.getFocusMemoryIssues(),
                userSurvey.getBrittleNailsHair()
        );
    }
}
