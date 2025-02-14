package org.homerunball.pillmein.auth.service;

import org.homerunball.pillmein.common.exception.ErrorCode;
import org.homerunball.pillmein.common.exception.InvalidRequestBodyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleAuthService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GOOGLE_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo?id_token=";

    public Map<String, Object> verifyIdToken(String idToken) {
        ResponseEntity<Map> response = restTemplate.getForEntity(GOOGLE_TOKEN_INFO_URL + idToken, Map.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new InvalidRequestBodyException(ErrorCode.INVALID_ID_TOKEN);
        }
        return response.getBody();
    }
}
