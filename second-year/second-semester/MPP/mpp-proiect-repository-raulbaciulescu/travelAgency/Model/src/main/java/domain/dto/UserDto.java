package domain.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserNetworkingDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
//    private Long id;
//    private String passwd;
//
//    public UserDto(Long id) {
//        this(id,"");
//    }
//
//    public UserDto(Long id, String passwd) {
//        this.id = id;
//        this.passwd = passwd;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPasswd() {
//        return passwd;
//    }
//
//    @Override
//    public String toString(){
//        return "UserDTO["+id+' '+passwd+"]";
//    }
}
