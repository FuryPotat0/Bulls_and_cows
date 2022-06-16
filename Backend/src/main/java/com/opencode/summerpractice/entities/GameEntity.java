package com.opencode.summerpractice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game_entity")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id", nullable = false)
    private Long id;

    @Column(name = "game_time")
    private Long gameTime;

    @Column(name = "turns_number")
    private int turnsNumber;

    @Column(name = "is_win")
    private boolean isWin;

    @Column(name = "hidden_number")
    private String hiddenNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<TurnEntity> turns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "is_end")
    private Boolean isEnd;
}