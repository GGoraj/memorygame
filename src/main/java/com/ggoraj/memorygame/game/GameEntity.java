package com.ggoraj.memorygame.game;

import com.ggoraj.memorygame.score.Score;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="games")
    @Getter
    @Setter
    @NoArgsConstructor
public class GameEntity {

        @Id
        @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        @Column(name = "id", nullable = false)
        private String id;

        @Column(name="user_id", nullable = false)
        private Long userId;

        @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        @JoinColumn(name = "score_id", referencedColumnName = "id")
        private Score score;

        public GameEntity(Long userId) {
            this.userId = userId;
        }


    }