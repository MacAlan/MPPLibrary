package DatabaseProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DatabaseProvider {


    public  static  Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:D:\\Users\\Downloads\\LibraryProject\\src\\lib\\LibraryDB.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
