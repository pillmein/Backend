package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum SedentaryHours {
    LESS_THAN_2("2시간 미만"),
    TWO_TO_FOUR("2~4시간"),
    FOUR_TO_EIGHT("4~8시간"),
    MORE_THAN_EIGHT("8시간 이상");

    private final String description;

    SedentaryHours(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
