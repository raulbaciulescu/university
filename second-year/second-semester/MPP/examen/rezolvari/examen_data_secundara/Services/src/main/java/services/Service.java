package services;

import domain.Game;
import domain.GameDTO;
import domain.Player;
import domain.UpdateObject;

import java.util.List;

public interface Service {
    void login(Player player, Observer client) throws LoginException;
    void logout(Player player, Observer client) throws LoginException;
    GameDTO startGame(String alias);
    //void updateGame(UpdateObject updateObject);
    void updateGame(Game game);

    List<Game> getFinishedGames();

//    void move(Proposal proposal);
}
