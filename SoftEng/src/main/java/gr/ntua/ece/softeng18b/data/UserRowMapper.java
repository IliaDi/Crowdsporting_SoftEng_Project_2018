package gr.ntua.ece.softeng18b.data;

import gr.ntua.ece.softeng18b.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

    	long id            = rs.getLong("id");
        String fullname   =  rs.getString("fullname");
        String password   =  rs.getString("password");
        String mail       =  rs.getString("mail");

        return new User(id,fullname, password, mail);
    }


}
