package exam.model;

import java.io.Serializable;

public class Configuration implements Entity<Integer>, Serializable {
    private Integer id;
    private int x;
    private int y;
    private int value;

    public Configuration(Integer id, int x, int y, int value) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.value = value;
    }
    public Configuration(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        id=integer;
    }

    @Override
    public String toString() {
        return x+","+y+","+value;
    }
}
