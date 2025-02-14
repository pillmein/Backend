package org.homerunball.pillmein.user.domain.enums.health;

public enum InfectionFrequency {
    NOT_APPLICABLE("해당 사항 없음"),
    ONCE_OR_TWICE("1년에 1~2번"),
    THREE_TO_FOUR("1년에 3~4번"),
    FOUR_OR_MORE("1년에 4번 이상");

    private final String description;

    InfectionFrequency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
