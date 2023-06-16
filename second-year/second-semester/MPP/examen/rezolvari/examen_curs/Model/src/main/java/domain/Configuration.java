package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@javax.persistence.Entity
@Table( name = "configuration")
public class Configuration extends Entity<Integer> {
    private Integer conf1;
    private Integer conf2;
    private Integer conf3;
    private Integer conf4;
    private Integer conf5;

    public Configuration(Integer conf1, Integer conf2, Integer conf3, Integer conf4, Integer conf5) {
        this.conf1 = conf1;
        this.conf2 = conf2;
        this.conf3 = conf3;
        this.conf4 = conf4;
        this.conf5 = conf5;
    }

    public Configuration() {
    }

    public Integer getConf1() {
        return conf1;
    }

    public void setConf1(Integer conf1) {
        this.conf1 = conf1;
    }

    public Integer getConf2() {
        return conf2;
    }

    public void setConf2(Integer conf2) {
        this.conf2 = conf2;
    }

    public Integer getConf3() {
        return conf3;
    }

    public void setConf3(Integer conf3) {
        this.conf3 = conf3;
    }

    public Integer getConf4() {
        return conf4;
    }

    public void setConf4(Integer conf4) {
        this.conf4 = conf4;
    }

    public Integer getConf5() {
        return conf5;
    }

    public void setConf5(Integer conf5) {
        this.conf5 = conf5;
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
