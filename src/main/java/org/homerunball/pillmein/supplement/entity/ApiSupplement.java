package org.homerunball.pillmein.supplement.entity;

import lombok.*;
import org.homerunball.pillmein.common.entity.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "api_supplements")
public class ApiSupplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String effects;

    @Column(columnDefinition = "TEXT")
    private String warnings;
}