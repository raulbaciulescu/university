package user.annotations;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "user")
public class UserAnnot {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;


    public UserAnnot() {
        // this form used by Hibernate
    }

//    public User(String username, String password, String firstName, String lastName) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    public UserAnnot(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
    }

    @Id
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
