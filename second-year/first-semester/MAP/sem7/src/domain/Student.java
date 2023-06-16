package domain;

public class Student extends Entity<Long> {
    private int group;
    private String name;

    public int getGroup() {
        return group;
    }

    public Student(int group, String name) {
        this.group = group;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId().toString() + " " + group + " " + name;
    }
}
