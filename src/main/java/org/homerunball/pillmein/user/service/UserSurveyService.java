package org.homerunball.pillmein.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.homerunball.pillmein.user.controller.dto.UserSurveyResponseDto;
import org.homerunball.pillmein.user.domain.UserSurvey;
import org.homerunball.pillmein.user.controller.dto.UserSurveyRequestDto;
import org.homerunball.pillmein.user.repository.UserSurveyRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserSurveyService {
    private final UserSurveyRepository userSurveyRepository;

    @Transactional
    public UserSurveyResponseDto getUserSurvey(Long userId) {
        UserSurvey userSurvey = userSurveyRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 설문 데이터를 찾을 수 없습니다."));

        return UserSurveyResponseDto.from(userSurvey);
    }

    @Transactional
    public void updateUserSurvey(Long userId, UserSurveyRequestDto requestDto) {
        UserSurvey userSurvey = userSurveyRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 설문 데이터를 찾을 수 없습니다."));

        boolean isOnboarding = userSurvey.getEyeFatigue() == null;

        if (isOnboarding) {
            saveAllFields(userSurvey, requestDto);
        } else {
            updateNonNullFields(userSurvey, requestDto);
        }

        userSurveyRepository.save(userSurvey);
    }

    private void saveAllFields(UserSurvey userSurvey, UserSurveyRequestDto requestDto) {
        userSurvey.setOutdoorActivity(Objects.requireNonNull(requestDto.outdoorActivity(), "outdoorActivity 필수 입력"));
        userSurvey.setSedentaryHours(Objects.requireNonNull(requestDto.sedentaryHours(), "sedentaryHours 필수 입력"));
        userSurvey.setSleepDuration(Objects.requireNonNull(requestDto.sleepDuration(), "sleepDuration 필수 입력"));
        userSurvey.setScreenTime(Objects.requireNonNull(requestDto.screenTime(), "screenTime 필수 입력"));
        userSurvey.setCaffeineIntake(Objects.requireNonNull(requestDto.caffeineIntake(), "caffeineIntake 필수 입력"));
        userSurvey.setMealPattern(Objects.requireNonNull(requestDto.mealPattern(), "mealPattern 필수 입력"));
        userSurvey.setAlcoholFrequency(Objects.requireNonNull(requestDto.alcoholFrequency(), "alcoholFrequency 필수 입력"));
        userSurvey.setPhysicalFatigue(requestDto.physicalFatigue());
        userSurvey.setMentalFatigue(requestDto.mentalFatigue());
        userSurvey.setDigestionIssues(requestDto.digestionIssues());
        userSurvey.setHeadacheDizziness(requestDto.headacheDizziness());
        userSurvey.setInfectionFrequency(requestDto.infectionFrequency());
        userSurvey.setSkinConcern(requestDto.skinConcern());
        userSurvey.setWeightChange(requestDto.weightChange());
        userSurvey.setDietMethod(requestDto.dietMethod());
        userSurvey.setSleepDisruption(requestDto.sleepDisruption());
        userSurvey.setSeasonalDiscomfort(requestDto.seasonalDiscomfort());
        userSurvey.setEyeFatigue(requestDto.eyeFatigue());
        userSurvey.setPainFrequency(requestDto.painFrequency());
        userSurvey.setFocusMemoryIssues(requestDto.focusMemoryIssues());
        userSurvey.setBrittleNailsHair(requestDto.brittleNailsHair());
        userSurvey.setHealthPurpose(requestDto.healthPurpose());
    }

    private void updateNonNullFields(UserSurvey userSurvey, UserSurveyRequestDto requestDto) {
        if (requestDto.outdoorActivity() != null) userSurvey.setOutdoorActivity(requestDto.outdoorActivity());
        if (requestDto.sedentaryHours() != null) userSurvey.setSedentaryHours(requestDto.sedentaryHours());
        if (requestDto.sleepDuration() != null) userSurvey.setSleepDuration(requestDto.sleepDuration());
        if (requestDto.screenTime() != null) userSurvey.setScreenTime(requestDto.screenTime());
        if (requestDto.caffeineIntake() != null) userSurvey.setCaffeineIntake(requestDto.caffeineIntake());
        if (requestDto.mealPattern() != null) userSurvey.setMealPattern(requestDto.mealPattern());
        if (requestDto.alcoholFrequency() != null) userSurvey.setAlcoholFrequency(requestDto.alcoholFrequency());
        if (requestDto.physicalFatigue() != null) userSurvey.setPhysicalFatigue(requestDto.physicalFatigue());
        if (requestDto.mentalFatigue() != null) userSurvey.setMentalFatigue(requestDto.mentalFatigue());
        if (requestDto.digestionIssues() != null) userSurvey.setDigestionIssues(requestDto.digestionIssues());
        if (requestDto.headacheDizziness() != null) userSurvey.setHeadacheDizziness(requestDto.headacheDizziness());
        if (requestDto.infectionFrequency() != null) userSurvey.setInfectionFrequency(requestDto.infectionFrequency());
        if (requestDto.skinConcern() != null) userSurvey.setSkinConcern(requestDto.skinConcern());
        if (requestDto.weightChange() != null) userSurvey.setWeightChange(requestDto.weightChange());
        if (requestDto.dietMethod() != null) userSurvey.setDietMethod(requestDto.dietMethod());
        if (requestDto.sleepDisruption() != null) userSurvey.setSleepDisruption(requestDto.sleepDisruption());
        if (requestDto.seasonalDiscomfort() != null) userSurvey.setSeasonalDiscomfort(requestDto.seasonalDiscomfort());
        if (requestDto.eyeFatigue() != null) userSurvey.setEyeFatigue(requestDto.eyeFatigue());
        if (requestDto.painFrequency() != null) userSurvey.setPainFrequency(requestDto.painFrequency());
        if (requestDto.focusMemoryIssues() != null) userSurvey.setFocusMemoryIssues(requestDto.focusMemoryIssues());
        if (requestDto.brittleNailsHair() != null) userSurvey.setBrittleNailsHair(requestDto.brittleNailsHair());
        if (requestDto.healthPurpose() != null) userSurvey.setHealthPurpose(requestDto.healthPurpose());
    }
}
