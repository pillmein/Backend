package org.homerunball.pillmein.user.domain.enums.health;

public enum SeasonalDiscomfort {
    NOT_APPLICABLE("해당 사항 없음"),
    OCCASIONALLY("가끔 느낌"),
    OFTEN("자주 느낌"),
    VERY_OFTEN("매우 자주 느낌");

    private final String description;

    SeasonalDiscomfort(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}