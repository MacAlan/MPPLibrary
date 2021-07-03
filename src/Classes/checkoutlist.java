package Classes;

import javafx.beans.property.SimpleStringProperty;

public class checkoutlist {


    public String getMember() {
        return member.get();
    }

    public String getISBN() {
        return ISBN.get();
    }


    public String getBookTitle() {
        return BookTitle.get();
    }

    SimpleStringProperty member;
    SimpleStringProperty ISBN;
    SimpleStringProperty BookTitle;

    public checkoutlist(String member, String ISBN, String bookTitle) {
        this.member = new SimpleStringProperty(member);
        this.ISBN = new SimpleStringProperty(ISBN);
        BookTitle = new SimpleStringProperty(bookTitle);
    }
}
