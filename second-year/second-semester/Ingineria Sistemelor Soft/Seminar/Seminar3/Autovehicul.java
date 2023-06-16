public class Autovehicul {
    private String nrInmatriculare;
    private int anFabricatie;
    private String model;
    private Set<Persoana> soferi;
    private Persoana proprietar;

    //persoana va fi referinta valida
    //asociere bidirectionala, 2 ascocieri unidirectionale sincronizate!!
    //
    public Autovehicul(String nrInmatriculare, int anFabricatie,
                String model, Persoana persoana) {
        this.nrInmatriculare = nrInmatriculare;
        this.anFabricatie = anFabricatie;
        this.proprietara = persoana;
        proprietar.basicAddToAutoDetinute(this);
    }

    public void setProprietar(Persoana persoana) {
        persoana.addToAutoDetinute(this);
    }

    public addToSoferi(Persoana sofer) {
        this.basicAddToSoferi(soferi);
        sofer.basicAddToAutoConduse(this);
    }
}