package org.homerunball.pillmein.voice.service;

import lombok.RequiredArgsConstructor;
import org.homerunball.pillmein.common.exception.ErrorCode;
import org.homerunball.pillmein.common.exception.PillmeinException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpeechToTextService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${naver.clova.client-id}")
    private String clientId;

    @Value("${naver.clova.client-secret}")
    private String clientSecret;

    private static final String CLOVA_API_URL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=Kor";

    public String recognize(MultipartFile voiceFile) {
        validateFile(voiceFile);

        try {
            byte[] fileBytes = StreamUtils.copyToByteArray(voiceFile.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
            headers.set("X-NCP-APIGW-API-KEY", clientSecret);

            HttpEntity<byte[]> requestEntity = new HttpEntity<>(fileBytes, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    CLOVA_API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                Object text = response.getBody().get("text");
                if (text == null || text.toString().isBlank()) {
                    throw new PillmeinException(ErrorCode.STT_EMPTY_RESULT);
                }
                return text.toString();
            } else {
                throw new PillmeinException(ErrorCode.STT_FAILED);
            }

        } catch (IOException e) {
            throw new PillmeinException(ErrorCode.STT_FAILED);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new PillmeinException(ErrorCode.VOICE_FILE_EMPTY);
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".mp3")) {
            throw new PillmeinException(ErrorCode.INVALID_FILE_EXTENSION);
        }

        if (file.getSize() > 3 * 1024 * 1024) {
            throw new PillmeinException(ErrorCode.FILE_SIZE_EXCEEDED);
        }
    }
}