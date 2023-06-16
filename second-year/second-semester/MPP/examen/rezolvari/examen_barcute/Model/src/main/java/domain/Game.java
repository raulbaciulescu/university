package domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@javax.persistence.Entity
@Table( name = "game")
public class Game extends Entity<Integer> {
    private Integer playerId1;
    private Integer playerId2;
    private Integer x1;
    private Integer y1;
    private Integer x2;
    private Integer y2;
    private Integer x3;
    private Integer y3;
    private Integer x4;
    private Integer y4;
    private Integer winnerId;

    public Game(Integer playerId1, Integer playerId2, Integer x1, Integer y1, Integer x2,
                Integer y2, Integer x3, Integer y3, Integer x4,
                Integer y4, Integer winnerId) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.winnerId = winnerId;
    }

    public Game() {
    }

    public Game(Player loggedPlayer, Integer x1, Integer y1, Integer x2, Integer y2) {
        this.playerId1 = playerId1;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Integer getPlayerId1() {
        return playerId1;
    }

    public void setPlayerId1(Integer playerId1) {
        this.playerId1 = playerId1;
    }

    public Integer getPlayerId2() {
        return playerId2;
    }

    public void setPlayerId2(Integer playerId2) {
        this.playerId2 = playerId2;
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Integer getX3() {
        return x3;
    }

    public void setX3(Integer x3) {
        this.x3 = x3;
    }

    public Integer getY3() {
        return y3;
    }

    public void setY3(Integer y3) {
        this.y3 = y3;
    }

    public Integer getX4() {
        return x4;
    }

    public void setX4(Integer x4) {
        this.x4 = x4;
    }

    public Integer getY4() {
        return y4;
    }

    public void setY4(Integer y4) {
        this.y4 = y4;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer integer) {
        super.setId(integer);
    }
}
