package domain;

import java.util.List;

public class GameRest {
    private Game game;
    private List<Proposal> proposals;
    private Integer len;

    public GameRest(Game game, List<Proposal> proposals, Integer len) {
        this.game = game;
        this.proposals = proposals;
        this.len = len;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }
}
