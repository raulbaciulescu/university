import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Persoana {
    //trebuie cu - in diagrama uml
    private String cnp;
    private String nume;
    private Date dataPermis;
    private Set<Autovehicul> autoDetinute;
    private Set<Autovehicul> autoConduce;

    public Persoana(String cnp, String nume, Date dataPermis) {
        this.cnp = cnp;
        this.nume = nume;
        this.dataPermis = dataPermis;
        this.autoDetinute = new HashSet<>();
        this.autoConduce = new HashSet<>();
    }

    public void basicAddToAutoDetinute(Autovehicul autovehicul) {
        autoDetinute.add(autovehicul);
    }

    public void addToAutoDetinute(Autovehicul autovehicul) {
            Persoana proprietarVechi = autovehicul.getProprietar();
            Persoana proprietarNou = this;
            proprietarVechi.basicRemoveFromAutoDetinute(autovehicul);
            proprietarNou.basicAddToAutoDetinute(autovehicul);
            autovehicul.basicSetProprietar(proprietarNou);
    }

    public Set<Autovehicul> getAutoDetinute() {
        return Collections.unmodifiableSet(this.autoDetinute);
    }

    public addToAutoConduce(Autovehicul autovehicul) {
        this.basicAddToAutoConduse(autovehicul);
        autovehicul.basicAddToSoferi(this);
    }
}