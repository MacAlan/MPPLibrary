package Classes;

import java.util.*;
public class BookCopy {
    private final Books book;
    private final String copyNumber;



    public BookCopy(Books book, String copyNumber) {
        this.book = book;
        this.copyNumber = copyNumber;

    }

    public BookCopy(String title, String ISBN, boolean availability, int days,Autor autors, String copyNumber) {
        this.book = new Books(title, ISBN, availability, days, autors);
        this.copyNumber = copyNumber;

    }


    public Books getBook() {
        return this.book;
    }


    public String getCopyNumber() {
        return this.copyNumber;
    }


}