package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.Shop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowMapper implements RowMapper {

    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id            =  rs.getLong("id");
        String name        =  rs.getString("name");
        String address     =  rs.getString("address");
        double lng         =  rs.getDouble("lng");
        double lat         =  rs.getDouble("lat");
        boolean withdrawn  =  rs.getBoolean("withdrawn");

        return new Shop(id, name, address, lng, lat, withdrawn);
    }

}
