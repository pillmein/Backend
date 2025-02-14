package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum SleepDuration {
    LESS_THAN_OR_EQUAL_4("4시간 이하"),
    FIVE_TO_SIX("5~6시간"),
    SEVEN_TO_EIGHT("7~8시간"),
    MORE_THAN_EIGHT("8시간 이상");

    private final String description;

    SleepDuration(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
