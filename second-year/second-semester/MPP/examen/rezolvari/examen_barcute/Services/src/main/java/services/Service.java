package services;

import domain.Game;
import domain.Player;

public interface Service {
    void login(Player player, Observer client) throws LoginException;
    void logout(Player player, Observer client) throws LoginException;

    void startGame(Game game, Observer mainController);
//    void startGame(ProposedWord proposedWord, Observer mainCtrl);
//    void move(Proposal proposal);
}
