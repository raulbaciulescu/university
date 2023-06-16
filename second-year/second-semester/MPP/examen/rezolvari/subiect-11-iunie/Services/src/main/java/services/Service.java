package services;

import java.util.List;
import domain.Player;
import domain.Proposal;
import domain.ProposedWord;

public interface Service {
    Player login(Player player, Observer client) throws LoginException;
    void logout(Player player, Observer client) throws LoginException;
    void startGame(ProposedWord proposedWord, Observer mainCtrl);
    void move(Proposal proposal);
}
