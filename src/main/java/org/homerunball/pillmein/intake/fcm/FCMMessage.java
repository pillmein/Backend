package org.homerunball.pillmein.intake.fcm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class FCMMessage {
    private boolean validateOnly;
    private Message message;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Message {
        private Notification notification;
        private String token; //디바이스 토큰
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
    }
}
