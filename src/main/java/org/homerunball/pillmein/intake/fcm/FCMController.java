package org.homerunball.pillmein.intake.fcm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody FCMPushRequestDto request) throws IOException {
        log.info("✅ Postman 요청 도착: targetToken={}, title={}, body={}",
                request.getTargetToken(), request.getTitle(), request.getBody()); // ✅ 로그 추가

        String response = fcmService.pushAlarm(request);
        return ResponseEntity.ok(response);
    }
}

