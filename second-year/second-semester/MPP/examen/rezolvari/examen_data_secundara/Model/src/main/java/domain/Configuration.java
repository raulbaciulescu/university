package domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "configuration")
public class Configuration extends Entity<Integer> implements Serializable {
    private Integer x;
    private Integer y;
    private Integer value;

    public Configuration() {

    }

    public Configuration(Integer x, Integer y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
        return "Configuration{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
