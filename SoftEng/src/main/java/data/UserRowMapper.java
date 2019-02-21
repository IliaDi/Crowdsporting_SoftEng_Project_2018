package data;

import data.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");
        long points =rs.getLong("points");
        String mail = rs.getString("mail");

        return new User(username, password, points, mail);
    }


}
