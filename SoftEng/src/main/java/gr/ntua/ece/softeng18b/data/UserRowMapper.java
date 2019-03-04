package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id           = rs.getLong("id");
        String username   =  rs.getString("fullname");
        String password   =  rs.getString("password");
        String mail       =  rs.getString("email");

        return new User(id, username, password, mail);
    }


}
