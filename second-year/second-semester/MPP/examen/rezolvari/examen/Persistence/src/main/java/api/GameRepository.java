package api;

import domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends Repository<Integer, Game> {
    //Optional<Game> findLastGame();
    List<Game> getGamesByAlias(String alias);
}
