package com.ggoraj.memorygame.score;

import com.ggoraj.memorygame.game.GameEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scores")
@Getter
@Setter
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "score")
    private int score = 0;

    @Column(name = "user_id")
    private int userId;

    @OneToOne(mappedBy = "score")
    private GameEntity game;

    public Score(int score){
        this.score = score;
    }

}
