package org.homerunball.pillmein.auth.service;

import org.homerunball.pillmein.auth.controller.dto.AuthApiResponse;
import org.homerunball.pillmein.auth.entity.UserAuth;
import org.homerunball.pillmein.auth.repository.UserAuthRepository;
import org.homerunball.pillmein.user.entity.User;
import org.homerunball.pillmein.user.entity.UserSurvey;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.homerunball.pillmein.user.repository.UserSurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final GoogleAuthService googleAuthService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final UserSurveyRepository userSurveyRepository; // 추가

    public AuthService(GoogleAuthService googleAuthService, JwtUtil jwtUtil,
                       UserRepository userRepository, UserAuthRepository userAuthRepository,
                       UserSurveyRepository userSurveyRepository) {
        this.googleAuthService = googleAuthService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
        this.userSurveyRepository = userSurveyRepository;
    }

    @Transactional
    public AuthApiResponse login(String idToken, String fcmToken) {
        Map<String, Object> tokenInfo = googleAuthService.verifyIdToken(idToken);
        String platformId = (String) tokenInfo.get("sub");

        Optional<UserAuth> existingAuth = userAuthRepository.findByPlatformId(platformId);

        String accessToken;
        String refreshToken;

        if (existingAuth.isPresent()) {
            UserAuth userAuth = existingAuth.get();
            refreshToken = jwtUtil.generateRefreshToken(userAuth.getUser().getId());
            userAuth.setRefreshToken(refreshToken);
            userAuth.setFcmToken(fcmToken);
            userAuthRepository.save(userAuth);

            accessToken = jwtUtil.generateAccessToken(userAuth.getUser().getId());
        } else {
            User newUser = new User();
            userRepository.save(newUser);

            refreshToken = jwtUtil.generateRefreshToken(newUser.getId());

            UserAuth newAuth = new UserAuth();
            newAuth.setUser(newUser);
            newAuth.setPlatformId(platformId);
            newAuth.setRefreshToken(refreshToken);
            newAuth.setFcmToken(fcmToken);
            userAuthRepository.save(newAuth);

            UserSurvey userSurvey = new UserSurvey();
            userSurvey.setUser(newUser);
            userSurveyRepository.save(userSurvey);

            accessToken = jwtUtil.generateAccessToken(newUser.getId());
        }
            return new AuthApiResponse(accessToken, refreshToken);
        }
    }