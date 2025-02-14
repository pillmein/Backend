package org.homerunball.pillmein.user.domain.enums.health;

public enum DietMethod {
    NOT_APPLICABLE("해당 사항 없음"),
    DIET_RESTRICTION("식이제한형"),
    FASTING("단식이나 하루 한끼 식사"),
    EXERCISE_BASED("운동 중심");

    private final String description;

    DietMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
