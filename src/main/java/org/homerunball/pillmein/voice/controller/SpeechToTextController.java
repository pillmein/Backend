package org.homerunball.pillmein.voice.controller;

import lombok.RequiredArgsConstructor;
import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.voice.controller.dto.SpeechToTextResponse;
import org.homerunball.pillmein.voice.service.SpeechToTextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/speech-to-text")
@RequiredArgsConstructor
public class SpeechToTextController {

    private final SpeechToTextService speechToTextService;

    @PostMapping
    public ResponseEntity<SuccessResponse<SpeechToTextResponse>> convertVoiceToText(
            @RequestParam("voiceFile") MultipartFile voiceFile) {

        String recognizedText = speechToTextService.recognize(voiceFile);
        SpeechToTextResponse responseDto = new SpeechToTextResponse(recognizedText);

        return SuccessResponse.of(HttpStatus.OK, "음성 인식 성공", responseDto);
    }

}