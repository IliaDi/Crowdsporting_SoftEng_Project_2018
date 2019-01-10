package data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop {
    private final long id;
    private final String name;
    private final String street;
    private final int number;
    private final int postal_code;
    private final String city;
    private final String mail;
    private final String phone;
    private final String website;
    private final double lng;
    private final double lat;
    private final boolean withdrawn;


    private List<String> tags = new ArrayList<>();

    public Shop(long id, String name, String street,int number, int postal_code, String city, String mail, String phone, String website, double lng, double lat, boolean withdrawn) {
        this.id          = id;
        this.name        = name;
        this.street      = street;
        this.number      = number;
        this.postal_code = postal_code;
        this.city        = city;
        this.mail        = mail;
        this.phone       = phone;
        this.website     = website;
        this.lng         = lng;
        this.lat         = lat;
        this.withdrawn   = withdrawn;

    }


    public long getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public String getStreet() {

        return street;
    }

    public int getNumber() {

        return number;
    }
    public int getPostal_code() {

        return postal_code;
    }

    public String getCity() {

        return city;
    }

    public String getMail() {

        return mail;
    }

    public String getPhone() {

        return phone;
    }

    public String getWebsite() {

        return website;
    }

    public double getLng(){

        return lng;
    }

    public double getLat(){

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
