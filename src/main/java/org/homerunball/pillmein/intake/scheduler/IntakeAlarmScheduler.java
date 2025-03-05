package org.homerunball.pillmein.intake.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homerunball.pillmein.auth.domain.UserAuth;
import org.homerunball.pillmein.auth.repository.UserAuthRepository;
import org.homerunball.pillmein.intake.domain.IntakeAlarm;
import org.homerunball.pillmein.intake.fcm.FCMPushRequestDto;
import org.homerunball.pillmein.intake.fcm.FCMService;
import org.homerunball.pillmein.intake.repository.IntakeAlarmRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntakeAlarmScheduler {
    private final IntakeAlarmRepository intakeAlarmRepository;
    private final UserAuthRepository userAuthRepository;
    private final FCMService fcmService;

    @Scheduled(fixedRate = 60000)
    public void checkAndSendAlarms() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

        LocalTime now = LocalTime.now().withSecond(0).withNano(0);
        //ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        log.info("현재 시간: {}", now.format(DateTimeFormatter.ofPattern("HH:mm")));

        List<IntakeAlarm> alarms = intakeAlarmRepository.findByAlarmTime(now);
        if (alarms.isEmpty()) {
            log.info("현재 알람이 없습니다.");
            return;
        }

        log.info("{}개의 알람이 조회됨: {}", alarms.size(), alarms);

        for (IntakeAlarm alarm : alarms) {
            sendPushNotification(alarm);
        }
    }

    private void sendPushNotification(IntakeAlarm alarm) {
        log.info("푸시 알림 준비 중: {} - {}", alarm.getUserSupplement().getSupplementName(), alarm.getAlarmTime());

        Optional<UserAuth> userAuth = userAuthRepository.findByUser(alarm.getUser());
        if (userAuth.isEmpty() || userAuth.get().getFcmToken() == null) {
            log.warn("FCM 토큰이 없는 사용자: {}", alarm.getUser().getId());
            return;
        }

        String fcmToken = userAuth.get().getFcmToken();
        log.info("FCM 토큰이 존재함: {}", fcmToken);

        String title = "영양제 복용 알림";
        String body = "지금 "+ alarm.getUserSupplement().getSupplementName() + "을(를) 복용할 시간입니다!";

        FCMPushRequestDto pushRequest = new FCMPushRequestDto(fcmToken, title, body);
        try {
            fcmService.pushAlarm(pushRequest);
            log.info("FCM 푸시 알림 전송 완료: {}", body);
        } catch (Exception e) {
            log.error("FCM 푸시 알림 전송 실패: {}", e.getMessage());
        }
    }
}
