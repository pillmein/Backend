package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum ScreenTime {
    LESS_THAN_1("1시간 미만"),
    ONE_TO_THREE("1~3시간"),
    THREE_TO_FIVE("3~5시간"),
    MORE_THAN_5("5시간 이상");

    private final String description;

    ScreenTime(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
