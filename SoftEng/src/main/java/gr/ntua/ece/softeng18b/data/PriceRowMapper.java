package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.Price;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceRowMapper implements RowMapper {

    @Override
    public Price mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id       = rs.getLong("id");
        double price  = rs.getLong("price");
        long pid      = rs.getLong("pid");
        long sid      = rs.getLong("sid");
        String date   = rs.getString("date");

        return new Price(id, price, pid, sid, date);
    }

}
