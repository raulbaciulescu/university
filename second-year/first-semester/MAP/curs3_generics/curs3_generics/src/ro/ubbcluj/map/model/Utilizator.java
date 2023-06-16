package ro.ubbcluj.map.model;

import java.util.List;

public class Utilizator extends Entity<Long>{
    private String firstName;
    private String lastName;
    public List<Utilizator> friendList;

    public Utilizator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }

    public List<Utilizator> getFriendList()
    {
        return friendList;
    }
    public void addFriend(Utilizator friend) {
        this.friendList.add(friend);
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friendList=" + friendList +
                '}';
    }
}
