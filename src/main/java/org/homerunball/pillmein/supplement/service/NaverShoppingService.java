package org.homerunball.pillmein.supplement.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.homerunball.pillmein.common.exception.ErrorCode;
import org.homerunball.pillmein.common.exception.PillmeinException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NaverShoppingService {
    private final RestClient restClient;
    private final String clientId;
    private final String clientSecret;

    public NaverShoppingService(
            @Value("${naver.api.client-id}") String clientId,
            @Value("${naver.api.client-secret}") String clientSecret) {

        this.restClient = RestClient.create();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String searchImageUrl(String supplementName) {
        try {
            String url = "https://openapi.naver.com/v1/search/shop.json?query=" + supplementName;

            ResponseEntity<String> response = restClient.get()
                    .uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header("X-Naver-Client-Id", clientId)
                    .header("X-Naver-Client-Secret", clientSecret)
                    .retrieve()
                    .toEntity(String.class);

            if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
                throw new PillmeinException(ErrorCode.NAVER_API_ERROR);
            }

            String imageUrl = extractImageUrl(response.getBody());

            if (imageUrl.isEmpty()) {
                throw new PillmeinException(ErrorCode.NAVER_API_ERROR);
            }

            return imageUrl;
        } catch (Exception e) {
            throw new PillmeinException(ErrorCode.NAVER_API_ERROR);
        }
    }

    private String extractImageUrl(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode items = root.path("items");

            System.out.println("네이버 API 응답 JSON: " + responseBody);

            if (items.isArray() && items.size() > 0) {
                JsonNode firstItem = items.get(0);
                return firstItem.path("image").asText();
            }
        } catch (Exception e) {
            throw new PillmeinException(ErrorCode.NAVER_API_ERROR);
        }
        return "";
}}
