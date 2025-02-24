package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum CaffeineIntake {
    NEVER("섭취하지 않음"),
    WEEKLY_1_2("주 1~2회"),
    WEEKLY_3_4("주 3~4회"),
    DAILY_OR_MORE("매일 1회 이상");

    private final String description;

    CaffeineIntake(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
