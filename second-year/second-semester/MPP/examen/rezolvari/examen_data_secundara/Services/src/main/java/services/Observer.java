package services;
import domain.Game;

import java.util.List;

public interface Observer {
    void updateResults(List<Game> games);
}
