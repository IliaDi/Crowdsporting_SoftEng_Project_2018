package gr.ntua.ece.softeng18b.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

// A 'dummy' class
public class Helper {

  public static String parse(String column, int n) {

    if (n == 0)
      return "";

    StringJoiner sj = new StringJoiner(",", " AND " + column + " in (", ")");

    for (int i = 0; i < n; i++)
      sj.add("?");

    return sj.toString();
  }

  public static String parseAtStart(String column, int n) {

  if (n == 0)
    return "";

  StringJoiner sj = new StringJoiner(",", column + " in (", ")");

  for (int i = 0; i < n; i++)
    sj.add("?");

  return sj.toString();
}

  public static void bindLongArray(PreparedStatement ps, int offset, Long[] values) throws SQLException {

    for (int i = 0; i < values.length; i++)
            ps.setLong(i + offset + 1, values[i]);

  }

  public static void bindStringArray(PreparedStatement ps, int offset, String[] values) throws SQLException {

    for (int i = 0; i < values.length; i++)
            ps.setString(i + offset + 1, values[i]);

  }

}
