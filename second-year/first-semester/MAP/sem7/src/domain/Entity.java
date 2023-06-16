package domain;

public class Entity <ID>{
    private ID id;
    private static final long serialVersionNewId = 123456789123L;

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

}
