package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.Token;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenRowMapper implements RowMapper {

    @Override
    public Token mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id       = rs.getLong("id");
        String token  = rs.getString("token");

        return new Token(id, token);
    }

}
