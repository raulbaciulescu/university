package domain;

import java.io.Serializable;
import java.util.List;

public class GameDTO implements Serializable {
    private Game game;
    private Configuration configuration;
    private List<Boolean> visited;

    public GameDTO(Game game, Configuration configuration, List<Boolean> visited) {
        this.game = game;
        this.configuration = configuration;
        this.visited = visited;
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

    public List<Boolean> getVisited() {
        return visited;
    }

    public void setVisited(List<Boolean> visited) {
        this.visited = visited;
    }
}
