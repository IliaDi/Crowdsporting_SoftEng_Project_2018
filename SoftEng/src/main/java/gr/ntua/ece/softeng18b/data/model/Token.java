package gr.ntua.ece.softeng18b.data.model;


import java.util.Objects;

public class Token {

    private final long id;
    private final String token;

    public Token(long id, String token) {
        this.id     = id;
        this.token   = token;
    }

    public long getId() {
        return id;
    }


    public String getToken() {
        return token;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return id == token.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
