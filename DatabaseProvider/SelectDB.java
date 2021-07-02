package DatabaseProvider;

import Classes.*;

import java.awt.print.Book;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class SelectDB {


        private Connection connect() {
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

        public void selectAll(){
            String sql = "SELECT id, name, capacity FROM warehouses";

            try (Connection conn = this.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){

                // loop through the result set
                while (rs.next()) {
                    System.out.println(rs.getInt("id") +  "\t" +
                            rs.getString("name") + "\t" +
                            rs.getDouble("capacity"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void main(String[] args) {
            SelectDB app = new SelectDB();
            app.selectAll();
        }


    }
