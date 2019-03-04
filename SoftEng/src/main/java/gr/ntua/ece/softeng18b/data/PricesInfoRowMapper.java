package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.PricesInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PricesInfoRowMapper implements RowMapper {

    @Override
    public PricesInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        double price        = rs.getDouble("price");
        String date         = rs.getString("date");
        long pid            = rs.getLong("product.id");
        String pname        = rs.getString("product.name");
        long sid            = rs.getLong("shop.id");
        String sname        = rs.getString("shop.name");
        String address      = rs.getString("shop.address");

        return new PricesInfo(price, date, pid, pname, sid, sname, address, 0);
    }

}
