package DatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class InsertDB extends  DatabaseProvider{


        public void insertLibraryMember(String firstname,String lastname, String phone, Integer address_id) {

            String sql = "INSERT INTO LibraryMember(firstname,lastname,phone,street) VALUES(?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, phone);
                pstmt.setInt(4, address_id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        public void insertAuthors(String firstname,String lastname, String phone, Integer address_id,String credential,String bio) {

            String sql = "INSERT INTO Authors(firstname,lastname,phone,address_id,credential,bio) VALUES(?,?,?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, phone);
                pstmt.setInt(4, address_id);
                pstmt.setString(5, credential);
                pstmt.setString(6, bio);
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

        public void insertUsers(Integer member_id,String password) {

            String sql = "INSERT INTO Users(member_id,password) VALUES(?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, member_id);
                pstmt.setString(2, password);
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

      /*  public void InsertBooks(String title,String ISBN,Integer author_id,Integer borrowDay,Integer availability ) {

            String sql = "INSERT INTO Books(title,ISBN,author_id,borrowDay,availability) VALUES(?,?,?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(   1, title);
                pstmt.setString(2,    ISBN);
                pstmt.setInt(   3, author_id);
                pstmt.setInt(4,    borrowDay);
                pstmt.setInt(   5, availability);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        public void InsertCopyBooks(Integer book_id ) {

            String sql = "INSERT INTO BookCopy( book_id) VALUES(?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(   1, book_id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
*/
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
           // app.insertAddress("1000 North","Chicago","IL","52557");
            app.insertLibraryMember("Tolganay","Muntinova","+77073095636",1);
            app.insertLibraryMember("Marat","Muntinov","+87073095636",0);
            app.insertLibraryMember("Sharlie","Moon","+87073095636",1);
            app.insertAuthors("Robert",null,"+87073095636",0,"1255 Prinston","Professor");
          //  app.insertRoles("LIBRARIAN");
          // app.insertRoles("ADMIN");
            app.insertUsers(1,"password");
            app.insertUsers(2,"password");
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
