package domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "proposal")
public class Proposal extends Entity<Integer> {
    private Integer gameId;
    private String word;
    private Integer playerId;
    private boolean ghicit;


    public Proposal() {
    }

    public Proposal(Integer gameId, String word, Integer playerId, boolean ghicit) {
        this.gameId = gameId;
        this.word = word;
        this.playerId = playerId;
        this.ghicit = ghicit;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public boolean isGhicit() {
        return ghicit;
    }

    public void setGhicit(boolean ghicit) {
        this.ghicit = ghicit;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer integer) {
        super.setId(integer);
    }

}
