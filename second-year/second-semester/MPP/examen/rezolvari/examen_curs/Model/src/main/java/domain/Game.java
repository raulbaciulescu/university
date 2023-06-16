package domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@javax.persistence.Entity
@Table( name = "game")
public class Game extends Entity<Integer> {
    private Integer score;
    private Integer idConfiguration;
    private boolean isFinished;
    private LocalDateTime startDate;
    private String alias;
    private Integer position;


    public Game() {

    }

    public Game(Integer score, Integer idConfiguration, boolean isFinished, LocalDateTime startDate, String alias,
                Integer position) {
        this.score = score;
        this.idConfiguration = idConfiguration;
        this.isFinished = isFinished;
        this.startDate = startDate;
        this.alias = alias;
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIdConfiguration() {
        return idConfiguration;
    }

    public void setIdConfiguration(Integer idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
        return "Game{" +
                "score=" + score +
                ", idConfiguration=" + idConfiguration +
                ", isFinished=" + isFinished +
                ", startDate=" + startDate +
                ", alias='" + alias + '\'' +
                '}';
    }
}
