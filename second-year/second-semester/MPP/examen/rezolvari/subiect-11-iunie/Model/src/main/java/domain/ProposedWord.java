package domain;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "proposedWord")
public class ProposedWord extends MyEntity<Integer> implements Serializable {
    private String word;
    private Integer playerId;
    private Integer gameId;
    private String positions;

    public ProposedWord() {

    }

    public ProposedWord(String word, Integer playerId, Integer gameId, String positions) {
        this.word = word;
        this.playerId = playerId;
        this.gameId = gameId;
        this.positions = positions;
    }

    public ProposedWord(String word, Integer playerId, String positions) {
        this.word = word;
        this.playerId = playerId;
        this.positions = positions;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
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

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer aLong) {
        super.setId(aLong);
    }

    @Override
    public String toString() {
        return "word id: " + getId().toString() + " player id: " + playerId.toString() + ", positions: " + positions;
    }
}
