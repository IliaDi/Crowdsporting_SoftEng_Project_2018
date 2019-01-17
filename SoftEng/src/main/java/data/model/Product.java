package data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {

    private final long id;
    private final String name;
    private final String description;
    private final String category;
    private final boolean withdrawn;
    private final long likes;
    private List<String> tags = new ArrayList<>();

    public Product(long id, String name, String description, String category, boolean withdrawn, long likes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.withdrawn = withdrawn;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public long getLikes() {
        return likes;
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
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}