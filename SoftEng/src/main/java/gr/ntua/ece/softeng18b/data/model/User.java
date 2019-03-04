package gr.ntua.ece.softeng18b.data.model;

import java.util.Objects;


public class User {

    private final long id;
    private final String username;
    private final String password;
    private final String mail;

    public User(long id, String username, String password, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username == user.username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
