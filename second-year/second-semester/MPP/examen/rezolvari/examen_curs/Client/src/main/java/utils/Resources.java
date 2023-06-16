package utils;

import domain.Player;

public class Resources {
    static Resources instance = null;
    private Player loggedPlayer;

    private Resources() {

    }
    public static Resources getInstance() {
        if (instance == null)
            instance = new Resources();
        return instance;
    }

    public Player getLoggedPlayer() {
        return loggedPlayer;
    }

    public void setLoggedPlayer(Player loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }
}
