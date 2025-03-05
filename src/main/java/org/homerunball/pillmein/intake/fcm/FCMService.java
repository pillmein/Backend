package org.homerunball.pillmein.intake.fcm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.http.HttpHeaders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.homerunball.pillmein.auth.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {
    private final ObjectMapper objectMapper;
    private final UserAuthRepository userAuthRepository; // TODO: 추후 fcm_token이 저장되면 활성화

    @Value("${fcm.key.path}")
    private String SERVICE_ACCOUNT_JSON;

    @Value("${fcm.api.url}")
    private String FCM_API_URL;

    @Transactional
    public String pushAlarm(FCMPushRequestDto request) throws IOException {
        /*
        TODO: 추후 프론트에서 fcm_token 제공 시 활성화
        UserAuth userAuth = userAuthRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 인증 정보가 없습니다."));

        String fcmToken = userAuth.getFcmToken();
        if (fcmToken == null || fcmToken.isEmpty()) {
            throw new IllegalArgumentException("해당 유저의 FCM 토큰이 존재하지 않습니다.");
        }

        String message = makeSingleMessage(request.getTitle(), request.getBody(), fcmToken);
        sendPushMessage(message);
        */

        // 지금은 더미 토큰을 사용하여 테스트
        String testToken = "dummy_fcm_token";

        String message = makeSingleMessage(request.getTitle(), request.getBody(), testToken);
        sendPushMessage(message);
        return "알림을 성공적으로 전송했습니다. userId = " + testToken;
    }

    private String makeSingleMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FCMMessage fcmMessage = FCMMessage.builder()
                .message(FCMMessage.Message.builder()
                        .token(targetToken)
                        .notification(FCMMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .build())
                        .build()
                ).validateOnly(false)
                .build();
        return objectMapper.writeValueAsString(fcmMessage);
    }

    private void sendPushMessage(String message) {
        log.info("FCM 메시지 전송 시작...");

        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
            Request httpRequest = new Request.Builder()
                    .url(FCM_API_URL)
                    .post(requestBody)
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                    .build();
            Response response = client.newCall(httpRequest).execute();
            log.info("푸시 알림 전송 성공: {}", response.body().string());
        } catch (IOException e) {
            throw new IllegalArgumentException("FCM 메시지 전송 실패", e);
        }
    }

    private String getAccessToken() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(SERVICE_ACCOUNT_JSON).getInputStream())
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

            googleCredentials.refreshIfExpired();
            String token = googleCredentials.getAccessToken().getTokenValue();

            return token;
        } catch (IOException e) {
            log.error("Firebase Access Token을 가져오는 데 실패했습니다.", e);
            throw new IllegalArgumentException("Firebase Access Token을 가져오는 데 실패했습니다.", e);
        }
    }

}
