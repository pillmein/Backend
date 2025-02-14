package org.homerunball.pillmein.intake.domain;

import java.time.LocalTime;

import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import org.homerunball.pillmein.intake.domain.enums.RepeatType;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "intake_alarm")
@Getter
@Setter
@NoArgsConstructor
public class IntakeAlarm extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_supplement_id", nullable = false)
    private UserSupplement userSupplement;

    @Column(name = "alarm_time")
    private LocalTime alarmTime;

    @Column(name = "repeat_type")
    @Enumerated(EnumType.STRING)
    private RepeatType repeatType;
}
