package exam.model.dto;

import exam.model.Configuration;
import exam.model.Game;

import java.io.Serializable;

public class GameDTO implements Serializable {
    private Game game;
    private Configuration conf;

    public GameDTO(Game game, Configuration conf) {
        this.game = game;
        this.conf = conf;
    }
    public GameDTO(){

    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }
}
