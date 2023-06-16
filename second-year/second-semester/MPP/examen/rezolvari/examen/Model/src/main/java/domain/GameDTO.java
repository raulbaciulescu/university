package domain;

import java.io.Serializable;
import java.util.List;

public class GameDTO implements Serializable {
    private Game game;
    private Configuration configuration;

    public GameDTO(Game game, Configuration configuration) {
        this.game = game;
        this.configuration = configuration;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
