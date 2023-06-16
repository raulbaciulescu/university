package exam.model;

import java.io.Serializable;

public class Player implements Entity<String>, Serializable {
    private String alias;

    public Player(){

    }
    public Player(String alias) {
        this.alias = alias;
    }

    @Override
    public String getID() {
        return alias;
    }

    @Override
    public void setID(String s) {
        alias=s;
    }
}
