package domain;

public class GameDTO {
    Game game;
    Player player1;
    Player player2;
    Player winner;

    public GameDTO(Game game, Player player1, Player player2, Player winner) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public GameDTO(Game game, Player loggedPlayer) {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
