package gr.ntua.ece.softeng18b.data.model;


import java.util.Objects;

public class Price {

    private final long id;
    private final long pid;
    private final long sid;
    private final double price;
    private final String date;

    public Price(long id, double price, long pid, long sid, String date) {
        this.id     = id;
        this.price  = price;
        this.pid    = pid;
        this.sid    = sid;
        this.date   = date;
    }

    public long getId() {
        return id;
    }

    public long getPid() {
        return pid;
    }

    public long getSid() {
        return sid;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return id == price.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
