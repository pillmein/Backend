package org.homerunball.pillmein.auth.domain;

import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import org.homerunball.pillmein.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_auth")
@Getter
@Setter
@NoArgsConstructor
public class UserAuth extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "platform_id")
    private String platformId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcmToken;
}
