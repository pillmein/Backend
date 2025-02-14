package org.homerunball.pillmein.user.domain.enums.health;

public enum WeightChange {
    NOT_APPLICABLE("해당 사항 없음"),
    SLIGHT_CHANGE("가벼운 변화가 있음"),
    RAPID_CHANGE("급격한 변화가 있음"),
    FREQUENT_CHANGE("자주 있음");

    private final String description;

    WeightChange(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}