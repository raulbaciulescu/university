package domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@javax.persistence.Entity
@Table( name = "game")
public class Game extends Entity<Integer> {
    private Integer score;
    private Integer idConfiguration;
    private boolean finish;
    private LocalDateTime startDate;
    private String alias;

    private String word;

    public Game() {

    }

    public Game(Integer score, Integer idConfiguration, boolean finish, LocalDateTime startDate, String alias) {
        this.score = score;
        this.idConfiguration = idConfiguration;
        this.finish = finish;
        this.startDate = startDate;
        this.alias = alias;
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

    public boolean getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
                ", finish=" + finish +
                ", startDate=" + startDate +
                ", alias='" + alias + '\'' +
                '}';
    }
}
