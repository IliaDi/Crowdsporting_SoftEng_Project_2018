package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        String username   =  rs.getString("username");
        String password   =  rs.getString("password");
        String mail       =  rs.getString("mail");

        return new User(username, password, mail);
    }


}
