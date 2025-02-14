package org.homerunball.pillmein.user.domain.enums.health;

public enum FocusMemoryIssues {
    NOT_APPLICABLE("해당 사항 없음"),
    OCCASIONALLY("가끔 있음"),
    OFTEN("자주 있음"),
    VERY_OFTEN("매우 자주 있음");

    private final String description;

    FocusMemoryIssues(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
