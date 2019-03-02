package gr.ntua.ece.softeng18b.data.model;

import java.util.Objects;


public class User {

    private final long id;
    private final String fullname;
    private final String password;
    private final String mail;

    public User(long id, String fullname, String password, String mail) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return fullname;
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
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
