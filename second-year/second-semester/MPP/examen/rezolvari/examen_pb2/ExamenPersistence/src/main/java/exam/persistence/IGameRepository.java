package exam.persistence;

import exam.model.Game;

import java.util.List;

public interface IGameRepository extends Repository<Integer, Game> {
    List<Game> findFinishedForPlayer(String alias);
    List<Game> getFinished();
}
