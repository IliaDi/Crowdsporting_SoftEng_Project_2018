package gr.ntua.ece.softeng18b.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop {

    private final long id;
    private final String name;
    private final String address;
    private final double lng;
    private final double lat;
    private final boolean withdrawn;
    private List<String> tags = new ArrayList<>();

    public Shop(long id, String name, String address,  double lng, double lat, boolean withdrawn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.withdrawn = withdrawn;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
