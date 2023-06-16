package domain;

import domain.Game;

import java.io.Serializable;
import java.util.List;

public class UpdateObject implements Serializable {
    private Game game;

    private Integer value;
    private boolean finish;

    public UpdateObject(Game game, Integer value, boolean finish) {
        this.game = game;
        this.value = value;
        this.finish = finish;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
