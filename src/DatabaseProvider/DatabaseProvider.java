package DatabaseProvider;

import Classes.*;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public final class DatabaseProvider {

    public static Connection connection = connect();

    public static Statement getStatement() {

        try {
            return connection.createStatement();
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

    public static List<Book> getBookByISBN(String ISBN){
        List<Book> books = new LinkedList<>();
        return books;
    }

    public static void addLibraryMember(LibraryMember member){

    }

    public static void addBook(Book book){

    }
    public static void addBookCopy(BookCopy bookCopy){

    }
    public static void addCheckout(Checkout checkout){

    }

    public static List<Checkout> getCheckoutByLibraryMemberId(String id){
        List<Checkout> checkouts = new LinkedList<>();
        return checkouts;
    }


    public static LibraryMember getMemberById(String id){
        String memberId = "SELECT t.firstname, t.lastname,t.phone, t.address_id FROM  LibraryMember t where memberId =" +id;
        String address = "SELECT t.street, t.city, t.state, t.zip FROM Address t join LibraryMember t2 on t2.address_id = t.id  where t2.memberId =" +id;
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
        catch (Exception e){}

        if (user==null){
            return null;
        }
        ResultSet rsAddress = DatabaseProvider.getResultSet(address);
        try{
            while (rsAddress.next()) {
                Address memberAddress = new Address(rsAddress.getString(1),rsAddress.getString(2),rsAddress.getString(3),rsAddress.getString(4));
            }
        }
        catch (Exception e){}


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
        catch (Exception e){}

        ResultSet rsMember = DatabaseProvider.getResultSet(memberId);
        try{
            while (rsMember.next()){
                return new LibraryMember(Long.parseLong(id),  rsMember.getString(1),  rsMember.getString(2),  rsMember.getString(3),  address ,roleList, user);
            }
        }
        catch (Exception e){}
        return null;
    }

}
