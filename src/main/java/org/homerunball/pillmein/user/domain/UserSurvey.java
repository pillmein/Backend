package org.homerunball.pillmein.user.domain;

import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_survey")
@Getter
@Setter
@NoArgsConstructor
public class UserSurvey extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 생활습관 관련 필드 (7개)
    @Column(name = "outdoor_activity")
    private String outdoorActivity;

    @Column(name = "sedentary_hours")
    private String sedentaryHours;

    @Column(name = "sleep_duration")
    private String sleepDuration;

    @Column(name = "screen_time")
    private String screenTime;

    @Column(name = "caffeine_intake")
    private String caffeineIntake;

    @Column(name = "meal_pattern")
    private String mealPattern;

    @Column(name = "alcohol_frequency")
    private String alcoholFrequency;

    // 건강고민 관련 필드 (14개)
    @Column(name = "physical_fatigue")
    private String physicalFatigue;

    @Column(name = "mental_fatigue")
    private String mentalFatigue;

    @Column(name = "digestion_issues")
    private String digestionIssues;

    @Column(name = "headache_dizziness")
    private String headacheDizziness;

    @Column(name = "infection_frequency")
    private String infectionFrequency;

    @Column(name = "skin_concern")
    private String skinConcern;

    @Column(name = "weight_change")
    private String weightChange;

    @Column(name = "diet_method")
    private String dietMethod;

    @Column(name = "sleep_disruption")
    private String sleepDisruption;

    @Column(name = "seasonal_discomfort")
    private String seasonalDiscomfort;

    @Column(name = "eye_fatigue")
    private String eyeFatigue;

    @Column(name = "pain_frequency")
    private String painFrequency;

    @Column(name = "focus_memory_issues")
    private String focusMemoryIssues;

    @Column(name = "brittle_nails_hair")
    private String brittleNailsHair;

    @Column(name = "health_purpose", columnDefinition = "TEXT")
    private String healthPurpose;
}
