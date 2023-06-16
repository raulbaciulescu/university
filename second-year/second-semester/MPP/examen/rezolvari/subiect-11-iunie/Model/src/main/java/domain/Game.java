package domain;


import jakarta.persistence.*;

import java.io.Serializable;


@jakarta.persistence.Entity
@Table( name = "game")
public class Game extends MyEntity<Integer> implements Serializable {
    private String name;
    private Integer score1;
    private Integer score2;
    private Integer score3;

    public Game(String name) {
        this.name = name;
        score1 = 0;
        score2 = 0;
        score3 = 0;
    }

    public Game() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer aLong) {
        super.setId(aLong);
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
    }
}
