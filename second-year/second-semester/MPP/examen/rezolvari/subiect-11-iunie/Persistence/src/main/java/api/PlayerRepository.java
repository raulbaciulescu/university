package api;

import domain.Player;

import java.util.Optional;

public interface PlayerRepository extends Repository<Integer, Player> {
    Optional<Player> findPlayer(String username, String password);
}
