package com.opencode.summerpractice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "turn_entity")
public class TurnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_past")
    private Long timePast;

    @Column(name = "user_number")
    private String userNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @Override
    public String toString(){
        return "введённое число: " + userNumber +
                ", времени прошло: " +
                timePast / 1000 + " секунд";
    }
}