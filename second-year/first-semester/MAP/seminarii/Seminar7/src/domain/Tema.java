package domain;

public class Tema {
    private String id;
    private String descriere;

    public Tema(String id, String descriere){
        this.id = id;
        this.descriere = descriere;
    }

    public String getId(){
        return this.id;
    }

    public String getDescriere(){
        return this.descriere;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setDescriere(String descriere){
        this.descriere = descriere;
    }

    @Override
    public String toString(){
        return id + " " + descriere;
    }

}
