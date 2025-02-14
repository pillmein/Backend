package org.homerunball.pillmein.supplement.domain;

import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import org.homerunball.pillmein.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
public class Favorite extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_supplement_id", nullable = false)
    private ApiSupplement apiSupplement;

    @Column(name = "img_url")
    private String imgUrl;
}
