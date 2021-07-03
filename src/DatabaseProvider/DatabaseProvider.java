package DatabaseProvider;

import Classes.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.addBooks.BooksDto;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseProvider {

    public static Connection connection = connect();

    public static Statement getStatement() {

        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement getPreparedStatement(String sqlStatement){
        try {
            return connection.prepareStatement(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getResultSet(String selectStatement) {
        ResultSet rs;
        try {
            rs = getStatement().executeQuery(selectStatement);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static  Connection connect() {
        String url = "jdbc:sqlite:.\\src\\lib\\LibraryDB.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static List<Book> getBookByISBN(String ISBN){
        List<Book> books = new LinkedList<>();
        return books;
    }

    public static void addLibraryMember(LibraryMember member){
        String sql = "INSERT INTO LibraryMember(firstname,lastname,phone,street,city,state,zip) VALUES(?,?,?,?,?,?,?)";

        try  {
            PreparedStatement pstmt = getPreparedStatement(sql);
            pstmt.setString(1, member.getFirstname());
            pstmt.setString(2, member.getLastname());
            pstmt.setString(3, member.getPhone());

            //pstmt.setInt(4, address_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addBook(Book book){

    }
    public static void addBookCopy(BookCopy bookCopy){

    }
    public static void addCheckout(Checkout checkout){

    }

    public static List<Checkout> getCheckoutByLibraryMemberId (){
        List<Checkout> checkouts = new LinkedList<>();
        return checkouts;
    }

    public static List<checkoutlist> getAllCheckoutList(){
        List<checkoutlist> checkouts = new LinkedList<>();
        String sql = "SELECT t3.ISBN, t3.title, t4.lastname ||' ' || t4.firstname FROM CheckoutRecords t " +
                "join CheckoutEntries t1 on t.id=t1.records_id " +
                "join bookCopy_id t2 on t1.bookCopy_id = t2.id" +
                "join Books t3 on t2.book_id = t3.id" +
                "join LibraryMember t4 on t4.memberId = t.member_id" ;
        try{
            ResultSet rsChckouts = getResultSet(sql);
            while (rsChckouts.next()){
                checkouts.add(new checkoutlist(rsChckouts.getString(1), rsChckouts.getString(2),rsChckouts.getString(3)));
            }
        }
        catch (Exception e){}
        return checkouts;
    }



    public static List<Autor> getMAuthors(){
        List<Autor> result = new LinkedList<>();
        String sql = "select t.id, t.firstname, t.lastname,t.bio,t.credential,t.phone  from Authors t";
        ResultSet rs = DatabaseProvider.getResultSet(sql);
        try{
            while (rs.next()){
                result.add( new Autor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getString(4)));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result.size());
        return result;

    }
    public static void InsertBooks(String title,String ISBN,Integer author_id,Integer borrowDay,Integer availability ) {

        String sql = "INSERT INTO Books(title,ISBN,author_id,borrowDay,availability) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(   1, title);
            pstmt.setString(2,    ISBN);
            pstmt.setInt(   3, author_id);
            pstmt.setInt(4,    borrowDay);
            pstmt.setInt(   5, availability);

            pstmt.executeUpdate();
        } catch (Exception e) {
            handleError(e);
        }
    }

    public static void addLibraryMember(String firstname, String lastname, String phone, String street, String city, String state, String zip){

        String sql = "INSERT INTO LibraryMember(firstname,lastname,phone,street,  city, state, zip) VALUES(?,?,?,?,?,?,?)";

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


    private void handleSuccesfully(String e) {
        // Alert the user when things go terribly wrong
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, e, ButtonType.CLOSE);
        // Close the program when the user clicks close on the alert
        // alert.setOnCloseRequest(event -> Platform.exit());
        // show the alert
        alert.show();
    }

    public static List<BooksDto> getBookByISBN1(String isbn){
        List<BooksDto> checkouts = new LinkedList<>();
        String sql = "select bc.book_id as id, b.ISBN,b.title,a.firstname || ' ' || a.lastname as author, count(*) as copies from Books b\n" +
                "left join BookCopy BC on b.id = BC.book_id\n" +
                "left join Authors A on b.author_id = A.id\n" +
                "where ISBN='" + isbn +
                "' group by bc.book_id " ;
        System.out.println(sql);
        try{
            ResultSet rsChckouts = getResultSet(sql);
            while (rsChckouts.next()){
                checkouts.add(new BooksDto(rsChckouts.getString(1),
                        rsChckouts.getString(2),
                        rsChckouts.getString(3),
                        rsChckouts.getString(4),
                        rsChckouts.getString(5)
                        ));
            }
        }
        catch (Exception e){}
        return checkouts;
    }

    private static void  handleError(Exception e) {
        // Alert the user when things go terribly wrong
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
        // Close the program when the user clicks close on the alert
        // alert.setOnCloseRequest(event -> Platform.exit());
        // show the alert
        alert.show();
    }
    public static int selectBookbyISBN(String ISBN ) {

        String sql = "SELECT t.id FROM  Books t where ISBN =" +ISBN;

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Integer bookId=rs.getInt("id");
               return bookId;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static void InsertCopyBooks(Integer book_id ) {

        String sql = "INSERT INTO BookCopy( book_id) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(   1, book_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static LibraryMember getMemberById(String id){
        String memberId = "SELECT t.firstname, t.lastname,t.phone, t.street, t.city t.state, t.zip FROM  LibraryMember t where memberId =" +id;
        String roles = "SELECT t.name FROM UserRoles UR join Roles t on UR.role_id = t.id where member_id = " +id;
        String credential = "SELECT t.member_id, t.password  FROM Users t where t.member_id = " +id;
        List<role> roleList = new LinkedList<>();
        ResultSet rsCredential = DatabaseProvider.getResultSet(credential);
        Credential user = null;
        try {
            while (rsCredential.next()){
                user = new Credential(rsCredential.getString(1),rsCredential.getString(2));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        if (user==null){
            return null;
        }


        ResultSet rsRole = DatabaseProvider.getResultSet(roles);
        try{
            while (rsRole.next()){
                if (rsRole.getString(1).toLowerCase().equals("admin")){
                    roleList.add(role.admin);
                }
                else if (rsRole.getString(1).toLowerCase().equals("librarian")){
                    roleList.add(role.librarian);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        ResultSet rsMember = DatabaseProvider.getResultSet(memberId);
        try{
            while (rsMember.next()){
                return new LibraryMember(Long.parseLong(id),
                        rsMember.getString(1),
                        rsMember.getString(2),
                        rsMember.getString(3),
                        rsMember.getString(4),
                        rsMember.getString(5),
                        rsMember.getString(6),
                        rsMember.getString(7),
                        roleList,
                        user);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}
