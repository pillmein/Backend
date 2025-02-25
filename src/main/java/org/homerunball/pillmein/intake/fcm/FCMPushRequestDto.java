package org.homerunball.pillmein.intake.fcm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FCMPushRequestDto {
    private String targetToken; //TODO: 나중에 프론트에서 fcm_token 제공 시 활성화

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String body;

    public static FCMPushRequestDto sendTestPush(String targetToken) {

        return FCMPushRequestDto.builder()
                .targetToken(targetToken)
                .title("💚Pill Me In💚")
                .body("영양제 복용하세요")
                .build();
    }
}
