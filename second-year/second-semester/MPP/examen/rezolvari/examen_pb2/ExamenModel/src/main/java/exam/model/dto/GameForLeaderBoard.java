package exam.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GameForLeaderBoard implements Serializable {
    private String alias;
    private LocalDateTime date;
    private int points;
    private int value;

    public GameForLeaderBoard(){

    }

    public GameForLeaderBoard(String alias, LocalDateTime date, int points, int value) {
        this.alias = alias;
        this.date = date;
        this.points = points;
        this.value = value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return alias + ", "+ date +
                ", points=" + points +
                ", value=" + value +
                '}';
    }
}
