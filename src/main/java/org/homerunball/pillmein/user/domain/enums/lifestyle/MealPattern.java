package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum MealPattern {
    THREE_MEALS("하루 세 끼 규칙적으로 식사"),
    IRREGULAR("하루 1-2끼 불규칙하게 식사"),
    SKIP_MEALS("끼니를 거르고 간식으로 대체"),
    DELIVERY_OR_OUTSIDE("배달음식이나 외식으로 끼니 해결");

    private final String description;

    MealPattern(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
