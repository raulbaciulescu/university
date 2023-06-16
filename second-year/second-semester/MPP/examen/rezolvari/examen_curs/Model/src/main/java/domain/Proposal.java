package domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "proposal")
public class Proposal extends Entity<Integer> {
    private Integer x;
    private Integer y;
    private Integer gameId;

    public Proposal(Integer x, Integer y, Integer gameId) {
        this.x = x;
        this.y = y;
        this.gameId = gameId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
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
    public void setId(Integer integer) {
        super.setId(integer);
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "x=" + x +
                ", y=" + y +
                ", gameId=" + gameId +
                '}';
    }
}
