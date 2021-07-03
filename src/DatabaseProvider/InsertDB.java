package DatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class InsertDB extends  DatabaseProvider{

        public void insertLibraryMember(String firstname,String lastname, String phone,
                                        String street,String city,String state,String zip) {

            String sql = "INSERT INTO LibraryMember(firstname,lastname,phone,street,city,state,zip) VALUES(?,?,?,?,?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, phone);
                pstmt.setString(4, street);
                pstmt.setString(5, city);
                pstmt.setString(6, state);
                pstmt.setString(7, zip);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        public void insertAuthors(String firstname,String lastname, String phone, String credential,
                                  String bio,String street,String city,String state,String zip) {

            String sql = "INSERT INTO Authors(firstname,lastname,phone,credential,bio, street,city,state,zip) VALUES(?,?,?,?,?,?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, phone);
                pstmt.setString(4, credential);
                pstmt.setString(5, bio);
                pstmt.setString(6, street);
                pstmt.setString(7, city);
                pstmt.setString(8, state);
                pstmt.setString(9, zip);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void insertRoles(String roleName) {

            String sql = "INSERT INTO Roles(name) VALUES(?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, roleName);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void insertUsers(Integer member_id,String login,String password) {

            String sql = "INSERT INTO Users(member_id,login,password) VALUES(?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, member_id);
                pstmt.setString(2, login);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


       public void insertUserRoles(Integer member_id,Integer role_id) {

            String sql = "INSERT INTO UserRoles(member_id,role_id) VALUES(?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, member_id);
                pstmt.setInt(2,    role_id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }






      public void InsertCheckoutRecords( Integer member_id ) {

            String sql = "INSERT INTO CheckoutRecords( member_id) VALUES(?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, member_id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void InsertCheckoutEntries(Integer records_id, Integer bookCopy_id, Date checkout, Date duedate ) {

            String sql = "INSERT INTO CheckoutEntries( records_id, bookCopy_id,checkout,duedate) VALUES(?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, records_id);
                pstmt.setInt(   2, bookCopy_id);
                pstmt.setDate(   3, checkout);
                pstmt.setDate(   4, duedate);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



        public static void main(String[] args) {

           InsertDB app = new InsertDB();
            // insert three new rows
            app.insertLibraryMember("Tolganay","Muntinova","+77073095636","100N","Faifiled","IOWA","52557");
            app.insertLibraryMember("Marat","Muntinov","+87073095636","100N","Faifiled","IOWA","52557");
            app.insertLibraryMember("Sharlie","Moon","+87073095636","100N","Faifiled","IOWA","52557");

            app.insertAuthors("Robert","Sedjik","+87073095636","500page Prinston Professor","1255 Prinston","100N","Faifiled","IOWA","52557");
            app.insertRoles("LIBRARIAN");
            app.insertRoles("ADMIN");

            app.insertUsers(1,"admin","password");
            app.insertUsers(2,"librarian","password");

            app.insertUserRoles(1,1);
            app.insertUserRoles(2,2);
            app.insertUserRoles(3,1);
            app.insertUserRoles(3,2);

            app.InsertBooks("IT book","123-456-789",1,21,1);
            app.InsertBooks("Java Book","323-456-789",1,7,1);
            app.InsertBooks("C# Book","423-456-789",1,7,1);

            app.InsertCopyBooks(1);
            app.InsertCopyBooks(1);
            app.InsertCopyBooks(2);
            app.InsertCopyBooks(3);

            app.InsertCheckoutRecords(1);
            app.InsertCheckoutEntries(1,2,new Date(Calendar.getInstance().getTimeInMillis()),new Date(Calendar.getInstance().getTimeInMillis()));

            app.InsertCheckoutEntries(1,1,new Date(Calendar.getInstance().getTimeInMillis()),new Date(Calendar.getInstance().getTimeInMillis()));

        }

    }
