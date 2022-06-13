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
        StringBuilder str = new StringBuilder();
        str.append(id);
        str.append(", ");
        str.append(timePast / 100);
        str.append(", ");
        str.append(userNumber);
        return str.toString();
    }
}