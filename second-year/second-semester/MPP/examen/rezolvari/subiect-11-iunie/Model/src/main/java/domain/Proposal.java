package domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;


@jakarta.persistence.Entity
@Table( name = "proposal")
public class Proposal extends MyEntity<Integer> implements Serializable {
    private Integer wordId;
    private Character letter;
    private Integer proposerId;

    public Proposal(Integer wordId, Character letter, Integer proposerId) {
        this.wordId = wordId;
        this.letter = letter;
        this.proposerId = proposerId;
    }

    public Proposal() {

    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Integer getProposerId() {
        return proposerId;
    }

    public void setProposerId(Integer proposerId) {
        this.proposerId = proposerId;
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
}
