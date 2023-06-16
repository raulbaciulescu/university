package services;


import domain.Game;
import domain.ProposedWord;

import java.util.List;

public interface Observer {
    void startGameUpdate(List<ProposedWord> proposedWords, Game game);
}
