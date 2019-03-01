package gr.ntua.ece.softeng18b.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;

public class Price {

    private final long id;
    private final long act_id;
    private final long prov_id;
    private final Date date;

    public Price(long id, long act_id, long prov_id, Date date) {
        this.id          = id;
        this.act_id      = act_id;
        this.prov_id     = prov_id;
        this.date        = date;
    }

    public long getId() {
        return id;
    }

    public long getAct_id() {
        return act_id;
    }
    public long getProv_id() {
        return prov_id;
    }

    public Date getDate() {
        return date;
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
