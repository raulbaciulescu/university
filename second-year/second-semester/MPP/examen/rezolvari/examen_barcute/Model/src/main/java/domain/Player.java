package domain;


import javax.persistence.*;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
////


@javax.persistence.Entity
@Table( name = "player" )
public class Player extends Entity<Integer> {
    private String alias;

    public Player() {

    }

    public Player(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Player{" +
                "alias='" + alias + '\'' +
                '}';
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
}
