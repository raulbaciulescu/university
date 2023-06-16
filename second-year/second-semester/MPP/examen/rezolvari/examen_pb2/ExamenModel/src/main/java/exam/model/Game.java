package exam.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name="games")
public class Game implements Entity<Integer>, Serializable {
    private Integer id;
    private int idConf;
    private String alias;
    private int points;
    private int value;
    private int finished;
    private LocalDateTime date;

    public Game(){}

    public int getIdConf() {
        return idConf;
    }

    public Game(Integer id, int idConf, String alias, LocalDateTime date) {
        this.id = id;
        this.idConf = idConf;
        this.alias = alias;
        this.date = date;
        points=0;
        value=-1;
        finished=0;
    }

    public Game(Integer id, int idConf, String alias, int points, int value, int finished, LocalDateTime date) {
        this.id = id;
        this.idConf = idConf;
        this.alias = alias;
        this.points = points;
        this.value = value;
        this.finished = finished;
        this.date = date;
    }

    public void setIdConf(int idConf) {
        this.idConf = idConf;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        id=integer;
    }
}
