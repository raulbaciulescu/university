package services;
import domain.GameDTO;

import java.util.List;

public interface Observer {
    void startGameUpdate(GameDTO gameDTO);
}
