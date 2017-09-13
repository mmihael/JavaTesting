package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mmamula on 10.12.2016.
 */
public class Jdbc {

    private static Connection conn;

    public static Connection conn() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.33.66/test?user=test&password=test");
        }
        return conn;
    }

    public static void select() {
        try {
            Connection conn = Jdbc.conn();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM test");
            while(rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("string"));
                System.out.println(rs.getInt("number"));
                System.out.println(rs.getDate("date"));
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert() {
        try {
            Connection conn = Jdbc.conn();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO test (string, number, date) VALUES ('test', 2, '1991-10-21')", Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            while (keys.next()) {
                System.out.println("ID: " + keys.getLong(1));
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}