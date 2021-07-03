package DatabaseProvider;

import Classes.*;

import javax.xml.crypto.Data;
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
        // SQLite connection string
        String url = "jdbc:sqlite:.\\src\\lib\\LibraryDB.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static List<Books> getBooksByISBN(String ISBN){
        List<Books> books = new LinkedList<>();
        String sql = "select t.title, " +
                "       t.ISBN, " +
                "       t.availability, " +
                "       t.borrowDay, " +
                "       t.author_id, " +
                "       a.firstname, " +
                "       a.lastname, " +
                "       a.phone, " +
                "       a.credential " +
                "from Books t left join Authors A on t.author_id = A.id where t.ISBN = '" + ISBN + "'";
        ResultSet rs = DatabaseProvider.getResultSet(sql);
        try {
            while (rs.next()) {

                books.add( new Books(rs.getString(1), rs.getString(2),rs.getBoolean(3),rs.getInt(4),
                        new Autor( rs.getInt(5),  rs.getString(6),  rs.getString(7),rs.getString(8),rs.getString(9))));
            }
        }
        catch (Exception e){}
        return books;
    }

    public static Books getBookByISBN(String ISBN){
        Books books = new Books();
        String sql = "select t.title, " +
                "       t.ISBN, " +
                "       t.avalible, " +
                "       t.borrowDay, " +
                "       t.author_id, " +
                "       a.firstname, " +
                "       a.lastname, " +
                "       a.phone, " +
                "       a.credential " +
                "from Books t left join Authors A on t.author_id = A.id where t.ISBN = '" + ISBN + "'";
        ResultSet rs = DatabaseProvider.getResultSet(sql);
        try {
            while (rs.next()) {

                books.setTitle(rs.getString(1));
                books.setISBN(rs.getString(2));
                books.setAutors(new Autor( rs.getInt(5),  rs.getString(6),  rs.getString(7),rs.getString(8),rs.getString(9)));
                books.setAvailability(rs.getBoolean(3));
                books.setDays(rs.getInt(4));
            }
        }
        catch (Exception e){}
        return books;
    }

    public static BookCopy getBookCopyByISBN(String ISBN){
        Books book = getBookByISBN(ISBN);
        BookCopy copyBook = null;
        String sql = "select " +
                "t2.id, " +
                "       t.title, " +
                "t.ISBN, " +
                "t.availability, " +
                "t.borrowDay, " +
                "t.author_id, " +
                "a.firstname, " +
                "a.lastname,  " +
                "a.phone, " +
                "a.credential " +
                "from Books t " +
                "    left join Authors A on t.author_id = A.id " +
                "    left join BookCopy t2 on t2.book_id = t.id " +
                "where t.ISBN = '" + ISBN + "'  LIMIT 1";
        ResultSet rs = DatabaseProvider.getResultSet(sql);
        try {
            while (rs.next()) {
                copyBook = new BookCopy(book,rs.getString(1));
            }
        }
        catch (Exception e){}
        return copyBook;
    }

    public static boolean checkBookByISBN(String ISBN){
        String sql = "select 1 from Books where ISBN = " + ISBN;
        ResultSet rs = DatabaseProvider.getResultSet(sql);
        try {
            while (rs.next()){
                if (rs.getString(1).equals("1")){return true;}
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }




    public static void addLibraryMember(LibraryMember member){
        String sql = "INSERT INTO LibraryMember(firstname,lastname,phone,address_id) VALUES(?,?,?,?)";

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
        String sql = "SELECT t3.ISBN, t3.title, t4.lastname ||' ' || t4.firstname FROM CheckoutRecords t\n" +
                "                join CheckoutEntries t1 on t.id=t1.records_id\n" +
                "                join BookCopy t2 on t1.bookCopy_id = t2.id\n" +
                "                join Books t3 on t2.book_id = t3.id\n" +
                "                join LibraryMember t4 on t4.memberId = t.member_id" ;
        try{
            ResultSet rsChckouts = getResultSet(sql);
            while (rsChckouts.next()){
                checkouts.add(new checkoutlist(rsChckouts.getString(1), rsChckouts.getString(2),rsChckouts.getString(3)));
            }
        }
        catch (Exception e){}
        return checkouts;
    }


    public void insertAddress(String street, String city,String state,String zip) {

        String sql = "INSERT INTO Address(street,city,state,zip) VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = getPreparedStatement(sql);
            pstmt.setString(1, street);
            pstmt.setString(2, city);
            pstmt.setString(3, state);
            pstmt.setString(4, zip);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static LibraryMember getMemberById(String id){
        String memberId = "SELECT t.firstname, t.lastname,t.phone, t.address_id FROM  LibraryMember t where memberId =" +id;
        String address = "SELECT t.street, t.city, t.state, t.zip FROM Address t join LibraryMember t2 on t2.address_id = t.id  where t2.memberId =" +id;
        String roles = "SELECT t.name FROM UserRoles UR join Roles t on UR.role_id = t.id where member_id = " +id;
        String credential = "SELECT t.member_id, t.password  FROM Users t where t.member_id = " +id;
        List<role> roleList = new LinkedList<>();
        ResultSet rsCredential = DatabaseProvider.getResultSet(credential);
        Credential user = null;
        Address memberAddress = new Address();
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
        ResultSet rsAddress = DatabaseProvider.getResultSet(address);
        try{
            while (rsAddress.next()) {
                memberAddress.setStreet(rsAddress.getString(1));
                memberAddress.setCity(rsAddress.getString(2));
                memberAddress.setState(rsAddress.getString(3));
                memberAddress.setZip(rsAddress.getString(4));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
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
                return new LibraryMember(Long.parseLong(id),  rsMember.getString(1),  rsMember.getString(2),  rsMember.getString(3),  memberAddress ,roleList, user);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        List<checkoutlist> asd = DatabaseProvider.getAllCheckoutList();
        for (checkoutlist a:asd  ) {
            System.out.println(a.getBookTitle());
        }
    }

}
