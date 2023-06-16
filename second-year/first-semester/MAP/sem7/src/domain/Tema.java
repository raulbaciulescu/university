package domain;

public class Tema {
    private String id;
    private String descriere;

    public String getId() {
        return id;
    }

    public Tema(String id, String descriere) {
        this.id = id;
        this.descriere = descriere;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return id + " " + descriere;
    }
}
