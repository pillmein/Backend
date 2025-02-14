package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum AlcoholFrequency {
    NEVER("전혀 마시지 않음"),
    MONTHLY_1_2("월 1-2회"),
    WEEKLY_1_2("주 1-2회 (월 4-8회)"),
    WEEKLY_3_PLUS("주 3회 이상 (월 9회 이상)");

    private final String description;

    AlcoholFrequency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}