package DatabaseProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB extends DatabaseProvider {

    public static void createNewTable() {

        String member = "   create table IF NOT EXISTS LibraryMember(" +
                "                        memberId     INTEGER not null primary key autoincrement ,\n" +
                "                        firstname VARCHAR(200)  null ,\n" +
                "                        lastname      VARCHAR(200)  null,\n" +
                "                        phone VARCHAR(10)  null,\n" +
                "                        street    VARCHAR(200)  null,\n" +
                "                        city      VARCHAR(200)  null,\n" +
                "                        state     VARCHAR(10)  null,\n" +
                "                        zip       VARCHAR(10)  null\n" +
                "                );";

        String author = "   create table IF NOT EXISTS Authors(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        firstname VARCHAR(200)  null,\n" +
                "                        lastname      VARCHAR(200)  null,\n" +
                "                        phone VARCHAR(10)  null,\n" +
                "                        credential VARCHAR(10)  null,\n" +
                "                        bio VARCHAR(10)  null,\n" +
                "                        street    VARCHAR(200)  null,\n" +
                "                        city      VARCHAR(200)  null,\n" +
                "                        state     VARCHAR(10)  null,\n" +
                "                        zip       VARCHAR(10)  null\n" +

                ");";

           String user = "   create table IF NOT EXISTS Users(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        member_id   int(10)\n," +
                   "                     login   VARCHAR(200),\n" +
                "                        password    VARCHAR(200),\n" +
                "                        FOREIGN KEY (member_id)  REFERENCES LibraryMember (memberId)" +

                ");";


         String roles = "   create table IF NOT EXISTS Roles(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        name    VARCHAR(200)\n" +
                ");";

       String user_role = "   create table IF NOT EXISTS UserRoles(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        member_id     int(10),\n" +
                "                        role_id      int(10),\n" +
                "                        FOREIGN KEY (member_id)  REFERENCES LibraryMember (memberId)," +
                "                        FOREIGN KEY (role_id)  REFERENCES Roles (id)" +
                ");";

        String book = "   create table IF NOT EXISTS Books(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        title      VARCHAR(200),\n" +
                "                        ISBN       VARCHAR(200),\n" +
                "                        author_id   int(10),\n" +
                "                        borrowDay   int(10),\n" +
                "                        availability  int(10),\n" +
                "                        FOREIGN KEY (author_id)  REFERENCES Authors(id)" +
                "" +
                "" +
                ");";

        String bookCopy = "   create table IF NOT EXISTS BookCopy(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        book_id  int(10),\n" +
                "                        availability  int(10),\n" +
                "                        FOREIGN KEY (book_id)  REFERENCES Books(id)" +
                ");";

        String records = "   create table IF NOT EXISTS CheckoutRecords(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        member_id  int(10),\n" +
                "                        FOREIGN KEY (member_id)  REFERENCES LibraryMember(memberId)" +
                ");";

        String entries = "   create table IF NOT EXISTS CheckoutEntries(" +
                "                        id  INTEGER not null primary key autoincrement,\n" +
                "                        records_id  int(10),\n" +
                "                        bookCopy_id  int(10),\n" +
                "                        checkout  date,\n" +
                "                        duedate  date,\n" +
                "                        FOREIGN KEY (records_id)  REFERENCES CheckoutRecords(id)," +
                "                        FOREIGN KEY (bookCopy_id) REFERENCES BookCopy(id)" +
                ");";



        try (Connection conn = DatabaseProvider.connection;
             Statement stmt = conn.createStatement()) {
             stmt.execute(member);
             stmt.execute(author);
             stmt.execute(user);
             stmt.execute(roles);
             stmt.execute(user_role);
             stmt.execute(book);
             stmt.execute(bookCopy);
             stmt.execute(records);
             stmt.execute(entries);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        createNewTable();
    }
}
