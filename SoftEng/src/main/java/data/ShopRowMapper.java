package data;

import data.model.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ShopRowMapper implements RowMapper {
    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("id");
        String name = rs.getString("name");
        String street = rs.getString("street");
        int number = rs.getInt("number");
        int postal_code = rs.getInt("postal_code");
        String city = rs.getString("city");
        String mail = rs.getString("mail");
        String phone = rs.getString("phone");
        String website = rs.getString("website");
        double lng = rs.getDouble("lng");
        double lat = rs.getDouble("lat");
        boolean withdrawn = rs.getBoolean("withdrawn");
        long likes =rs.getLong("likes");

        return new Shop(id, name, street, number, postal_code, city, mail, phone, website, lng, lat, withdrawn);
    }

}
