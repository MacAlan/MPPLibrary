package DatabaseProvider;

import Classes.*;
import addBooks.BooksDto;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.xml.crypto.Data;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseProvider {


    public static boolean addCheckoutEntry(List<CheckoutEntry> list, int id){
        String sql = "insert into CheckoutEntries (records_id, bookCopy_id, checkout, duedate) values(?,?,?,?)";
        for (CheckoutEntry row: list ) {
            System.out.println("======================================");
            System.out.println(row);
            try
                    (Connection c = connect();
                     Statement stm = c.createStatement();
                     PreparedStatement preparedStatement = c.prepareStatement(sql))
            {
                preparedStatement.setInt(1,id);
                preparedStatement.setInt(2,getBookByISBN(row.getBook().getBook().getISBN()).id);
                preparedStatement.setDate(3, Date.valueOf(row.getCheckoutDate()));
                preparedStatement.setDate(4, Date.valueOf(row.getDueDate()));
                preparedStatement.executeUpdate();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return  false;
    }

    public static boolean addCheckout(Checkout checkout){
        String sql = "insert into CheckoutRecords(member_id) values(?)";
        String sql2 = "select max(id) from CheckoutRecords where member_id = ";
        int checkoutId = 0;
        try
                (Connection c = connect();
                 Statement stm = c.createStatement();
                 PreparedStatement preparedStatement = c.prepareStatement(sql))
        {

            preparedStatement.setInt(1, Integer.parseInt(checkout.getPerson()));
            preparedStatement.executeUpdate();

            ResultSet rs =stm.executeQuery(sql2 + checkout.getPerson());

            while (rs.next()){
                checkoutId = rs.getInt(1);
            }

            return addCheckoutEntry(checkout.CheckoutEntries, checkoutId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
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

        try
                (Connection c = connect();
                Statement stm = c.createStatement()
                )
        {
        ResultSet rs = stm.executeQuery(sql);

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
                "       case when exists (select 1 from BookCopy tt where tt.book_id = t.id and tt.avalible = 1) as avalible, " +
                "       t.borrowDay, " +
                "       t.author_id, " +
                "       a.firstname, " +
                "       a.lastname, " +
                "       a.phone, " +
                "       a.credential, " +
                " t.id " +
                "from Books t left join Authors A on t.author_id = A.id where t.ISBN = '" + ISBN + "'";
        try
        (Connection c = connect();
        Statement stm = c.createStatement()
                ){
        ResultSet rs = stm.executeQuery(sql);

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
    public static List<BooksDto> getBookByISBN1(String isbn){
        List<BooksDto> checkouts = new LinkedList<>();
        String sql = "select bc.book_id as id, b.ISBN,b.title,a.firstname || ' ' || a.lastname as author, count(*) as copies from Books b\n" +
                "left join BookCopy BC on b.id = BC.book_id\n" +
                "left join Authors A on b.author_id = A.id\n" +
                "where ISBN='" + isbn +
                "' group by bc.book_id " ;
        System.out.println(sql);
        try
                (Connection c = connect();
                 Statement stm = c.createStatement())

        {
            ResultSet rsChckouts = stm.executeQuery(sql);
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

    public static List<Autor> getMAuthors(){
        List<Autor> result = new LinkedList<>();
        String sql = "select t.id, t.firstname, t.lastname,t.bio,t.credential,t.phone  from Authors t";

        try
                (Connection c = connect();
                 Statement stm = c.createStatement())
        {
        ResultSet rs =stm.executeQuery(sql);

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
        try
                (Connection c = connect();
                 Statement stm = c.createStatement())
        {
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()) {
                copyBook = new BookCopy(book,rs.getString(1));
            }
        }
        catch (Exception e){}
        return copyBook;
    }

    public static boolean checkBookByISBN(String ISBN){
        String sql = "select 1 from Books where ISBN = " + ISBN;
        try
                (Connection c = connect();
                 Statement stm = c.createStatement())
        {
            ResultSet rs =stm.executeQuery(sql);
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


    public static void InsertBooks(String title,String ISBN,Integer author_id,Integer borrowDay,Integer availability ) {

        String sql = "INSERT INTO Books(title,ISBN,author_id,borrowDay,availability) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, ISBN);
            pstmt.setInt(3, author_id);
            pstmt.setInt(4, borrowDay);
            pstmt.setInt(5, availability);

            pstmt.executeUpdate();
        } catch (Exception e) {
            handleError(e);
        }

    }
    private static void  handleError(Exception e) {
        // Alert the user when things go terribly wrong
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
        // Close the program when the user clicks close on the alert
        // alert.setOnCloseRequest(event -> Platform.exit());
        // show the alert
        alert.show();
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
        try
                (Connection c = connect();
                 Statement stm = c.createStatement())
        {
            ResultSet rsChckouts =stm.executeQuery(sql);
            while (rsChckouts.next()){
                checkouts.add(new checkoutlist(rsChckouts.getString(1), rsChckouts.getString(2),rsChckouts.getString(3)));
            }
        }
        catch (Exception e){}
        return checkouts;
    }


    public static LibraryMember getMemberById(String id){
        String memberId = "SELECT t.firstname, t.lastname,t.phone, t.street, t.city,t.state,t.zip FROM  LibraryMember t where memberId =" +id;
        String roles = "SELECT t.name FROM UserRoles UR join Roles t on UR.role_id = t.id where member_id = " +id;
        String credential = "SELECT t.member_id, t.password  FROM Users t where t.member_id = " +id;
        List<role> roleList = new LinkedList<>();
        ResultSet rsCredential = null;
        Credential user = null;
        LibraryMember Member = null;
        try
                (Connection c = connect();
                 Statement stm = c.createStatement())
        {
            rsCredential = stm.executeQuery(credential);
            while (rsCredential.next()){
                user = new Credential(rsCredential.getString(1),rsCredential.getString(2));}
            if (user==null){
                return null;
            }

            ResultSet rsRole = stm.executeQuery(roles);

            while (rsRole.next()){
                if (rsRole.getString(1).toLowerCase().equals("admin")){
                    roleList.add(role.admin);
                }
                else if (rsRole.getString(1).toLowerCase().equals("librarian")){
                    roleList.add(role.librarian);
                }
            }

            ResultSet rsMember = stm.executeQuery(memberId);

            while (rsMember.next()){
                Member = new LibraryMember(Long.parseLong(id),  rsMember.getString(1),  rsMember.getString(2),  rsMember.getString(3), rsMember.getString(4),rsMember.getString(5),rsMember.getString(6),rsMember.getString(7)  ,roleList, user);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return Member;
    }

    public static void main(String[] args) {
        List<checkoutlist> asd = DatabaseProvider.getAllCheckoutList();
        for (checkoutlist a:asd  ) {
            System.out.println(a.getBookTitle());
        }
    }

}
