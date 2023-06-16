package domain;

public class Entity<ID> {

    private ID id;
    private static final long serialVersionNewId = 123456789123L;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
