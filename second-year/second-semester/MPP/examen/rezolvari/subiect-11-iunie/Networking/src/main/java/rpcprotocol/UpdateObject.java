package rpcprotocol;

import domain.Game;
import domain.ProposedWord;

import java.io.Serializable;
import java.util.List;

public class UpdateObject implements Serializable {
    private List<ProposedWord> proposedWords;
    private Game game;

    private Integer score1;
    private Integer score2;
    private Integer score3;

    public UpdateObject(List<ProposedWord> proposedWords, Game game) {
        this.proposedWords = proposedWords;
        this.game = game;
    }

    public List<ProposedWord> getProposedWords() {
        return proposedWords;
    }

    public void setProposedWords(List<ProposedWord> proposedWords) {
        this.proposedWords = proposedWords;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
    }
}
