package data.model;

//import java.util.ArrayList;
//import java.util.List;

import java.util.Objects;


public class User {

    private final String username;
    private final String password;
    private final long points;
    private final String mail;

    public User(String username, String password, long points , String mail) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getPoints() {
        return points;
    }

    public String getMail() {
        return mail;
    }


    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
